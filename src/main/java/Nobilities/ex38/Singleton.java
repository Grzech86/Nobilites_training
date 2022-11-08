package Nobilities.ex38;

final class Singleton {
    private Singleton() {}

    private static Singleton instance;

    public static Singleton create() {
        if (instance == null) {
            final var singleton = new Singleton();
            instance = singleton;
        }
        return instance;
    }
}
