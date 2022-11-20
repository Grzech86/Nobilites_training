package parking.domain.rest;

public sealed interface PlatesModificationResponse {

    record PlatesModificationSucceed(String message) implements PlatesModificationResponse {}

    record PlatesModificationFail(String message) implements PlatesModificationResponse {}
}
