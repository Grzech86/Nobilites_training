package parking.domain.rest;

import java.math.BigDecimal;

public sealed interface BalanceResponse {
    record Ok(BigDecimal balance) implements BalanceResponse {}
    record Error(String message) implements BalanceResponse {}
}
