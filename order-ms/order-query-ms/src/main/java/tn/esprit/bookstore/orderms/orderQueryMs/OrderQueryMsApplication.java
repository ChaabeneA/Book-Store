package tn.esprit.bookstore.orderms.orderQueryMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderQueryMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderQueryMsApplication.class, args);
    }

}
