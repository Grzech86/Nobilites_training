package ex41;

public sealed interface SearchOperation {
    record OneParametr (String a) implements SearchOperation {
    }
    record MoreParametr (String a, String b, String c ) implements SearchOperation {
    }


}
