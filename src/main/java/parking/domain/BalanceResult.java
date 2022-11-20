package parking.domain;

import java.math.BigDecimal;
public sealed interface BalanceResult {
    record Ok(BigDecimal balance) implements BalanceResult {}
    record Error(String message) implements BalanceResult {}
}

