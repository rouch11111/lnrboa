package lndaily.com.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"lndaily.com.cn.mapper"})
@EnableTransactionManagement
public class Applicaction {
    public static void main(String[] args) {
        SpringApplication.run(Applicaction.class,args);
    }
}
