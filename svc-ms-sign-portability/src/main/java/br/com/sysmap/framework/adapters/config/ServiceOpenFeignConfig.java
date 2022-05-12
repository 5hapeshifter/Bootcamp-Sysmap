package br.com.sysmap.framework.adapters.config;

import br.com.sysmap.application.ports.out.PortabilityConsumerRepository;
import br.com.sysmap.application.ports.out.PortabilityFeign;
import br.com.sysmap.application.ports.services.PortabilityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceOpenFeignConfig {
    @Bean
    PortabilityServiceImpl portabilityService(PortabilityConsumerRepository repository, PortabilityFeign feign){
        return new PortabilityServiceImpl(repository, feign);
    }

}
