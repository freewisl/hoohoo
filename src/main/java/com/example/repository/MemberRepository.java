package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findById(String id);
	
	@Query(value = "SELECT COUNT(*) FROM MEMBERTBL7 WHERE MEMID = :id", nativeQuery = true)
	int sqlEqualById(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM MEMBERTBL7 WHERE MEMID = :id", nativeQuery = true)
	int sqlDeleteById(@Param("id") String id);
	
	@Query(value = "SELECT MEMNO, MEMDATE, MEMEMAIL, MEMPW, MEMID, MEMROLE, MEMNAME FROM MEMBERTBL7 WHERE MEMID LIKE '%' || :txt || '%' ORDER BY MEMNO DESC", nativeQuery = true)
	public List<Member> selectQueryWhereMemname(String txt, Pageable pageable);
	
	long countByNameIgnoreCaseContaining(String txt);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM MEMBERTBL7 WHERE MEMNO IN(:ids)", nativeQuery = true)
	int memberBatchDelete(@Param("ids") List<Long> no);
	
	@Query(value = "SELECT MEMNO FROM MEMBERTBL7 WHERE MEMID = ':id'", nativeQuery = true)
	long sqlSelectById(String id);
} 
