package org.prgms.kdt.kdtspringorder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgms.kdt.kdtspringorder.order.OrderItem;
import org.prgms.kdt.kdtspringorder.order.OrderService;
import org.prgms.kdt.kdtspringorder.order.OrderStatus;
import org.prgms.kdt.kdtspringorder.voucher.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
@SpringJUnitConfig
@ActiveProfiles("local")
public class KdtSpringContextTests {

    @Configuration
    @ComponentScan(
            basePackages = {"org.prgms.kdt.kdtspringorder.voucher", "org.prgms.kdt.kdtspringorder.order", "org.prgms.kdt.kdtspringorder.configuration"}
    )
    static class Config{
        VoucherRepository voucherRepository(){
            return new VoucherRepository() {
                @Override
                public Optional<Voucher> findById(UUID voucherID) {
                    return Optional.empty();
                }

                @Override
                public Voucher insert(Voucher voucher) {
                    return null;
                }
            };
        }
    }

    @Autowired
    ApplicationContext context;

    @Autowired
    OrderService orderService;

    @Autowired
    VoucherRepository voucherRepository;

    @Test
    @DisplayName("applicationContext가 생성 되어야한다.")
    public void testApplicationContext(){
        assertThat(context,notNullValue());
    }

    @Test
    @Disabled
    @DisplayName("voucerRepository가 빈으로 등록 되어야 한다.")
    public void testVoucherRepositoryCreation(){
        var bean = context.getBean(VoucherRepository.class);
        assertThat(bean,notNullValue());
    }

    @Test
    @DisplayName("orderService를 사용해서 주문 생성")
    public void testOrderService(){
//        Given
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(),100);
        voucherRepository.insert(fixedAmountVoucher);

//        When
        var order = orderService.createOrder(
                UUID.randomUUID(),
                List.of(new OrderItem(UUID.randomUUID(),200,1)),
                fixedAmountVoucher.getVoucherId());

//        Then
        assertThat(order.totalAmount(), is(100L));
        assertThat(order.getVoucher().isEmpty(), is(false));
       assertThat(order.getVoucher().get().getVoucherId(), is(fixedAmountVoucher.getVoucherId()));
        assertThat(order.getOrderStatus(), is(OrderStatus.ACCEPTED));

    }
}
