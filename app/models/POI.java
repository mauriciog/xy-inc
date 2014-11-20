package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Min;
import play.db.ebean.Model;

/**
 * 
 * @author mauricio
 *
 */
@Entity
public class POI extends Model {
	private static final long serialVersionUID = -1784510198095152005L;

	/**
	 * Constructor
	 */
	public POI(String name, Integer x, Integer y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}

	/**
	 * Attributes
	 */
	@Id
	private Long id;

	@NotNull
	private String name;

	@Min(0)
	private Integer x;

	@Min(0)
	private Integer y;

	/**
	 * Getters and Setters
	 */
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

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
}
