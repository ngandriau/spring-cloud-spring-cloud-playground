package hello;

import hello.filters.pro.ResponseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import hello.filters.pre.RequestFilter;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RequestFilter requestFilter() {
    return new RequestFilter();
  }

  @Bean
  public ResponseFilter responseFilter() {
    return new ResponseFilter();
  }

}
