package parking.controllers;

import org.springframework.stereotype.Controller;
import pl.nobilites.parking.domain.*;
import pl.nobilites.parking.domain.database.car.Plate;
import pl.nobilites.parking.domain.database.parking.ParkingStatus;
import pl.nobilites.parking.domain.database.parking.SlotRegister;
import pl.nobilites.parking.domain.rest.ReservationDetails;
import pl.nobilites.parking.repositories.ParkingStatusRepository;
import pl.nobilites.parking.repositories.SlotRegisterRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Controller
public class SlotController {
    private final SlotRegisterRepository slotRegisterRepository;
    private final ParkingStatusRepository parkingStatusRepository;

    public SlotController(SlotRegisterRepository slotRegisterRepository, ParkingStatusRepository parkingStatusRepository) {
        this.slotRegisterRepository = slotRegisterRepository;
        this.parkingStatusRepository = parkingStatusRepository;

        reset();
    }

    public Iterable<Integer> all() {
        return StreamSupport
                .stream(parkingStatusRepository.findAll().spliterator(), false)
                .map(ParkingStatus::getNumber)
                .toList();
    }

    public List<Integer> occupied() {
        return StreamSupport
                .stream(parkingStatusRepository.findBySlotStatus(SlotStatus.OCCUPIED).spliterator(), false)
                .map(ParkingStatus::getNumber)
                .toList();
    }

    public List<Integer> available() {
        return StreamSupport
                .stream(parkingStatusRepository.findBySlotStatus(SlotStatus.AVAILABLE).spliterator(), false)
                .map(ParkingStatus::getNumber)
                .toList();
    }

    public boolean take(int number, Plate plate) {
        if (parkingStatusRepository.existsByNumberAndSlotStatus(number, SlotStatus.OCCUPIED)) {
            return false;
        }

        final var optionalParkingStatus = parkingStatusRepository.findById(number);
        final var parkingStatus = optionalParkingStatus.get();

        parkingStatus.setSlotStatus(SlotStatus.OCCUPIED);
        parkingStatus.setTimestamp(Instant.now());
        parkingStatusRepository.save(parkingStatus);

        slotRegisterRepository.save(new SlotRegister(number, plate));
        return true;
    }

    public Optional<Long> release(int number) {
        final var optionalParkingStatus = parkingStatusRepository.findById(number);
        if (optionalParkingStatus.isEmpty()) {
            return Optional.empty();
        }

        final var parkingStatus = optionalParkingStatus.get();
        if (SlotStatus.AVAILABLE.equals(parkingStatus.getSlotStatus())) {
            return Optional.empty();
        }

        final var previousTimestamp = parkingStatus.getTimestamp();
        final var now = Instant.now();
        parkingStatus.setSlotStatus(SlotStatus.AVAILABLE);
        parkingStatus.setTimestamp(now);
        parkingStatusRepository.save(parkingStatus);
        slotRegisterRepository.deleteById(number);

        final var duration = now.getEpochSecond() - previousTimestamp.getEpochSecond();
        final var durationInHours = (duration + (3600 - 1)) / 3600;

        return Optional.of(durationInHours);
    }


    public ReservationDetails takeSlot(Plate plate) {

        //check if car plate is parked
        final var parked = slotRegisterRepository.existsByPlate(plate);

        if (parked) {
            return new ReservationDetails.Error("Car plate is already parked.");
        } else {

            // list available slots
            final var availableSlots = this.available();
            if (availableSlots.isEmpty()) {
                return new ReservationDetails.Error("All slots is occupied.");
            }
            // get first available slot from the list
            final var firstAvailableSlot = availableSlots.get(0);
            // take the slot
            this.take(firstAvailableSlot, plate);

            return new ReservationDetails.Ok(firstAvailableSlot);
        }

    }


    public ReservationDetails releaseSlot(Plate plate) {
        //plates.put(number, carPlate);
        final var optionalSlotRegister = slotRegisterRepository.findByPlate(plate);

        if (optionalSlotRegister.isEmpty()) {
            return new ReservationDetails.Error("Car plate is not parked.");
        }

        // get number occupied slot
        final var numReleaseSlot = optionalSlotRegister.get().getNumber();

        final var optionalDuration = optionalSlotRegister.flatMap(slotRegister -> release(slotRegister.getNumber()));

        final var duration = optionalDuration.orElse(0L);
        return new ReservationDetails.Release("Slot number " + numReleaseSlot + " is released.", duration);
    }

    public void reset() {
        parkingStatusRepository.deleteAll();
        slotRegisterRepository.deleteAll();

        final var slots = List.of(
                new Slot(1, false, false),
                new Slot(2, true, false),
                new Slot(3, false, true)
        );

        final var now = Instant.now();
        for (var slot : slots) {
            parkingStatusRepository.save(new ParkingStatus(slot.number(), SlotStatus.AVAILABLE, now));
        }
    }
}
