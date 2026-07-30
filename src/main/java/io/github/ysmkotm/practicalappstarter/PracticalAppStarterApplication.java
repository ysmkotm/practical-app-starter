package io.github.ysmkotm.practicalappstarter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.github.ysmkotm.practicalappstarter.mapper")
public class PracticalAppStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticalAppStarterApplication.class, args);
	}
}
