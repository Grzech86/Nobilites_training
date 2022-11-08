package Nobilities.ex36ToDo;

public sealed interface ID {
    static ID create(String input) {
        if (input.matches("[a-zA-z]+[a-zA-Z0-9]+")) {
            return new Valid(input);
        } else {
            return new Invalid();
        }
    }

    record Valid(String id) implements ID {
    }

    record Invalid() implements ID {}
}