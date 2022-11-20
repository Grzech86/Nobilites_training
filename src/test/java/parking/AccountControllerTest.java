package parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.nobilites.parking.controllers.AccountController;
import pl.nobilites.parking.domain.database.user.Identifier;
import pl.nobilites.parking.repositories.CarRepository;

@SpringBootTest
class AccountControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(AccountControllerTest.class);
    @Autowired
    AccountController accountController;

    @Autowired
    CarRepository carRepository;


    @BeforeEach
    public void clearAccountController() {
    }

    public void prepareAccountControllerWithThreeAccounts() {
        accountController.createNewAccount(
                "Karol",
                "Kania",
                "karollukaszkania@gmail.com",
                607111014,
                "WJ3369J", "WJ3370J", "WJ3371J");

        accountController.createNewAccount(
                "Carlos",
                "Cuhnia",
                "carloscuhnia@gmail.com",
                607111111,
                "GD3369J", "GD3370J");

        accountController.createNewAccount(
                "Charles",
                "Cahnia",
                "charlescahnia@gmail.com",
                607014014,
                "EL3369J");
    }

    @Test
    @DisplayName("Test: creating 3 new accounts")
    public void testAccountControllerCreateMethod() {
        accountController.createNewAccount(
                "Karol",
                "Kania",
                "karollukaszkania@gmail.com",
                607111014,
                "WJ3369J", "WJ3370J", "WJ3371J");

        accountController.createNewAccount(
                "Carlos",
                "Cuhnia",
                "carloscuhnia@gmail.com",
                607111111,
                "GD3369J", "GD3370J");

        accountController.createNewAccount(
                "Charles",
                "Cahnia",
                "charlescahnia@gmail.com",
                607014014,
                "EL3369J");

        logger.info("There is {} accounts registered.", accountController.amountOfAccounts());

        final var expected = 3;
        final var actual = accountController.amountOfAccounts();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test: delete account")
    public void testAccountControllerDeleteMethod() {

        prepareAccountControllerWithThreeAccounts();

        final var actual1 = accountController.amountOfAccounts();
        Assertions.assertEquals(3, actual1);

        accountController.deleteExistingAccount(new Identifier("carloscuhnia@gmail.com"));

        final var actual2 = accountController.amountOfAccounts();
        Assertions.assertEquals(2, actual2);
    }

    @Test
    @DisplayName("Test: find account by email")
    public void testAccountFindByEmail() {

        prepareAccountControllerWithThreeAccounts();

        final var optionalAccount = accountController
                .findBy("carloscuhnia@gmail.com");
        Assertions.assertTrue(optionalAccount.isPresent(), "account not found");

        final var actual2 = accountController.findBy("niematakiegoadresu@gmail.com").isEmpty();
        Assertions.assertTrue(actual2);
    }

    @Test
    @DisplayName("Test: find account by UserIdentifier")
    public void testAccountFindByUserIdentifier() {

        prepareAccountControllerWithThreeAccounts();

        final var actual1 = accountController.findBy(new Identifier("carloscuhnia@gmail.com")).isPresent();
        Assertions.assertTrue(actual1);

        final var optionalAccount = accountController
                .findBy("carloscuhnia@gmail.com");
        final var actual2 = optionalAccount.isPresent();
        Assertions.assertTrue(actual2);

        final var actual3 = optionalAccount
                .get().getIdentifier().getUserId().equals("carloscuhnia@gmail.com");
        Assertions.assertTrue(actual3);

        final var actual4 = accountController.findBy(new Identifier("niematakiegoadresu@gmail.com")).isEmpty();
        Assertions.assertTrue(actual4);
    }

    @Test
    @DisplayName("Test: find account by email address")
    public void testAccountFindByEmailAddress() {

        prepareAccountControllerWithThreeAccounts();

        final var actual1 = accountController.findBy("carloscuhnia@gmail.com").isPresent();
        Assertions.assertTrue(actual1);

        final var actual2 = accountController.findBy("niematakiegoadresu@gmail.com").isEmpty();
        Assertions.assertTrue(actual2);
    }


    @Test
    @DisplayName("Test: check if email address is correct")
    public void testIfEmailIsCorrect() {

        final var actual1 = accountController.isEmailAddressCorrect("a@b.c");
        Assertions.assertTrue(actual1);

        final var actual2 = accountController.isEmailAddressCorrect("ab.c");
        Assertions.assertFalse(actual2);

        final var actual3 = accountController.isEmailAddressCorrect("a@bc");
        Assertions.assertFalse(actual3);

        final var actual4 = accountController.isEmailAddressCorrect("@b.c");
        Assertions.assertFalse(actual4);

        final var actual5 = accountController.isEmailAddressCorrect("a@.c");
        Assertions.assertFalse(actual5);

        final var actual6 = accountController.isEmailAddressCorrect("a@b.");
        Assertions.assertFalse(actual6);
    }

//    @Test
//    @DisplayName("Test: plates update")
//    public void testPlatesUpdate() {
//
//        prepareAccountControllerWithThreeAccounts();
//
//        // add one plate or many plates
//        accountController.addPlates(new UserIdentifier("karollukaszkania@gmail.com"), "WJ0001", "WJ0002");
//        final var optionalAccount1 = accountController.findAccountBy(new CarPlate("WJ3369J"));
//        Assertions.assertTrue(optionalAccount1.isPresent());
//        final var actual1 = optionalAccount1.get().getUserPlates().size();
//        Assertions.assertEquals(5, actual1);
//
//        // delete plate
//        accountController.deletePlate("WJ0002");
//        final var optionalAccount2 = accountController.findAccountBy(new CarPlate("WJ0001"));
//        Assertions.assertTrue(optionalAccount2.isPresent());
//        final var actual2 = optionalAccount2.get().getUserPlates().size();
//        Assertions.assertEquals(4, actual2);
//
//        // delete all plates
//        final var optionalAccount3 = accountController.findAccountBy("carloscuhnia@gmail.com");
//        Assertions.assertTrue(optionalAccount3.isPresent());
//        final var actual3 = optionalAccount3.get().getUserPlates().size();
//        Assertions.assertEquals(2, actual3);
//
//        accountController.clearPlates(new UserIdentifier("carloscuhnia@gmail.com"));
//
//        final var optionalAccount4 = accountController.findAccountBy("carloscuhnia@gmail.com");
//        Assertions.assertTrue(optionalAccount4.isPresent());
//        final var actual4 = optionalAccount4.get().getUserPlates().size();
//        Assertions.assertEquals(0, actual4);
//    }
}