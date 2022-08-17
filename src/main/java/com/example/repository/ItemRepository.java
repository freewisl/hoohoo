package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	List<Item> findAllByOrderByNoDesc();
	
	@Query(value = "SELECT * FROM ITEMTBL7 WHERE ITEMCATEID LIKE :cateid ORDER BY ITEMNO DESC", nativeQuery = true)
	public List<Item> selectQueryItemcateOrderByItemNoDesc(@Param("cateid") String cateid);
	
	Item findByNo(long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM ITEMTBL7 WHERE ITEMNO = :no", nativeQuery = true)
	int sqlDeleteByNo(@Param("no") long no);
} 
