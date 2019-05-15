package com.core.api;

import com.core.api.Utils.metricas.HttpRemoteClient;
import com.core.api.component.StatusAppComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableAsync  // Investigar que hace
@EnableScheduling // Investigar que hace
@SpringBootApplication
public class ApiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(HttpRemoteClient.class);

    //    Que hace esto
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


    //    que hace esto
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public void updateStatus() {
        try {
            StatusAppComponent.getInstance().getStatusInstance();
            StatusAppComponent.getInstance(applicationContext).getStatusInstance();
            LOG.info("Se ha actualizado [Status]");
        } catch (Exception e) {
            LOG.error(e.toString());
        }


    }
}
