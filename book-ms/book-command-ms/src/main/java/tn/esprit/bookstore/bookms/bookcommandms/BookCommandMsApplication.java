package tn.esprit.bookstore.bookms.bookcommandms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookCommandMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCommandMsApplication.class, args);
    }

}
