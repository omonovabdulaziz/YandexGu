package ala.ddin.yagu;

import ala.ddin.yagu.utils.SmsServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YaGuApplication {

    public static void main(String[] args) {
        SpringApplication.run(YaGuApplication.class, args);
    }

}
