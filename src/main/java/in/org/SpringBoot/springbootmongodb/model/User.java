package in.org.SpringBoot.springbootmongodb.model;

import java.util.Date;


import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Document(collection = "user")
public class User {

	@Id
	private Long id;
	@NotNull(message = "firstName could not be null")
	private String firstName;
	@NotNull(message = "LastName could not be null")
	private String laststName;
	@NotNull(message = "birthDate could not be null")
	private Date birthDate;
	private Date createDate;
	private Date UpdateDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLaststName() {
		return laststName;
	}
	public void setLaststName(String laststName) {
		this.laststName = laststName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User(Long id, String firstName, String laststName, Date birthDate, Date createDate) {
		super();
		this.id = id;

		this.firstName = firstName;

		this.laststName = laststName;

		this.birthDate = birthDate;
		this.createDate = createDate;
	}


	public User() {

	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}



}
