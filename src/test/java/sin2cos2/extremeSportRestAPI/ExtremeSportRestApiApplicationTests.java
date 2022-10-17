package sin2cos2.extremeSportRestAPI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
		"spring.flyway.enabled=false",
		"spring.jpa.hibernate.ddl-auto=none"
})
@DataJpaTest
class ExtremeSportRestApiApplicationTests{

	@Test
	void contextLoads() {
	}

}
