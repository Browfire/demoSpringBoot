package cl.everis.demoSpringBoot.rest;

import java.util.Date;
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

import cl.everis.demoSpringBoot.entity.Product;
import cl.everis.demoSpringBoot.service.Services;

/**
 * Clase encargada de contener los métodos necesarios para.
 * obtener, crear, actualizar y borrar datos de la base de datos
 * 
 * @author Browfire
 *
 */
@RestController
@RequestMapping("products")
public class ProductsREST {
	
	/* Objeto que permite usar los métodos contenidos en Services.java */
	@Autowired
	private Services service;
	
	/* Método que realiza la función de obtener datos de la url localhost:8080/products */
	@GetMapping		//  /products (GET)
	public ResponseEntity<List<Product>> getProduct(){
		
		try {
			List<Product> products = service.getAllProducts();
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que realiza la función de obtener datos de la url localhost:8080/products/{id} */
	@GetMapping(value = "{productId}") //  /products/{productId} (GET)
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		
		try {
			Optional<Product> optionalProduct = service.getProductById(productId);
			if(optionalProduct.isPresent()) {
				return ResponseEntity.ok(optionalProduct.get());
			}else {
				return ResponseEntity.noContent().build();
			}		
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que realiza la función de crear datos en la url localhost:8080/products */
	@PostMapping	//  /products (POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
	
		try {
			Product newProduct = service.setOneProduct(product);
			return ResponseEntity.ok(newProduct);
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que realiza la función de borrar datos de la url localhost:8080/products/ */
	@DeleteMapping(value = "{productId}")	//  /products/{productId} (DELETE)
	public ResponseEntity<Void> deleteProductById(@PathVariable("productId") Long productId){
	
		try {
			service.deleteProductById(productId);
			return ResponseEntity.ok(null);
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que realiza la función de actualizar datos de la url localhost:8080/products */
	@PutMapping		//  /products (PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		
		try {
			Optional<Product> optionalProduct = service.getProductById(product.getId());
			if(optionalProduct.isPresent()) {
				Product updateProduct = optionalProduct.get();
				updateProduct.setName(product.getName());
				updateProduct.setPrice(product.getPrice());
				updateProduct.setBrand(product.getBrand());
				updateProduct.setProvider(product.getProvider());
				updateProduct.setDescription(product.getDescription());
				updateProduct.setWeight(product.getWeight());
				updateProduct.setColor(product.getColor());
				updateProduct.setElaborationDate(product.getElaborationDate());
				updateProduct.setExpirationDate(product.getExpirationDate());
				updateProduct = service.setOneProduct(updateProduct);
				return ResponseEntity.ok(updateProduct);
			}else {
				return ResponseEntity.notFound().build();
			}		
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 *  Consultas a la base de datos mediante JPA Keywords.
	 *   
	 */
	
	/* Método que trae productos entre un precio mínimo y un precio máximo */
	@GetMapping(value="/priceBetween")		
	public ResponseEntity<List<Product>> getPriceBetween(
			@RequestParam Double priceMin, @RequestParam Double priceMax){
		
		try {
			List<Product> products = service.getByPriceBetween(priceMin, priceMax);
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que trae productos después de cierta fecha de elaboración */
	@GetMapping(value="/dateAfter")		
	public ResponseEntity<List<Product>> getElaborationDateAfter(
			@RequestParam Date elaborationDate){
		
		try {
			List<Product> products = service.getByElaborationDateAfter(elaborationDate);
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que trae productos antes de cierta vecha de expiración */
	@GetMapping(value="/dateBefore")		
	public ResponseEntity<List<Product>> getExpirationDateBefore(
			@RequestParam Date expirationDate){
		
		try {
			List<Product> products = service.getByExpirationDateBefore(expirationDate);
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que busca productos de una cierta marca o proveedor */
	@GetMapping(value="/brandOrProvider")		
	public ResponseEntity<List<Product>> getBrandOrProvider(
			@RequestParam String brand, @RequestParam String provider){
		
		try {
			List<Product> products = service.getByBrandOrProvider(brand, provider);
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que busca productos que su nombre comience por cierto string */
	@GetMapping(value="/nameStart")		
	public ResponseEntity<List<Product>> getNameStartingWith(
			@RequestParam String start){
		
		try {
			List<Product> products = service.getByNameStartingWith(start);
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/* Método que busca productos que su descripción contenga cierto string */
	@GetMapping(value="/description")		
	public ResponseEntity<List<Product>> getDescriptionContaining(
			@RequestParam String description){
		
		try {
			List<Product> products = service.getByDescriptionContaining(description);
			return ResponseEntity.ok(products);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	/**
	 * Funcionalidades de prueba
	 * 
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