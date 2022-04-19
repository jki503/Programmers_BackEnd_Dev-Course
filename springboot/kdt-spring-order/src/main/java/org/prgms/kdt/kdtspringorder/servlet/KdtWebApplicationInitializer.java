package org.prgms.kdt.kdtspringorder.servlet;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class KdtWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic servletRegistration
                = servletContext.addServlet("test", new TestServlet());
        servletRegistration.addMapping("/*");
        servletRegistration.setLoadOnStartup(1);
    }
}
