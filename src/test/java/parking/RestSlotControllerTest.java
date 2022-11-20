package parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.nobilites.parking.domain.database.car.Plate;
import pl.nobilites.parking.domain.rest.*;
import pl.nobilites.parking.domain.rest.TakeResponse;
import pl.nobilites.parking.rest.RestAccountController;
import pl.nobilites.parking.rest.RestSlotController;

import java.util.List;

@SpringBootTest
class RestSlotControllerTest {

    @Autowired
    RestSlotController restSlotController;

    @Autowired
    RestAccountController restAccountController;

    @BeforeEach
    public void prepare() {
        restAccountController.create(
                new AccountCreateRequest("Ala", "Nowak", "ala@ma.kota", 123456789, "WI 1234A"));

        restAccountController.create(
                new AccountCreateRequest("Ala", "Test", "ala@ma.test", 123456789, "WE 1234A"));

        restAccountController.create(
                new AccountCreateRequest("Jan", "Kowal", "jan@ma.psa", 123789012, "WA 1234A"));

        restAccountController.create(
                new AccountCreateRequest("Ola", "Kowal", "ola@ma.rybe", 100400700, "WX 1234A"));

        restAccountController.create(
                new AccountCreateRequest("Pan", "Test", "pan@ma.test", 111111111, "dummy-plate"));

        restSlotController.reset();
    }

    @Test
    public void test() {
        final var actual = restSlotController.getAll();
        final var expected = List.of(1, 2, 3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAvailableTest() {
        final var actual = restSlotController.getAvailable();
        final var expected = List.of(1, 2, 3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAvailableTest2() {

        restSlotController.take(new TakeRequest(2, new Plate("dummy-plate")));

        final var actual = restSlotController.getAvailable();
        final var expected = List.of(1, 3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getOccupiedTest() {
        final var actual = restSlotController.getOccupied();
        final var expected = List.of();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testChangeStatusOfSlot1() {
        final var actual1 = restSlotController.getOccupied();
        final var expected1 = List.of();
        // all available
        Assertions.assertEquals(expected1, actual1);

        // take slot 1
        restSlotController.take(new TakeRequest(1, new Plate("dummy-plate")));

        final var actual3 = restSlotController.getOccupied();
        final var expected3 = List.of(1);

        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void testDoubleTakeOfSlot1() {
        final var actual1 = restSlotController.getOccupied();
        final var expected1 = List.of();
        // all available
        Assertions.assertEquals(expected1, actual1);

        // take slot 1
        final var actualTake1 = restSlotController.take(new TakeRequest(1, new Plate("dummy-plate")));
        final var actualTake2 = restSlotController.take(new TakeRequest(1, new Plate("dummy-plate")));

        Assertions.assertInstanceOf(TakeResponse.Taken.class, actualTake1);
        Assertions.assertInstanceOf(TakeResponse.Error.class, actualTake2);
    }

    @Test
    public void testDoubleFreeOfSlot1() {
        final var actual1 = restSlotController.getOccupied();
        final var expected1 = List.of();
        // all available
        Assertions.assertEquals(expected1, actual1);

        // take slot 1
        final var actualTake1 = restSlotController.take(new TakeRequest(1, new Plate("dummy-plate")));
        Assertions.assertInstanceOf(TakeResponse.Taken.class, actualTake1);

        final var actualFree1 = restSlotController.release(new ReleaseRequest(1));
        Assertions.assertInstanceOf(ReleaseResponse.Release.class, actualFree1);

        final var actualFree2 = restSlotController.release(new ReleaseRequest(1));
        Assertions.assertInstanceOf(ReleaseResponse.Error.class, actualFree2);
    }


    @Test
    public void testConfirmTakeSlot1() {
        final var carPlate = new Plate("WI 1234A");
        int numSlot = 1;

        final var actual = restSlotController.confirmTakeSlot(new PlateRequest(carPlate));
        final var expected = new PlateResponse.ConfirmationTake(numSlot);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testConfirmTakeSlot2() {

        final var actual = restSlotController.getAvailable();
        final var expected = List.of(1, 2, 3);
        Assertions.assertEquals(expected, actual);

        // take slot
        restSlotController.confirmTakeSlot(new PlateRequest(new Plate("dummy-plate")));

        final var actual1 = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        final var expected1 = new PlateResponse.ConfirmationTake(2);
        Assertions.assertEquals(expected1, actual1);


    }


    @Test
    public void testConfirmTakeSlot3() {
        final var actual = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        final var expected = new PlateResponse.ConfirmationTake(1);
        Assertions.assertEquals(expected, actual);

        final var actual2 = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WA 1234A")));
        final var expected2 = new PlateResponse.ConfirmationTake(2);
        Assertions.assertEquals(expected2, actual2);

        final var actual3 = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WE 1234A")));
        final var expected3 = new PlateResponse.ConfirmationTake(3);
        Assertions.assertEquals(expected3, actual3);

        final var actual4 = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WX 1234A")));
        Assertions.assertInstanceOf(PlateResponse.Error.class, actual4);


    }


    @Test
    public void testConfirmTakeSlot4() {
        final var actual = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        final var expected = new PlateResponse.ConfirmationTake(1);
        Assertions.assertEquals(expected, actual);

        final var actual3 = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        Assertions.assertInstanceOf(PlateResponse.Error.class, actual3);


    }

    @Test
    public void testConfirmReleaseSlot1() {
        final var actual = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        final var expected = new PlateResponse.ConfirmationTake(1);
        Assertions.assertEquals(expected, actual);

        final var actual1 = restSlotController.confirmReleaseSlot(new PlateRequest(new Plate("WI 1234A")));
        Assertions.assertInstanceOf(PlateResponse.ConfirmationRelease.class, actual1);

        final var actual2 = restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        final var expected2 = new PlateResponse.ConfirmationTake(1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void testConfirmReleaseSlot2() {
        final var actual = restSlotController.confirmReleaseSlot(new PlateRequest(new Plate("WI 1234A")));
        Assertions.assertInstanceOf(PlateResponse.Error.class, actual);
    }

    @Test
    public void testConfirmReleaseSlot3() {

        final var actual = restSlotController.getAvailable();
        final var expected = List.of(1, 2, 3);
        Assertions.assertEquals(expected, actual);

        // take slots
        restSlotController.confirmTakeSlot(new PlateRequest(new Plate("dummy-plate")));
        restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));

        final var actual1 = restSlotController.getAvailable();
        final var expected1 = List.of(3);
        Assertions.assertEquals(expected1, actual1);

        //release slot
        restSlotController.confirmReleaseSlot(new PlateRequest(new Plate("dummy-plate")));

        final var actual2 = restSlotController.getAvailable();
        final var expected2 = List.of(1, 3);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void testTakeAndReleaseAndCharge() {
        restSlotController.confirmTakeSlot(new PlateRequest(new Plate("WI 1234A")));
        restSlotController.confirmReleaseSlot(new PlateRequest(new Plate("dummy-plate")));

    }


    WebTestClient client;

    @BeforeEach
    void setUp(ApplicationContext context) {
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void testRestGetAll() {
        client
                .get()
                .uri("/slots/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}