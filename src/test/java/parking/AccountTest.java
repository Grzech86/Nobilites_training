package parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.nobilites.parking.domain.database.user.Account;
import pl.nobilites.parking.domain.database.user.Identifier;

class AccountTest {

    Account account;
    String[] plates;

    @BeforeEach
    public void prepareTestSettings() {
        account = new Account(
                new Identifier("karollukaszkania@gmail.com"),
                "Karol",
                "Kania",
                "karollukaszkania@gmail.com",
                607111014
        );

        plates = new String[]{"WJ3369J", "WJ3370J", "WJ3371J"};
    }

    @Test
    @DisplayName("Test: account creation")
    public void testAccountCreation() {
        account = new Account(
                new Identifier("testforkarollukaszkania@gmail.com"),
                "K",
                "K",
                "testforkarollukaszkania@gmail.com",
                6
        );

        final var expected = "testforkarollukaszkania@gmail.com";
        final var actual = account.getIdentifier().getUserId();

        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    @DisplayName("Test: update all car plates")
//    public void testAccountPlatesUpdate() {
//      account.updateAllCarPlates("KR3369J", "KR3370J", "KR3371J");
//
//        final var expected = new HashSet<>(Set.of(new CarPlate("KR3369J"), new CarPlate("KR3370J"), new CarPlate("KR3371J")));
//        final var actual = account.getUserPlates();
//
//        Assertions.assertEquals(expected, actual);
//    }

//    @Test
//    @DisplayName("Test: clear all car plates")
//    public void testClearAccountPlates() {
//
//        Assertions.assertNotEquals(0, account.getUserPlates().size());
//
//        account.clearAllCarPlates();
//        Assertions.assertEquals(0, account.getUserPlates().size());
//    }

//    @Test
//    @DisplayName("Test: add new car plates")
//    public void testAddNewPlates() {
//        final var actual1 = account.getUserPlates().contains(new CarPlate("PL4TE5"));
//        Assertions.assertFalse(actual1);
//
//        account.addCarPlate("PL4TE5");
//        final var actual2 = account.getUserPlates().contains(new CarPlate("PL4TE5"));
//        Assertions.assertTrue(actual2);
//    }
//    @Test
//    @DisplayName("Test: delete car plates")
//    public void testDeletePlates() {
//
//        account.addCarPlate("PL4TE5");
//        final var actual1 = account.getUserPlates().contains(new CarPlate("PL4TE5"));
//        Assertions.assertTrue(actual1);
//
//        account.deleteCarPlate("PL4TE5");
//        final var actual2 = account.getUserPlates().contains(new CarPlate("PL4TE5"));
//        Assertions.assertFalse(actual2);
//    }
}