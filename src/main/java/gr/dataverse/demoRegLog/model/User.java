package gr.dataverse.demoRegLog.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name="user_tbl")
public class User {
	
	@Transient
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)//for dont create the extra hibernate table for auto increments in db
		@GenericGenerator(
		    name = "native", 
		    strategy = "native"
		)
	@Column(name = "user_id")
	private Long userId;
	@Column( nullable=false , length=25)
	private String firstName;
	@Column( nullable=false , length=25)
	private String lastName;
	@Column( nullable=false , length=25 , unique= true)
	private String email;
	@Column( nullable=false , length=25)
	private String telephone;
	@Column( length=25)
	private String company;
	@Column( length=250)
	private String password;
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = passwordEncoder.encode(password);
	}
	
	@Override
	public String toString() {
		return "User [passwordEncoder=" + passwordEncoder + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", telephone=" + telephone + ", company=" + company
				+ ", password=" + password + "]";
	}
	
	

}
