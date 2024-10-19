/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "categories")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name =  "categoryName")
	private String categoryName;
	@Column(name = "categoryStatus")
	private Boolean categoryStatus;
	@OneToMany(mappedBy = "category")
	private Set<Product> products;
	public Category() {
		// TODO Auto-generated constructor stub
	}

        public Category(Integer id, String categoryName, Boolean categoryStatus, Set<Product> products) {
            this.id = id;
            this.categoryName = categoryName;
            this.categoryStatus = categoryStatus;
            this.products = products;
        }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Product> getProducts() {
        return products;
    }

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer categoryId) {
		this.id = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	
}