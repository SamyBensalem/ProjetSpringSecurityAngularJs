package org.enset1.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String username;
	private String password;
	private boolean actived;


	@ManyToMany
	@JoinTable(name="USERS_ROLES")
	private Collection<Role> roles;


	public User(String username, String password, boolean actived) {
		super();
		this.username = username;
		this.password = password;
		this.actived = actived;
	}
	
	
	
	
	
	

}
