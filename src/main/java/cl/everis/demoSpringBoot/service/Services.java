package cl.everis.demoSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.everis.demoSpringBoot.dao.ProductsDAO;
import cl.everis.demoSpringBoot.entity.Product;


/**
 * Clase contenedora de los servicios a utilizar por el controlador.
 * 
 * @author Browfire
 *
 */
@Service
public class Services {
	
	/* Objeto heredado del repositorio JPA con todas sus funciones */
	@Autowired
	private ProductsDAO productDAO;
	
	/* Método que guarda una entidad en la base de datos */
	public Product setOneProduct(Product product) {
		return productDAO.save(product);
	}
	
	/* Método que trae todas las entidades de la base de datos */
	public List<Product> getAllProducts() {
		return productDAO.findAll();
	}
	
	/* Método que trae una entidad de la base de datos, según un id */
	public Optional<Product> getProductById(Long productId) {
		return productDAO.findById(productId);
	}
	
	/* Método que elimina una entidad de la base de datos, según un id */
	public void deleteProductById(Long productId) {
		productDAO.deleteById(productId);
	}
	
	
	
	
	
	
	
	
	/* Método que se encarga de sumar dos números de tipo Double */
	public Double sumar(Double num1, Double num2) {
		if(validar(num1, num2)) {
			return Double.sum(num1, num2);
		}else {
			throw new RuntimeException("Debe ingresar números válidos");
		}
	}
	
	/* Método que se encarga de restar dos números de tipo Double */
	public Double restar(Double num1, Double num2) {
		if(validar(num1, num2)) {
			return Double.sum(num1, -num2);
		}else {
			throw new RuntimeException("Debe ingresar números válidos");
		}
	}
	
	/* Método que se encarga de multiplicar dos números de tipo Double */
	public Double multiplicar(Double num1, Double num2) {
		if(validar(num1, num2)) {
			return num1*num2;
		}else {
			throw new RuntimeException("Debe ingresar números válidos");
		}
	}
	
	/* Método que se encarga de dividir dos números de tipo Double */
	public Double dividir(Double num1, Double num2) {
		if(validar(num1, num2) && validarDiv(num2)) {
			return num1/num2;
		}else {
			throw new RuntimeException("Debe ingresar números válidos");
		}
	}
	
	/* Método que se encarga de validar que los numeros sean distintos de null */
	public Boolean validar(Double num1, Double num2) {
		if(num1 == null || num2 == null) {
			return false;
		}else {
			return true;
		}
	}
	
	/* Método que se encarga de validar que num2 sea distinto de 0 */
	public Boolean validarDiv(Double num2) {
		if(num2.compareTo(0D) == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	/* Método que ordena un arreglo de números, de mayor a menor */
	public Double[] ordenarDesc(Double[] nums) {
		if(validarArr(nums)) {
			Double tmp = 0D;
		    for (Integer x = 0; x < nums.length; x++) {
		        for (Integer i = 0; i < nums.length-x-1; i++) {
		            if (nums[i] < nums[i+1]){
		                tmp = nums[i+1];
		                nums[i+1] = nums[i];
		                nums[i] = tmp;
		            }
		        }
		    }
		    return nums;
		}else {
			throw new RuntimeException("Debe ingresar números válidos");
		}
	}
	
	/* Método que se encarga de validar que los numeros sean distintos de null */
	public Boolean validarArr(Double[] nums) {
		for (Object o : nums) {
			if(o == null) {
				return false;
			}
		}
		return true;
	}
		
}