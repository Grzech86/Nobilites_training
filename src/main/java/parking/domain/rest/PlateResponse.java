package parking.domain.rest;

public sealed interface PlateResponse {

    record ConfirmationTake(Integer number) implements PlateResponse {}
    record Error(String message) implements PlateResponse {}
    record ConfirmationRelease(String message) implements PlateResponse {}
}


