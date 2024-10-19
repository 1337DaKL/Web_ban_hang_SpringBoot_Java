/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Category;
import com._dakl.project.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luong
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return this.categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findById(Integer id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public Boolean update(Category category) {
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }

    @Override
    public Boolean detete(Integer id) {
        try {
            this.categoryRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
