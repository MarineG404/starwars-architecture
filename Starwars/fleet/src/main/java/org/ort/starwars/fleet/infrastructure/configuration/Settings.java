package org.ort.starwars.fleet.infrastructure.configuration;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data 
@Configuration 
@ConfigurationProperties(prefix = "app")
public class Settings {
    private String version;
    private String author;
}
