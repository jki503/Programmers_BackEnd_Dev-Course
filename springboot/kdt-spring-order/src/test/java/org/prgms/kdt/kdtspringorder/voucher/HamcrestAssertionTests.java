package org.prgms.kdt.kdtspringorder.voucher;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HamcrestAssertionTests {

    @Test
    @DisplayName("여러 hamcrest matcher 테스트")
    void hamcrestTest(){
        assertThat(1+1, equalTo(2));
        assertThat(1+1, is(2));
        assertThat(1+1, anyOf(is(1),is(2)));

        assertNotEquals(1, 1+1);
        assertThat (1+1, not(equalTo(1)));
    }

    @Test
    @DisplayName("컬렉션에 대한 matcher 테스트")
    void hamcrestListMatcher(){
        var prices = List.of(1,2,3);
        assertThat(prices,hasSize(3));
        assertThat(prices,everyItem(greaterThan(0)));
        // containsInAnyOrder : 순서가 중요하지 않으면
        assertThat(prices,contains(1,2,3));
        assertThat(prices,hasItem(greaterThanOrEqualTo(2)));
    }

}
