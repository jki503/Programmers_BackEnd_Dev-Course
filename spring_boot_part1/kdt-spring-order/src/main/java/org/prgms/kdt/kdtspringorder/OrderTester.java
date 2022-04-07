package org.prgms.kdt.kdtspringorder;

import org.prgms.kdt.kdtspringorder.order.OrderProperties;
import org.prgms.kdt.kdtspringorder.voucher.FixedAmountVoucher;
import org.prgms.kdt.kdtspringorder.order.OrderItem;
import org.prgms.kdt.kdtspringorder.order.OrderService;
import org.prgms.kdt.kdtspringorder.voucher.JdbcVoucherRepository;
import org.prgms.kdt.kdtspringorder.voucher.VoucherRepository;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderTester {
    public static void main(String[] args) throws IOException {
        var customerId = UUID.randomUUID();
        var orderItems = new ArrayList<OrderItem>(){{
            add(new OrderItem(UUID.randomUUID(),100L,1));
        }};

//      var orderContext = new AppConfiguration();


//        var ac = new AnnotationConfigApplicationContext();
//        ac.register(AppConfiguration.class);
//        var environment = ac.getEnvironment();
//        environment.setActiveProfiles("local");
//        ac.refresh();

        var ac = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var resource = ac.getResource("application.yaml");
        System.out.println(MessageFormat.format("resource -> {0}", resource.getClass().getCanonicalName()));

        var file = resource.getFile();
        var strings = Files.readAllLines(file.toPath());
        System.out.println(strings.stream().reduce("",(a, b) -> a + "\n" + b));

        var resource2 = ac.getResource("file:sample.txt");
        var file2 = resource2.getFile();
        var strings2 = Files.readAllLines(file2.toPath());
        System.out.println(strings2.stream().reduce("",(a, b) -> a + "\n" + b));

        var resource3 = ac.getResource("https://stackoverflow.com/");
        var readableByteChannel = Channels.newChannel(resource3.getURL().openStream());
        var bufferedReader = new BufferedReader(Channels.newReader(readableByteChannel, StandardCharsets.UTF_8));
        var lines = bufferedReader.lines();
        var contents = lines.collect(Collectors.joining("\n"));
        System.out.println(contents);

//        var version = environment.getProperty("kdt.version");
//        var minimumOrderAmount = environment.getProperty("kdt.minimum-order-amount", Integer.class);
//        var support = environment.getProperty("kdt.support-vendors", List.class);
//        var description = environment.getProperty("kdt.description", List.class);
//
//        System.out.println(MessageFormat.format("version -> {0}", version));
//        System.out.println(MessageFormat.format("minimumOrderAmount -> {0}", minimumOrderAmount));
//        System.out.println(MessageFormat.format("support -> {0}", support));
//        System.out.println(MessageFormat.format("description -> {0}", description));
//        var voucherRepository  = ac.getBean(VoucherRepository.class);

        var orderProperties = ac.getBean(OrderProperties.class);

        System.out.println(MessageFormat.format("version -> {0}", orderProperties.getVersion()));
        System.out.println(MessageFormat.format("minimumOrderAmount -> {0}", orderProperties.getMinimumOrderAmount()));
        System.out.println(MessageFormat.format("support -> {0}", orderProperties.getSupportVendors()));
        System.out.println(MessageFormat.format("description -> {0}", orderProperties.getDescription()));


        // qualifer 방식은 사용하는쪽에서 신경 쓰게하여 아예 안하게 하는게 낫다.
//        var voucherRepository = BeanFactoryAnnotationUtils.qualifiedBeanOfType(ac.getBeanFactory(),VoucherRepository.class,"memory");
//        var voucherRepository2 = BeanFactoryAnnotationUtils.qualifiedBeanOfType(ac.getBeanFactory(),VoucherRepository.class,"memory");
//        System.out.println(MessageFormat.format("voucherRepository {0}", voucherRepository));
//        System.out.println(MessageFormat.format("voucherRepository2 {0}", voucherRepository2));
//        System.out.println(MessageFormat.format("voucherRepository == voucherRepository2 => {0}", voucherRepository == voucherRepository2));
//        var voucher = voucherRepository.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));

        var voucherRepository = ac.getBean(VoucherRepository.class);
        var voucher = voucherRepository.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));

        System.out.println(MessageFormat.format("is Jdbc -> {0}", voucherRepository instanceof JdbcVoucherRepository));
        System.out.println(MessageFormat.format("is Jdbc -> {0}", voucherRepository.getClass().getCanonicalName()));

        var orderService  = ac.getBean(OrderService.class);
        var order = orderService.createOrder(customerId, orderItems, voucher.getVoucherId());

       // var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(),10L);
        Assert.isTrue(order.totalAmount() == 100L, MessageFormat.format("totalAmount {0} is not 90L", order.totalAmount()));
      //  ac.close(); // destory와 같다 모든 빈이 소멸하게되고 콜백이 일어나요


    }
}
