package it.sevenbits;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration
 * @author  Ivan Pastushenko @ sevenbits
 * @version 1.0.0 25.09.2013
 */

@Configuration
@EnableJpaRepositories
@Import(InfrastructureConfig.class)
public class ApplicationConfig {
}
