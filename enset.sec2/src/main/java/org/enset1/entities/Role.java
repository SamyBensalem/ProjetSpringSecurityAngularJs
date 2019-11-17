package org.enset1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String role;
	private String description;
	
	
	
	

}
