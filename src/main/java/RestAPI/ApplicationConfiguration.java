package RestAPI;

import RestAPI.Entity.Factory.IProductFactory;
import RestAPI.Entity.Factory.ProductFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Api REST configuration file
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public IProductFactory factory() { return new ProductFactory(); }

}