package org.prgms.kdt.kdtspringorder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        var customerId = UUID.randomUUID();
        var orderItems = new ArrayList<OrderItem>(){{
            add(new OrderItem(UUID.randomUUID(),100,1));
        }};

//        var orderContext = new AppConfiguration();
        var ac = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var orderService  = ac.getBean(OrderService.class);
        var order = orderService.createOrder(customerId, orderItems);

        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(),10);
        Assert.isTrue(order.totalAmount() == 100L, MessageFormat.format("totalAmount {0} is not 1000L", order.totalAmount()));
    }
}
