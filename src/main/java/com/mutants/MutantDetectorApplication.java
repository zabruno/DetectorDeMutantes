package com.mutants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Detector de Mutantes",
                version = "1.0",
                description = "API REST para identificar ADN mutante y obtener estadísticas"
        )
)
@SpringBootApplication
public class MutantDetectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantDetectorApplication.class, args);
        System.out.println("Hola!");
	}
}
