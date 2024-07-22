package tn.esprit.bookstore.orderCommandMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderCommandMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCommandMsApplication.class, args);
    }

}
