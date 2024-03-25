package net.piyush.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="category_cat",
      uniqueConstraints= {
    		  @UniqueConstraint(name="u1",columnNames = {"categoryCat"})
      })
public class EntityCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idCat;
	
	@Column(length=225,columnDefinition = "VARCHAR(225)")
	public String categoryCat;
	
	@Column(length=225,columnDefinition = "CHAR(1) default 'N'")
	public char deleteflagCat='N';
	
} 
