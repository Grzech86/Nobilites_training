package parking.controllers;

import org.springframework.stereotype.Controller;
import parking.domain.BalanceResult;
import parking.domain.OperationType;
import parking.domain.rest.BalanceRequest;
import parking.repositories.BalanceRepository;
import parking.domain.BalanceResult;
import parking.domain.CreditResult;
import parking.domain.DebitResult;
import parking.domain.OperationType;
import parking.domain.database.user.Identifier;
import parking.domain.database.user.Operation;
import parking.domain.rest.BalanceRequest;
import parking.repositories.BalanceRepository;

import java.math.BigDecimal;

@Controller
public class Accountant {
    private final BalanceRepository balanceRepository;
    private final AccountController accountController;

    public Accountant(BalanceRepository balanceRepository, AccountController accountController) {
        this.balanceRepository = balanceRepository;
        this.accountController = accountController;
    }

    public BalanceResult getBalanceInPLN(BalanceRequest balanceRequest) {
        final var operations = balanceRepository.findOperationByIdentifier(balanceRequest.identifier());

        if (operations.isEmpty() && accountController.accountRepository.existsById(balanceRequest.identifier())) {
            return new BalanceResult.Ok(BigDecimal.valueOf(0));
        } else if (operations.isEmpty() && !accountController.accountRepository.existsById(balanceRequest.identifier())) {
            return new BalanceResult.Error("User not found");
        } else {
            BigDecimal balance = operations.stream()
                    .reduce(new BigDecimal(0),
                            (a, b) -> OperationType.DEBIT.equals(b.getOperationType()) ? a.add(b.getAmount()) : a.subtract(b.getAmount()),
                            BigDecimal::add);
            return new BalanceResult.Ok(balance);
//
//            Optional<BigDecimal> debit = operations.stream()
//                    .filter(operation -> OperationType.DEBIT.equals(operation.getOperationType()))
//                    .map(Operation::getAmount)
//                    .reduce(BigDecimal::add);
//            Optional<BigDecimal> credit = operations.stream()
//                    .filter(operation -> OperationType.CREDIT.equals(operation.getOperationType()))
//                    .map(Operation::getAmount)
//                    .reduce(BigDecimal::subtract);
//
//            for (Operation localOperation : operations) {
//                if (localOperation.getOperationType().equals(OperationType.DEBIT)) {
//                    localBalance = localBalance.add(localOperation.getAmount());
//                } else {
//                    localBalance = localBalance.subtract(localOperation.getAmount());
//                }
//            }
        }
    }


    public DebitResult debit(Identifier identifier, BigDecimal amount) {
        try {
            final var currentOperation = new Operation(null, identifier, OperationType.DEBIT, amount);
            balanceRepository.save(currentOperation);
            return new DebitResult.Success();
        } catch (UnsupportedOperationException e) {
            return new DebitResult.SomethingWentWrong();
        }
    }

    public CreditResult credit(Identifier identifier, BigDecimal amount) {
        try {
            var balanceResult = getBalanceInPLN(new BalanceRequest(identifier));
            if (balanceResult instanceof BalanceResult.Ok) {
                if (((BalanceResult.Ok) balanceResult).balance().compareTo(amount) >= 0) {    // jeśli balance >= amount
                    final var currentOperation = new Operation(null, identifier, OperationType.CREDIT, amount);
                    balanceRepository.save(currentOperation);
                } else {
                    return new CreditResult.Error("Insufficient funds in the account");
                }
            } else {
                return new CreditResult.Error("No account found");
            }
            return new CreditResult.Success();
        } catch (UnsupportedOperationException e) {
            return new CreditResult.SomethingWentWrong();
        }
    }
}
