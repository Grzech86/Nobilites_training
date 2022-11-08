package Nobilities.ex38;

public class Main {
    public static void main(String[] args) {
        final var s1 = Singleton.create();
        final var s2 = Singleton.create();

        System.out.println("s1: " + s1 + ", s2: " + s2);
    }
}