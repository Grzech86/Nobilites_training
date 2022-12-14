package parking.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import parking.controllers.Accountant;
import parking.domain.BalanceResult;
import parking.domain.DebitResult;
import parking.domain.rest.*;

@RestController
public class RestCashController {
    Accountant accountant;

    RestCashController(Accountant accountant) {
        this.accountant = accountant;
    }

    @PostMapping("/balance")
    public BalanceResult balance(@RequestBody BalanceRequest balanceRequest) {
        return accountant.getBalanceInPLN(balanceRequest);
    }

    @PostMapping("/balance/debit")
    public DebitResponse debitAccount(@RequestBody DebitRequest debitRequest) {
        final var result = accountant.debit(debitRequest.identifier(), debitRequest.amount());
        if(result instanceof DebitResult.SomethingWentWrong){
            return new DebitResponse.SomethingGetWrong();
        } else { return new DebitResponse.Success();}
    }
}
