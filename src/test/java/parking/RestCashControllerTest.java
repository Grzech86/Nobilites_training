package parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import parking.controllers.AccountController;
import parking.domain.BalanceResult;
import parking.domain.rest.*;
import parking.rest.RestAccountController;
import parking.rest.RestCashController;

import java.math.BigDecimal;

@SpringBootTest
class RestCashControllerTest {
    private static Logger logger = LoggerFactory.getLogger(RestCashControllerTest.class);

    @Autowired
    RestAccountController restAccountController;
    @Autowired
    RestCashController restCashController;
    @Autowired
    AccountController accountController;

    @BeforeEach
    public void prepare() {
        final AccountCreateRequest accountCreateRequest = new AccountCreateRequest(
                "Anna",
                "Kowalska",
                "anna.kowalska@gmail.com",
                537128932,
                "GD3279G", "GD92K20");

        AccountCreateResponse account1 = restAccountController.create(accountCreateRequest);

//        Assertions.assertInstanceOf(AccountCreateResponse.AccountCreatedSuccessfully.class, account1);
    }

    @Test
    @DisplayName("Test of debit")
    public void testRestCashControllerDebit() {
        final var optionalUserIdentifier = accountController.findByEmail("anna.kowalska@gmail.com");

        optionalUserIdentifier.ifPresentOrElse(
                userIdentifier -> {
                    final var beforeBalanceResponse = restCashController.balance(new BalanceRequest(userIdentifier));
                    restCashController.debitAccount(new DebitRequest(userIdentifier, BigDecimal.valueOf(1000)));
                    final var afterBalanceResponse = restCashController.balance(new BalanceRequest(userIdentifier));

                    Assertions.assertInstanceOf(BalanceResult.Ok.class, beforeBalanceResponse);
                    Assertions.assertInstanceOf(BalanceResult.Ok.class, afterBalanceResponse);

                    if (beforeBalanceResponse instanceof BalanceResult.Ok beforeOk && afterBalanceResponse instanceof BalanceResult.Ok afterOk) {
                        final var expected = new BigDecimal("1000.00");

                        Assertions.assertEquals(expected, afterOk.balance().subtract(beforeOk.balance()));
                    }
                },
                () -> Assertions.fail("account not found"));
    }
}