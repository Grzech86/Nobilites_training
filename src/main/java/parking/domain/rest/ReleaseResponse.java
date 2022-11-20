package parking.domain.rest;

public sealed interface ReleaseResponse {

    record Release(int number) implements ReleaseResponse {}
    record Error(String message) implements ReleaseResponse {}
}
