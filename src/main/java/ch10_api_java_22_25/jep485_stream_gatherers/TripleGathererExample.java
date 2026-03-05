package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;


/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class TripleGathererExample {
    void main()
    {
        IO.println(Stream.of("This", "is", "a", "test").
                          gather(triple()).
                          toList());

        IO.println(Stream.of(1, 2, 3, 4, 5).
                          gather(triple()).
                          toList());

        IO.println(Stream.of("This", "is", "a", "test").
                gather(every2ndTriple()).
                toList());

        IO.println(Stream.of(1, 2, 3, 4, 5).
                gather(every2ndTriple()).
                toList());
    }

    <T> Gatherer<T, Void, T> triple_lambda() {
        return new Gatherer<>() {
            @Override
            public Integrator<Void, T, T> integrator() {
                return (_, element, downstream) ->
                        downstream.push(element) && downstream.push(element) &&
                                downstream.push(element);
            }
        };
    }

<T> Gatherer<T, Void, T> triple_Old() {
    return new Gatherer<T, Void, T>() {
        @Override
        public Integrator<Void, T, T> integrator() {
            return new Integrator<Void, T, T>() {
                @Override
                public boolean integrate(Void unusedState, T element,
                                         Downstream<? super T> downstream) {
                    downstream.push(element);
                    downstream.push(element);
                    downstream.push(element);

                    return true;
                }
            };
        }
    };
}

    <T> Gatherer<T, Void, T> triple() {
        return new Gatherer<T, Void, T>() {
            @Override
            public Integrator<Void, T, T> integrator() {
                return new Integrator<Void, T, T>() {
                    @Override
                    public boolean integrate(Void unusedState, T element,
                                             Downstream<? super T> downstream) {
                        boolean pushed = downstream.push(element);
                        if (pushed)
                            pushed = downstream.push(element);

                        if (pushed)
                            pushed = downstream.push(element);

                        return pushed;
                    }
                };
            }
        };
    }


    <T> Gatherer<T, AtomicInteger, T> every2ndTriple() {
        return new Gatherer<T, AtomicInteger, T>() {
            @Override
            public Supplier<AtomicInteger> initializer() {
                return () -> new AtomicInteger(0);
            }

            @Override
            public Integrator<AtomicInteger, T, T> integrator() {
                return new Integrator<AtomicInteger, T, T>() {
                    @Override
                    public boolean integrate(AtomicInteger state, T element,
                                             Downstream<? super T> downstream) {
                        boolean pushed = true;
                        if (state.getAndIncrement() % 2 == 0) {
                            pushed = downstream.push(element);
                            if (pushed)
                                pushed = downstream.push(element);

                            if (pushed)
                                pushed = downstream.push(element);
                        }
                        return pushed;
                    }
                };
            }
        };
    }

    <T> Gatherer<T, AtomicInteger, T> every2ndTripleAgain_Old() {
        return Gatherer.ofSequential(
                AtomicInteger::new,
                (state, element, downstream) ->
                {
                    boolean pushed = true;
                    if (state.getAndIncrement() % 2 == 0) {
                        pushed = downstream.push(element);
                        if (pushed)
                            pushed = downstream.push(element);

                        if (pushed)
                            pushed = downstream.push(element);
                    }
                    return pushed;
                }
        );
    }

    public static <T> Gatherer<T, AtomicInteger, T> every2ndTripleAgain() {
        return Gatherer.ofSequential(
                AtomicInteger::new,
                (state, element, downstream) ->
                {
                    if (state.getAndIncrement() % 2 != 0)
                        return true;

                    return downstream.push(element)
                            && downstream.push(element)
                            && downstream.push(element);
                }
        );
    }
}
