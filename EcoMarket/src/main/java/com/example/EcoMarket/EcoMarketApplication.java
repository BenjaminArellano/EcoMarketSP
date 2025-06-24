package com.example.EcoMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {
	"Productos.repository",
    "Usuarios.repository",
    "Pedidos.repository",
    "Productos.model",
    "Pedidos.model",
    "Usuarios.model",
    "Productos.controller",
    "Pedidos.controller",
    "Usuarios.controller",
    "Productos.service",
    "Pedidos.service",
    "Usuarios.service"})
@EnableJpaRepositories(basePackages = {
    "Productos.repository",
    "Pedidos.repository",
    "Usuarios.repository"
})
@EntityScan(basePackages = {
    "Productos.model",
    "Pedidos.model",
    "Usuarios.model"
})
public class EcoMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoMarketApplication.class, args);
	}

}
