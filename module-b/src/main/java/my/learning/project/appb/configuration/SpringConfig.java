package my.learning.project.appb.configuration;

import my.learning.project.appb.mapper.AccountMapper;
import my.learning.project.appb.mapper.AccountMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public  AccountMapper accountMapper(){
        return new AccountMapperImpl();
    }
}
