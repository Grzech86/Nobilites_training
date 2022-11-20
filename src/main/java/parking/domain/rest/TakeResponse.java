package parking.domain.rest;

public sealed interface TakeResponse {
    record Taken(int number) implements TakeResponse {}
    record Error(String message) implements TakeResponse {}
}
