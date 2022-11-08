package Nobilities.ex50;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.http.client.HttpClient;
import java.util.Collection;
import java.util.List;

//Calculate average USD to PLN currency rate for September 2022
public class Currency {

    record CurrencyRate(String table, String currency, String code, Collection<Rate> rates) {}

    record Rate(String no, String effectiveDate, double bid, double ask) {}

    public static void main(String[] args) {
        HttpClient client = HttpClient.create();

        String body = client.get()
                .uri("http://api.nbp.pl/api/exchangerates/rates/c/eur/2022-09-30")
                .responseContent()
                .aggregate()
                .asString()
                .block();

        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        try {
            CurrencyRate currencyRate = mapper.readValue(body, CurrencyRate.class);
            System.out.println("currency rate: " + currencyRate);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}


