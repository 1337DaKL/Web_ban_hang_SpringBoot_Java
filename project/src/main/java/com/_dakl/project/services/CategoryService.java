/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Category;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author luong
 */
public interface CategoryService {
    List<Category> getAll();
    Boolean create(Category category);
    Category findById(Integer id);
    Boolean update(Category category);
    Boolean detete(Integer id);
    Page<Category> getAll(Integer pageNo);
    
}
