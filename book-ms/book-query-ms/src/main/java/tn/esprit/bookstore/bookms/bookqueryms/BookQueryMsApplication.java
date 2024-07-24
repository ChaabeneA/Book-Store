package tn.esprit.bookstore.bookms.bookqueryms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookQueryMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookQueryMsApplication.class, args);

    }

}
