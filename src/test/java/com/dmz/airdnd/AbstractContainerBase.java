package com.dmz.airdnd;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractContainerBase {

	public static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
		.withDatabaseName("testdb")
		.withUsername("test")
		.withPassword("test");

	static {
		mysql.start(); // 컨테이너를 static 블록에서 강제 시작
	}

	@DynamicPropertySource
	static void overrideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
	}
}
