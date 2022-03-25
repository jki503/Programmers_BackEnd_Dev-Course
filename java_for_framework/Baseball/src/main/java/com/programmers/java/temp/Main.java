package com.programmers.java.temp;

import com.github.javafaker.Faker;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();

        Stream.generate(() -> faker.number().randomDigit())
                .limit(10)
                .iterator().forEachRemaining(System.out::println);
    }

}
