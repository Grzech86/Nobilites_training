package Nobilities.ex57;

import java.util.ArrayList;
import java.util.Collection;

class LottoDraw implements Drawable {
    private final int no;
    private final String date;
    final Collection<Integer> numbers;

    private LottoDraw(int no, String date, Collection<Integer> numbers) {
        this.no = no;
        this.date = date;
        this.numbers = numbers;
    }

    public static LottoDraw decode(String line) {
        final var segments = line.split(" ");
        final var numbers = segments[2].split(",");

        final var listOfNumbers = new ArrayList<Integer>();

        for (final var number : numbers) {
            listOfNumbers.add(Integer.valueOf(number));
        }

        return new LottoDraw(Integer.valueOf(segments[0].substring(0, segments[0].length() - 1)), segments[1], listOfNumbers);
    }

    @Override
    public boolean contains(Collection<Integer> subnumbers) {
        return this.numbers.containsAll(subnumbers);
    }
}

interface Drawable {
    boolean contains(Collection<Integer> subnumbers);
}