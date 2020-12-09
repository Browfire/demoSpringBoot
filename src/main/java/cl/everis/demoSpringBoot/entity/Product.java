package cl.everis.demoSpringBoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase encargada de contener la información de los productos
 * funcionando como una tabla llamada "products" contenida en 
 * una base de datos H2 con ruta jdbc:h2:./database
 * 
 * @author playe
 *
 */
@Entity
@Table(name = "products")
public class Product {
	
	/** Atributo que representa el id del producto **/
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Atributo que representa el nombre del producto **/
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	
	/** Getters & Setters **/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
