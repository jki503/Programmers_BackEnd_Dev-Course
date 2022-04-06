package org.prgms.kdt.kdtspringorder;

import org.prgms.kdt.kdtspringorder.voucher.FixedAmountVoucher;
import org.prgms.kdt.kdtspringorder.order.OrderItem;
import org.prgms.kdt.kdtspringorder.order.OrderService;
import org.prgms.kdt.kdtspringorder.voucher.VoucherRepository;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        var customerId = UUID.randomUUID();
        var orderItems = new ArrayList<OrderItem>(){{
            add(new OrderItem(UUID.randomUUID(),100L,1));
        }};

//      var orderContext = new AppConfiguration();
        var ac = new AnnotationConfigApplicationContext(AppConfiguration.class);

//        var voucherRepository  = ac.getBean(VoucherRepository.class);

        // qualifer 방식은 사용하는쪽에서 신경 쓰게하여 아예 안하게 하는게 낫다.
        var voucherRepository = BeanFactoryAnnotationUtils.qualifiedBeanOfType(ac.getBeanFactory(),VoucherRepository.class,"memory");
        var voucherRepository2 = BeanFactoryAnnotationUtils.qualifiedBeanOfType(ac.getBeanFactory(),VoucherRepository.class,"memory");
        System.out.println(MessageFormat.format("voucherRepository {0}", voucherRepository));
        System.out.println(MessageFormat.format("voucherRepository2 {0}", voucherRepository2));
        System.out.println(MessageFormat.format("voucherRepository == voucherRepository2 => {0}", voucherRepository == voucherRepository2));
        var voucher = voucherRepository.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));

        var orderService  = ac.getBean(OrderService.class);
        var order = orderService.createOrder(customerId, orderItems, voucher.getVoucherId());

       // var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(),10L);
        Assert.isTrue(order.totalAmount() == 100L, MessageFormat.format("totalAmount {0} is not 90L", order.totalAmount()));
        ac.close(); // destory와 같다 모든 빈이 소멸하게되고 콜백이 일어나요
    }
}
