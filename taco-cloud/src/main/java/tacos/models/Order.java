package tacos.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity(name="Order")
@Table(name="Taco_Order")
public class Order implements Serializable {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date placedAt;
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;

	@ManyToMany(targetEntity=Taco.class)
	@JoinTable(name="Taco_Order_Tacos",
		joinColumns = @JoinColumn(name="order_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name="taco_id", referencedColumnName="id")
	)
	
	private List<Taco> tacos;
	
	public Order( ) {
		this.tacos = new ArrayList<>();
	}
	
	@NotBlank(message="Name is required")
	private String name;
	@NotBlank(message="Street is required")
	private String street;
	@NotBlank(message="City is required")
	private String city;
	@NotBlank(message="State is required")
	private String state;
	@NotBlank(message="Zip is required")
	private String zip;
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer=3, fraction=0, message="Invalid CCV")
	private String ccCVV;
	
	public void addTaco(Taco taco) {
		tacos.add(taco);
	}

	public List<Taco> getTacos() {
		return tacos;		
	}

}
