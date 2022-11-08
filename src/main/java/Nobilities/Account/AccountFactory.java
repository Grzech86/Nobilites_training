package Nobilities.Account;

public class AccountFactory {
    static Accountable create(String s) {
        switch (s) {
            case "PLN": return new AccountPLNOther();
            case "USD": return new AccountUSD();
        }
        throw new UnsupportedOperationException();
    }

    static private final class AccountPLN implements Accountable {}
    static private final class AccountPLNOther implements Accountable {}
    static private final class AccountUSD implements Accountable {}
}
