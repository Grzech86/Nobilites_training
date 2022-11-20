package Nobilites.ex36;

import Nobilities.ex36.Barcode;
import Nobilities.ex36.BarcodeScanner;
import Nobilities.ex36.Priceable;
import Nobilities.ex36.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ex36Test {
    BarcodeScanner barcodeScanner = new BarcodeScanner(
            List.of(new Product(new Barcode("12345"), new Priceable.Price(7.25)),
                    new Product(new Barcode("23456"), new Priceable.Price(12.50)),
                    new Product(new Barcode("234561"), new Priceable.Price(12.50)),
                    new Product(new Barcode("234562"), new Priceable.Price(12.50)),
                    new Product(new Barcode("234563"), new Priceable.Price(12.50)),
                    new Product(new Barcode("234564"), new Priceable.Price(12.50))
                    )
    );

    @BeforeAll
    public static void prepare() {

    }


    @Test
    @DisplayName("12345 code")
    public void test12345Code() {
            final var expected = new Priceable.Price(7.25);
            final var actual = barcodeScanner.scan(new Barcode("12345"));
            Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("23456 code")
    public void test23456Code() {
        final var expected = new Priceable.Price(12.50);
        final var actual = barcodeScanner.scan(new Barcode("23456"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("empty barcode")
    public void testEmptyBarcode() {
        final var actual = barcodeScanner.scan(new Barcode(""));
        Assertions.assertInstanceOf(Priceable.Empty.class, actual);
    }

    @Test
    @DisplayName("99999 not found")
    public void test99999CodeVariant2() {
        final var expected = 19.75;

        final var actual = barcodeScanner.scanAndSum(List.of(new Barcode("12345"), new Barcode("23456")));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("sum")
    public void test9Sum() {
        final var actual = barcodeScanner.scan(new Barcode("99999"));

        if (actual instanceof Priceable.Price p) {
            System.out.println("amount " + p.amount());
        } else {
            System.out.println("no amount");
        }
        Assertions.assertInstanceOf(Priceable.NotFound.class, actual);
    }

}
