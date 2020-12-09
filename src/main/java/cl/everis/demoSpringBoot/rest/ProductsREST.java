package cl.everis.demoSpringBoot.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.everis.demoSpringBoot.dao.ProductsDAO;
import cl.everis.demoSpringBoot.entity.Product;

/**
 * Clase encargada de contener los métodos necesarios para
 * obtener, crear, actualizar y borrar datos de la base de datos 
 * 
 * @author playe
 *
 */
@RestController
@RequestMapping("products")
public class ProductsREST {
	
	/* Objeto heredado del repositorio JPA con todas sus funciones */
	@Autowired
	private ProductsDAO productDAO;
	
	/* Método que realiza la función de obtener datos de la url localhost:8080/products */
	@GetMapping		//  /products (GET)
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	/* Método que realiza la función de obtener datos de la url localhost:8080/products/{id} */
	@RequestMapping(value = "{productId}") //  /products/{productId} (GET)
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		Optional<Product> optionalProduct = productDAO.findById(productId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	/* Método que realiza la función de crear datos en la url localhost:8080/products */
	@PostMapping	//  /products (POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	/* Método que realiza la función de borrar datos de la url localhost:8080/products/ */
	@DeleteMapping(value = "{productId}")	//  /products/{productId} (DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId){
		productDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	/* Método que realiza la función de actualizar datos de la url localhost:8080/products */
	@PutMapping		//  /products (PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
