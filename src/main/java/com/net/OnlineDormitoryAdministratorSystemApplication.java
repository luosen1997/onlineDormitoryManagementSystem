package com.net;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.net")
public class OnlineDormitoryAdministratorSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineDormitoryAdministratorSystemApplication.class, args);
    }

}
