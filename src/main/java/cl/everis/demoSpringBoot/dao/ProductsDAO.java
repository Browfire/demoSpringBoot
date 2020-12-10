package cl.everis.demoSpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.everis.demoSpringBoot.entity.Product;

/**
 * Interfaz encargada de conectar la clase ProductsREST.java .
 * con los métodos de JPARepository
 * 
 * @author Browfire
 *
 */
public interface ProductsDAO extends JpaRepository<Product, Long> {

	
	
	
}