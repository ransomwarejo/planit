package com.josias.planit;

import com.josias.planit.infrastructure.AbstractTestcontainers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlanitApplicationTests extends AbstractTestcontainers {

	@Test
	void contextLoads() {
	}

}
