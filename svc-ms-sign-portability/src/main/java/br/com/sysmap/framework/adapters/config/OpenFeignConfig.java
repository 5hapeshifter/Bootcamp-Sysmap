package br.com.sysmap.framework.adapters.config;

import br.com.sysmap.application.ports.out.PortabilityFeign;
import br.com.sysmap.framework.adapters.out.PortabilityFeignImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    @Bean
    PortabilityFeign portabilityFeign() {
        return new PortabilityFeignImpl();
    }
}
