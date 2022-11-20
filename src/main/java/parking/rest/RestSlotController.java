package parking.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nobilites.parking.controllers.Accountant;
import pl.nobilites.parking.controllers.PlateController;
import pl.nobilites.parking.controllers.SlotController;
import pl.nobilites.parking.domain.rest.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RestSlotController  {
    private final SlotController slotController;
    private final Accountant accountant;

    private final PlateController plateController;

    RestSlotController(SlotController slotController, Accountant accountant, PlateController plateController) {
        this.slotController = slotController;
        this.accountant = accountant;
        this.plateController = plateController;
    }

    @GetMapping("/slots/all")
    public Iterable<Integer> getAll() {
        return slotController.all();
    }

    @GetMapping("/slots/available")
    public List<Integer> getAvailable() {
        return slotController.available();
    }

    @GetMapping("/slots/occupied")
    public List<Integer> getOccupied() {
        return slotController.occupied();
    }

    @PostMapping("/slots/take")
    public TakeResponse take(@RequestBody TakeRequest takeRequest) {
        final var ok = slotController.take(takeRequest.number(), takeRequest.plate());
        return ok ? new TakeResponse.Taken(takeRequest.number()) : new TakeResponse.Error("slot already taken");
    }
    @PostMapping("/slots/release")
    public ReleaseResponse release(@RequestBody ReleaseRequest releaseRequest) {
        final var ok = slotController.release(releaseRequest.number());
        return ok.isPresent() ? new ReleaseResponse.Release(releaseRequest.number()) : new ReleaseResponse.Error("slot already released");
    }

    @PostMapping("/slots/takeSlot")
    public PlateResponse confirmTakeSlot(@RequestBody PlateRequest slotPlateRequest){
        final var registered =  plateController.isRegistered(slotPlateRequest.plate());
        if (!registered) {
            return new PlateResponse.Error("Car plate not registered.");
        }

        final var result = slotController.takeSlot(slotPlateRequest.plate());
        if (result instanceof ReservationDetails.Ok ok) {
            return new PlateResponse.ConfirmationTake(ok.number());
        } else if (result instanceof ReservationDetails.Error error) {
            return new PlateResponse.Error(error.message());
        } else {
            return new PlateResponse.Error("internal server error");
        }
    }

    @PostMapping("/slots/releaseSlot")
    public PlateResponse confirmReleaseSlot(@RequestBody PlateRequest slotPlateRequest){
        final var registered = plateController.isRegistered(slotPlateRequest.plate());
        if (!registered) {
            return new PlateResponse.Error("Car plate not registered.");
        }

        final var result = slotController.releaseSlot(slotPlateRequest.plate());
        if (result instanceof ReservationDetails.Release ok) {
            final var cashInPln = BigDecimal.valueOf(ok.duration() * 25);
            final var optionalUserIdentifier = plateController.find(slotPlateRequest.plate()).stream().findFirst();
            optionalUserIdentifier.ifPresent(userIdentifier -> accountant.credit(userIdentifier, cashInPln));

            return new PlateResponse.ConfirmationRelease(ok.message());
        } else if (result instanceof ReservationDetails.Error error) {
            return new PlateResponse.Error(error.message());
        } else {
            return new PlateResponse.Error("internal server error");
        }
    }

    public void reset() {
        slotController.reset();
    }
}
