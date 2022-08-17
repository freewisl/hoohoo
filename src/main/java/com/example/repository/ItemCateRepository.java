package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ItemCate;

public interface ItemCateRepository extends JpaRepository<ItemCate, Long>{
	
	Optional<ItemCate> findById(String cateid);
	
} 
