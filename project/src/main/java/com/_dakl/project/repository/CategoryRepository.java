/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.repository;

import com._dakl.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author luong
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
