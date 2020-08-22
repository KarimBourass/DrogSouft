package org.sid.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
	@Id @GeneratedValue
    private Long id;
    private String roleName;

	public AppRole(String roleName) {
		super();
		this.roleName = roleName;
	}

    
}
