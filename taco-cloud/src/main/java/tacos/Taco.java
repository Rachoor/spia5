package tacos;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity(name="Taco")
public class Taco {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date createdAt;

	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	
	@Size(min=1, message="You must choose at least 1 ingredient")
	@JoinTable(name="Taco_Ingredient",
				joinColumns = @JoinColumn(name="taco_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn(name="ingredient_id", referencedColumnName="id")
	)
	@ManyToMany(targetEntity=Ingredient.class)
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}