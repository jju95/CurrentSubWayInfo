package Assignment.sample.Config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("my.api")
@Getter @Setter
public class MyPropertiesConfig {

    String url;
    String dataType;
    String name;
    key key;

    @Getter
    @Setter
    public static class key{
        String dev;
    }
}
