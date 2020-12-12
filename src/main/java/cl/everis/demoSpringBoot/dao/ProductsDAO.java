package cl.everis.demoSpringBoot.dao;

import java.util.Date;
import java.util.List;

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

	/* Método que busca productos entre un precio mínimo y un precio máximo */
	List<Product> findByPriceBetween(Double priceMin, Double priceMax);
	
	/* Método que busca productos después de cierta fecha de elaboración */
	List<Product> findByElaborationDateAfter(Date elaborationDate);	
	
	/* Método que busca productos antes de cierta vecha de expiración */
	List<Product> findByExpirationDateBefore(Date expirationDate);
	
	/* Método que busca productos de una cierta marca o proveedor */
	List<Product> findByBrandOrProvider(String brand, String provider);
	
	/* Método que busca productos que su nombre comience por cierto string */
	List<Product> findByNameStartingWith(String start);
	
	/* Método que busca productos que su descripción contenga cierto string */
	List<Product> findByDescriptionContaining(String description);
	
}
