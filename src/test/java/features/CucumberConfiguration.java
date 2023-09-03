package features;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("cucumber")
@CucumberContextConfiguration
public class CucumberConfiguration {
}
