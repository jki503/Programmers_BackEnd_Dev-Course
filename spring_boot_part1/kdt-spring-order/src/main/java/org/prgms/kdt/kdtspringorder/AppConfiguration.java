package org.prgms.kdt.kdtspringorder;

import org.prgms.kdt.kdtspringorder.order.Order;
import org.prgms.kdt.kdtspringorder.voucher.MemoryVoucherRepository;
import org.prgms.kdt.kdtspringorder.voucher.Voucher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.Optional;
import java.util.UUID;

// 개별 class에 대한 생성에 대한 책임
@Configuration
//@ComponentScan(basePackages = {"org.prgms.kdt.kdtspringorder.order","org.prgms.kdt.kdtspringorder.voucher"})
@ComponentScan(basePackageClasses = {Order.class, Voucher.class})
    //    excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MemoryVoucherRepository.class)})
public class AppConfiguration {
    /*
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
            public Order insert(Order order) {}
        };
    }

    @Bean
    public VoucherService voucherService(){return new VoucherService(voucherRepository());}

    @Bean
    public  OrderService orderService(){return new OrderService(voucherService(), orderRepository());}
*/

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanOne beanOne(){
        return new BeanOne();
    }
}

class BeanOne implements InitializingBean{

    public void init(){
        System.out.println("[BeanOne] init called");
    }

    public void destroy() {
        System.out.println("[BeanOne] destroy called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[BeanOne] afterPropertiesSet called");
    }
}

