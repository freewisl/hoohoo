package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query(value = "SELECT * FROM CARTTBL7 WHERE MEMNO = :no", nativeQuery = true)
	List<Cart> sqlSelectByMemno(long no);
	
	@Query(value = "SELECT COUNT(*) FROM CARTTBL7 WHERE MEMNO = :no", nativeQuery = true)
	long sqlCountByMemno(long no);
	
	@Query(value = "SELECT COUNT(*) FROM CARTTBL7 WHERE ITEMNO = :itemno AND MEMNO = :memno", nativeQuery = true)
	long sqlCountByItemnoAndMemno(long itemno, long memno);
	
	@Query(value = "SELECT * FROM CARTTBL7 WHERE ITEMNO = :no", nativeQuery = true)
	Cart sqlSelectByItemno(long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE CARTTBL7 SET CARTCNT = CARTCNT + :#{#vo.cnt} WHERE CARTNO = :#{#vo.no}", nativeQuery = true)
	int sqlUpdateById(@Param("vo") Cart vo);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM CARTTBL7 WHERE CARTNO IN(:ids)", nativeQuery = true)
	int cartBatchDelete(@Param("ids") List<Long> no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM CARTTBL7 WHERE MEMNO = :no ", nativeQuery = true)
	int cartEmptyDelete(@Param("no") long no);
}
