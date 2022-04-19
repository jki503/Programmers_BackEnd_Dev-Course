package org.prgms.kdt.kdtspringorder.aop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgms.kdt.kdtspringorder.order.OrderItem;
import org.prgms.kdt.kdtspringorder.order.OrderService;
import org.prgms.kdt.kdtspringorder.order.OrderStatus;
import org.prgms.kdt.kdtspringorder.voucher.FixedAmountVoucher;
import org.prgms.kdt.kdtspringorder.voucher.Voucher;
import org.prgms.kdt.kdtspringorder.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
@SpringJUnitConfig
@ActiveProfiles("local")
public class AopTests {

    @Configuration
    @ComponentScan(
            basePackages = {"org.prgms.kdt.kdtspringorder.voucher", "org.prgms.kdt.kdtspringorder.aop"}
    )
    @EnableAspectJAutoProxy
    static class Config{

    }


    @Autowired
    ApplicationContext context;




    @Autowired
    VoucherRepository voucherRepository;

    @Test
    @DisplayName("Aop test")
    public void testOrderService(){
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(),100);
        voucherRepository.insert(fixedAmountVoucher);
    }
}
