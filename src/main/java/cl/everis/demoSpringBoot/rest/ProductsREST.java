package cl.everis.demoSpringBoot.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.everis.demoSpringBoot.dao.ProductsDAO;
import cl.everis.demoSpringBoot.entity.Product;
import cl.everis.demoSpringBoot.service.Services;

/**
 * Clase encargada de contener los métodos necesarios para
 * obtener, crear, actualizar y borrar datos de la base de datos. 
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
	
	/* Objeto que permite usar los métodos contenidos en Services.java */
	@Autowired
	private Services service;
	
	/* Método que realiza la función de obtener datos de la url localhost:8080/products */
	@GetMapping		//  /products (GET)
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	/* Método que realiza la función de obtener datos de la url localhost:8080/products/{id} */
	@GetMapping(value = "{productId}") //  /products/{productId} (GET)
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
	public ResponseEntity<Void> deleteProductById(@PathVariable("productId") Long productId){
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
	
	/**
	 * Métodos para sumar, restar, multiplicar y dividir 2 números
	 */
	
	/* Método encargado de sumar dos números */
	@GetMapping(value = "/sumar")
	public ResponseEntity<Double> sumar(@RequestParam Double num1, @RequestParam Double num2){
		return new ResponseEntity<Double>(service.sumar(num1, num2), HttpStatus.OK );
	}
	
	/* Método encargado de restar dos números */
	@GetMapping(value = "/restar")
	public ResponseEntity<Double> restar(@RequestParam Double num1, @RequestParam Double num2){
		return new ResponseEntity<Double>(service.restar(num1, num2), HttpStatus.OK );
	}
	
	/* Método encargado de multiplicar dos números */
	@GetMapping(value = "/multiplicar")
	public ResponseEntity<Double> multiplicar(@RequestParam Double num1, @RequestParam Double num2){
		return new ResponseEntity<Double>(service.multiplicar(num1, num2), HttpStatus.OK );
	}
	
	/* Método encargado de dividir dos números */
	@GetMapping(value = "/dividir")
	public ResponseEntity<Double> dividir(@RequestParam Double num1, @RequestParam Double num2){
		return new ResponseEntity<Double>(service.dividir(num1, num2), HttpStatus.OK );
	}
	
	/* Método encargado de ordenar de mayor a menor un arreglo de números */
	@GetMapping(value = "/ordenar")
	public ResponseEntity<Double[]> ordenar(@RequestParam Double[] nums){
		return new ResponseEntity<Double[]>(service.ordenarDesc(nums), HttpStatus.OK );
	}
}
