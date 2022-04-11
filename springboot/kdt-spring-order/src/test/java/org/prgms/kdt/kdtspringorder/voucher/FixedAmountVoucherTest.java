package org.prgms.kdt.kdtspringorder.voucher;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FixedAmountVoucherTest {

    private static final Logger logger = LoggerFactory.getLogger(FixedAmountVoucherTest.class);

    @BeforeAll
    static void setUp(){
        logger.info("@BeforeAll - 단 한 번 실행");
    }

    @BeforeEach
    void init(){
         logger.info("@BeforeEach - 매 테스트 마다 실행");
    }

    @Test
    @DisplayName("기본적인 assertEquals 테스트")
    void assertEqualsTest() {
        assertEquals(2,1 + 1);
    }

    @Test
    @DisplayName("주어진 금액만큼 할인을 해야한다.")
    void testDiscount() {
        var sut = new FixedAmountVoucher(UUID.randomUUID(), 100);
        assertEquals(900, sut.discount(1000));
    }

    @Test
    @DisplayName("할인 금액은 마이너스가 될 수 없다.")
    @Disabled // 테스트 에러를 발견할 수 없다면 임시로 테스트 대상에서 제외
    void testWithMinus() {
        assertThrows(IllegalArgumentException.class, () -> new FixedAmountVoucher(UUID.randomUUID(), -100));
    }

    @Test
    @DisplayName("할인 금액은 마이너스가 될 수 없다.")
    void testMinusDiscountedAmount(){
        var sut = new FixedAmountVoucher(UUID.randomUUID(), 1000);
        assertEquals(0,sut.discount(900));
    }

    @Test
    @DisplayName("유효한 할인 금액으로만 생성할 수 있다.")
    void testVoucherCreation(){
        assertAll("FixedAmountVoucher creation",
                () -> assertThrows(IllegalArgumentException.class, () -> new FixedAmountVoucher(UUID.randomUUID(), 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new FixedAmountVoucher(UUID.randomUUID(), -100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new FixedAmountVoucher(UUID.randomUUID(), 1000000))
                );

        var sut = new FixedAmountVoucher(UUID.randomUUID(), 1000);
    }

}