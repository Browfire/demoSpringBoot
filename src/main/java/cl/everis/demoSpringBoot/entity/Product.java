package cl.everis.demoSpringBoot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Clase encargada de contener la información de los productos.
 * funcionando como una tabla llamada "products" contenida en 
 * una base de datos H2 (con ruta /database)
 * 
 * @author Browfire
 *
 */
@Data
@Entity
@Table(name = "products")
public class Product {
	
	/** Atributo que representa el id del producto **/
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Atributo que representa el nombre del producto **/
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	/** Atributo que representa el precio del producto **/
	@Column(name = "price", nullable = false, length = 50)
	private Double price;
	
	/** Atributo que representa la marca del producto **/
	@Column(name = "brand", nullable = false, length = 50)
	private String brand;
	
	/** Atributo que representa el proveedor del producto **/
	@Column(name = "provider", nullable = false, length = 50)
	private String provider;
	
	/** Atributo que representa la descripción del producto **/
	@Column(name = "description", length = 200)
	private String description;
	
	/** Atributo que representa el peso en kg del producto **/
	@Column(name = "weight", length = 50)
	private Double weight;
	
	/** Atributo que representa el color dominante del producto **/
	@Column(name = "color", length = 50)
	private String color;
	
	/** Atributo que representa la fecha de elaboración del producto **/
	@Column(name = "elaborationDate")
	private Date elaborationDate;

	/** Atributo que representa la fecha de caducidad del producto **/
	@Column(name = "expirationDate")
	private Date expirationDate;
	
}