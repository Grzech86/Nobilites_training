package parking.domain.rest;

public sealed interface ReservationDetails {
    record Ok(int number) implements ReservationDetails {}
    record Error(String message) implements ReservationDetails {}
    record Release(String message, Long duration) implements ReservationDetails {}
}
