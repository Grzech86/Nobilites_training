package parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.nobilites.parking.domain.database.user.Identifier;
import pl.nobilites.parking.domain.rest.*;
import pl.nobilites.parking.repositories.AccountRepository;
import pl.nobilites.parking.rest.RestAccountController;

@SpringBootTest
class RestAccountControllerTest {
    @Autowired
    RestAccountController restAccountController;

    @Autowired
    AccountRepository accountRepository;

    AccountCreateRequest accountCreateRequest1;
    AccountCreateRequest accountCreateRequest2;
    AccountCreateRequest accountCreateRequest3;

    @BeforeEach
    public void prepare(){
        accountRepository.deleteAll();

        accountCreateRequest1 = new AccountCreateRequest(
                "Karol",
                "Kania",
                "karollukaszkania@gmail.com",
                607111014,
                "WJ3369J", "WJ3370J", "WJ3371J");

        accountCreateRequest2 = new AccountCreateRequest(
                "Carlos",
                "Cuhnia",
                "carloscuhnia@gmail.com",
                607111111,
                "GD3369J" , "GD3370J");

        accountCreateRequest3 = new AccountCreateRequest(
                "Charles",
                "Cahnia",
                "charlescahnia@gmail.com",
                607014014,
                "EL3369J");
    }

    @Test
    @DisplayName("Test of \"create account\": successful")
    public void testPositiveCreateRestAccountController() {

        final var actual = restAccountController.create(accountCreateRequest1);
        Assertions.assertInstanceOf(AccountCreateResponse.AccountCreatedSuccessfully.class, actual);
    }
    @Test
    @DisplayName("Test of \"create account\": again the same email address")
    public void testNegative1CreateRestAccountController() {

        restAccountController.create(accountCreateRequest1);

        final var actual = restAccountController.create(accountCreateRequest1);
        Assertions.assertInstanceOf(AccountCreateResponse.AccountNotCreated.class, actual);
    }

    @Test
    @DisplayName("Test of \"create account\": incorrect email address")
    public void testNegative2CreateRestAccountController() {

        final var accountCreateRequest4 = new AccountCreateRequest(
                "A",
                "B",
                "incorrect-email",
                0 ,
                "A");

        final var actual = restAccountController.create(accountCreateRequest4);
        Assertions.assertInstanceOf(AccountCreateResponse.AccountNotCreated.class, actual);
    }


    @Test
    @DisplayName("Test of \"delete account\": successful")
    public void testPositiveDelete() {

        restAccountController.create(accountCreateRequest1);
        restAccountController.create(accountCreateRequest2);
        restAccountController.create(accountCreateRequest3);

        final var actual =
                restAccountController.delete(
                        new AccountDeleteRequest(
                                new Identifier("carloscuhnia@gmail.com")));
        Assertions.assertInstanceOf(AccountDeleteResponse.AccountDeletedSuccessfully.class, actual);

    }
    @Test
    @DisplayName("Test of \"delete account\": incorrect email address")
    public void testNegativeDelete() {

        restAccountController.create(accountCreateRequest1);
        restAccountController.create(accountCreateRequest2);
        restAccountController.create(accountCreateRequest3);

        final var actual =
                restAccountController.delete(
                        new AccountDeleteRequest(
                                new Identifier("niematakiegomaila@gmail.com")));
        Assertions.assertInstanceOf(AccountDeleteResponse.AccountNotDeleted.class, actual);
    }

//    @Test
//    @DisplayName("Test of plates modifications")
//    public void testPlatesModifications() {
//
//        restAccountController.create(accountCreateRequest1);
//        restAccountController.create(accountCreateRequest2);
//        restAccountController.create(accountCreateRequest3);
//
//        final var actual1 = restAccountController
//                .addPlates(new PlatesAddRequest(new Identifier("carloscuhnia@gmail.com"), "NEWPL4T31", "NEWPL4T32" ));
//        Assertions.assertInstanceOf(PlatesModificationResponse.PlatesModificationSucceed.class, actual1);
//
//
//        final var actual2 = restAccountController
//                .deletePlate(new PlatesDeleteRequest("WJ3370J"));
//        Assertions.assertInstanceOf(PlatesModificationResponse.PlatesModificationSucceed.class, actual2);
//
//
//        final var actual3 = restAccountController
//                .deletePlate(new PlatesDeleteRequest("WJ3370J"));
//        Assertions.assertInstanceOf(PlatesModificationResponse.PlatesModificationFail.class, actual3);
//
//
//        final var actual4 = restAccountController
//                .clearPlates(new PlatesClearRequest(new Identifier("karollukaszkania@gmail.com")));
//        Assertions.assertInstanceOf(PlatesModificationResponse.PlatesModificationSucceed.class, actual4);
//
//
//        final var actual5 = restAccountController
//                .clearPlates(new PlatesClearRequest(new Identifier("niematakiegoadresu@gmail.com")));
//        Assertions.assertInstanceOf(PlatesModificationResponse.PlatesModificationFail.class, actual5);
//
//    }



}