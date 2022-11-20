package parking.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.nobilites.parking.domain.database.car.Plate;
import pl.nobilites.parking.domain.database.parking.SlotRegister;

@SpringBootTest
class SlotRegisterRepositoryTest {

    @Autowired
    SlotRegisterRepository slotRegisterRepository;

    @Test
    public void testSave() {
        final var slotRegister0 = new SlotRegister(1, new Plate("dummy-plate"));
        final var slotRegister1 = new SlotRegister(2, new Plate("dummy-plate"));

        slotRegisterRepository.save(slotRegister0);
        slotRegisterRepository.save(slotRegister1);

        final var optionalSlotRegister = slotRegisterRepository.findById(1);

        Assertions.assertTrue(optionalSlotRegister.isPresent());
        Assertions.assertEquals(new Plate("dummy-plate"), optionalSlotRegister.get().getPlate());
    }

    @Test
    public void testSaveAndDelete() {
        final var slotRegister0 = new SlotRegister(2, new Plate("dummy-plate"));
        slotRegisterRepository.save(slotRegister0);

        final var optionalSlotRegister = slotRegisterRepository.findById(2);

        Assertions.assertTrue(optionalSlotRegister.isPresent());
        Assertions.assertEquals(new Plate("dummy-plate"), optionalSlotRegister.get().getPlate());

        slotRegisterRepository.deleteById(slotRegister0.getNumber());

        final var optionalSlotRegisterAfter = slotRegisterRepository.findById(2);
        Assertions.assertTrue(optionalSlotRegisterAfter.isEmpty());
    }

    @Test
    public void testFindByPlate() {
        final var slotRegister0 = new SlotRegister(3, new Plate("dummy-plate0"));
        final var slotRegister1 = new SlotRegister(4, new Plate("dummy-plate1"));
        final var slotRegister2 = new SlotRegister(5, new Plate("dummy-plate2"));
        slotRegisterRepository.save(slotRegister0);
        slotRegisterRepository.save(slotRegister1);
        slotRegisterRepository.save(slotRegister2);

        final var optionalSlotRegister = slotRegisterRepository.findByPlate(new Plate("dummy-plate1"));
        Assertions.assertTrue(optionalSlotRegister.isPresent());
    }

    @Test
    public void testIsParked() {
        final var slotRegister0 = new SlotRegister(3, new Plate("dummy-plate0"));
        final var slotRegister1 = new SlotRegister(4, new Plate("dummy-plate1"));
        final var slotRegister2 = new SlotRegister(5, new Plate("dummy-plate2"));
        slotRegisterRepository.save(slotRegister0);
        slotRegisterRepository.save(slotRegister1);
        slotRegisterRepository.save(slotRegister2);

        final var isParked = slotRegisterRepository.existsByPlate(new Plate("dummy-plate1"));
        Assertions.assertTrue(isParked);

        final var isNotParked = slotRegisterRepository.existsByPlate(new Plate("dummy-plate-not-parked"));
        Assertions.assertFalse(isNotParked);
    }

//    @Test
//    public void testSaveAndSaveWithNull() {
//        final var slotRegister0 = new SlotRegister(3, "dummy-plate");
//
//        final var saveSlotRegister = slotRegisterRepository.save(slotRegister0);
//
//        final var optionalSlotRegister = slotRegisterRepository.findById(3);
//
//        Assertions.assertTrue(optionalSlotRegister.isPresent());
//        Assertions.assertEquals("dummy-plate", optionalSlotRegister.get().getPlate());
//
//        saveSlotRegister.setPlate("plate0");
//        slotRegisterRepository.save(saveSlotRegister);
//
//        final var optionalSlotRegisterAfter = slotRegisterRepository.findById(3);
//        Assertions.assertTrue(optionalSlotRegisterAfter.isPresent());
//        Assertions.assertNull(optionalSlotRegister.get().getPlate());
//    }
}