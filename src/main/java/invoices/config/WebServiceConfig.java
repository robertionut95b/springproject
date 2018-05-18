package invoices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /*@Bean
    public ServletRegistrationBean invoiceDispatcherServlet(ApplicationContext context) {
        InvoiceDispatcherServlet servlet = new InvoiceDispatcherServlet();
    }*/

    @Bean
    public XsdSchema invoiceSchema() {
        return new SimpleXsdSchema((new ClassPathResource("schema.xsd")));
    }
}
