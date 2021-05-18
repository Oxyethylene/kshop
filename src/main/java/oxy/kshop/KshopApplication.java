package oxy.kshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"oxy.kshop.mapper"})
@SpringBootApplication
public class KshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(KshopApplication.class, args);
    }

}
