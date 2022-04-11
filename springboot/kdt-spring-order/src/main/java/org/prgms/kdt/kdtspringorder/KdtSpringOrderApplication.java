package org.prgms.kdt.kdtspringorder;

import org.prgms.kdt.kdtspringorder.order.OrderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {
				"org.prgms.kdt.kdtspringorder.voucher",
				"org.prgms.kdt.kdtspringorder.order",
				"org.prgms.kdt.kdtspringorder.configuration"
		}
)
public class KdtSpringOrderApplication {

	public static void main(String[] args) {
		var springApplication = new SpringApplication(KdtSpringOrderApplication.class);
		springApplication.setAdditionalProfiles("local");
		var applicationContext = springApplication.run(args);
		var orderProperties	= applicationContext.getBean(OrderProperties.class);
	}

}
