# Board Game Gatherings

As you might have noticed, at First8 we love boardgames! We regularly organise board game evenings, just for fun. 
Keeping score of your wins and losses can be a bit of a hassle, so we would like to automate this process.

Luckily, in Java 22 there is a new intermediate stream operation available as a preview. This preview is described in 
JEP 473, with `Stream::gather(Gatherer)`. A gatherer can transform elements and track previously seen elements.
In JEP 473 also convenient methods for creating a Gatherer are introduced in java.util.stream.Gatherers. The one we
will be using is `Gatherers.scan()`.

Read more at `https://openjdk.org/jeps/473`

## Assignment

Your task is to complete the `Scanner` in such a way that it can compute cumulative totals.

You are given a stream of `BoardGameEvening` objects. Each `BoardGameEvening` object has a date and a score.
And you are given an empty `scanner()` function, that works inside a stream as a gatherer: it is your job to fill it in!

The expected output is again a stream, but this time of cumulative totals. E.g. if you scored respectively 3, 2 and 4 points at three evenings, your cumulative scores would be `3`, `3+2` and `3+2+4`, resulting in a stream of `[3,5,9]`. 

This is the code that you will be working with:
<pre>
public List<Integer> scoreHistory(List<BoardGameEvening> evenings) {
    return evenings.stream()
            .sorted(Comparator.comparing(BoardGameEvening::evening))
            .gather(Gatherers.scan(() -> 0, scanner()))
            .toList();
}

private BiFunction<Integer, BoardGameEvening, Integer> scanner() {
    // TODO complete this function
    return null;
}
</pre>

The BoardGameEvening class is defined as follows:
<pre>
public record BoardGameEvening(LocalDate evening, int score) {}
</pre>
