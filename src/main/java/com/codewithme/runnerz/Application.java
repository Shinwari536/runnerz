package com.codewithme.runnerz;

import com.codewithme.runnerz.run.Location;
import com.codewithme.runnerz.run.Run;
import com.codewithme.runnerz.run.RunJsonDataLoader;
import com.codewithme.runnerz.user.UserHttpClient;
import com.codewithme.runnerz.user.UserRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpExchangeAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    UserHttpClient userHttpClient(){
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }



    @Bean
    public CommandLineRunner init(UserHttpClient userHttpClient) {
        return args -> {
            System.out.println(userHttpClient.findAll().toString());;
        };
    }

}
