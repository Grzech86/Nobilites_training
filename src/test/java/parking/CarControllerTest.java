package parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import parking.controllers.PlateController;
import parking.domain.database.car.Plate;
import parking.domain.database.user.Identifier;
import parking.repositories.CarRepository;

@SpringBootTest
public class CarControllerTest {
    @Autowired
    PlateController plateController;

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    public void preparePlateControllerWithThreePlates() {
        carRepository.deleteAll();
        plateController.add(new Identifier("karollukaszkania@gmail.com"), new Plate("WJ3369J"));
        plateController.add(new Identifier("carloscuhnia@gmail.com"), new Plate("GD3369J"));
        plateController.add(new Identifier("carloscuhnia@gmail.com"), new Plate("EL3369J"));
    }


    @Test
    @DisplayName("Test: check isCarPlateRegistered true/false")
    public void testIsPlateRegistered() {
        final var actual1 = plateController.isRegistered(new Plate("WJ3369J"));
        Assertions.assertTrue(actual1);

        final var actual2 = plateController.isRegistered(new Plate("NOTR3G"));
        Assertions.assertFalse(actual2);
    }


    @Test
    @DisplayName("Test: check delete CarPlate")
    public void testDeleteCarPlate() {
        final var actual1 = plateController.find(new Plate("WJ3369J")).contains(new Identifier("karollukaszkania@gmail.com"));
        Assertions.assertTrue(actual1);

        plateController.deleteCarPlate(new Plate("WJ3369J"));

        final var actual2 = plateController.find(new Plate("WJ3369J")).contains(new Identifier("karollukaszkania@gmail.com"));
        Assertions.assertFalse(actual2);
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
