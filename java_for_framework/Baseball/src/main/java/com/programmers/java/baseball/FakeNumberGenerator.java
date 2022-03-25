package com.programmers.java.baseball;

import com.github.javafaker.Faker;
import com.programmers.java.baseball.io.NumberGenerator;
import com.programmers.java.baseball.model.Numbers;

import java.util.stream.Stream;

public class FakeNumberGenerator implements NumberGenerator {

    private final Faker faker = new Faker();

    @Override
    public Numbers generate(int count) {
        return new Numbers(
                Stream.generate(() -> faker.number().randomDigitNotZero())
                        .distinct()
                        .limit(count)
                        .toArray(Integer[]::new)
        );
    }


}
