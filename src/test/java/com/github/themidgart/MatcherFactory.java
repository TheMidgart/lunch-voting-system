package com.github.themidgart;

import java.util.function.BiConsumer;

public class MatcherFactory {

    public static <T> Matcher<T> usingAssertions(Class<T> clazz, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
        return new Matcher<>(clazz, assertion, iterableAssertion);
    }

    public static class Matcher<T> {
        private final Class<T> clazz;
        private final BiConsumer<T, T> assertion;
        private final BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion;

        private Matcher(Class<T> clazz, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
            this.clazz = clazz;
            this.assertion = assertion;
            this.iterableAssertion = iterableAssertion;
        }

        public void assertMatch(T actual, T expected) {
            assertion.accept(actual, expected);
        }


        public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
            iterableAssertion.accept(actual, expected);
        }
    }

}