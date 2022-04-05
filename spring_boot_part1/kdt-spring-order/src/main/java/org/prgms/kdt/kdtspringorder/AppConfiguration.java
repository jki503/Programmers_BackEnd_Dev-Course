package org.prgms.kdt.kdtspringorder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

// 개별 class에 대한 생성에 대한 책임
@Configuration
public class AppConfiguration {

    @Bean
    public VoucherRepository voucherRepository(){
        return new VoucherRepository() {
            @Override
            public Optional<Voucher> findById(UUID voucherID) {
                return Optional.empty();
            }
        };
    }

    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepository() {
            @Override
            public void insert(Order order) {}
        };
    }

    @Bean
    public VoucherService voucherService(){return new VoucherService(voucherRepository());}

    @Bean
    public  OrderService orderService(){return new OrderService(voucherService(), orderRepository());}
}
