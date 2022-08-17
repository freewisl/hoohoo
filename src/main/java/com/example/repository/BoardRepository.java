package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@Query(value = "SELECT NOTICENO, NOTICETITLE, NOTICECONTENT, NOTICEDATE, NOTICEHIT, NOTICEWRITER FROM NOTICEBOARDTBL7 WHERE NOTICETITLE LIKE '%' || :txt || '%' ORDER BY NOTICENO DESC", nativeQuery = true)
	public List<Board> countQueryWhereNoticeno(String txt, Pageable pageable);
	
	long countByTitleIgnoreCaseContaining(String txt);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE NOTICEBOARDTBL7 SET NOTICEHIT = NOTICEHIT+1 WHERE NOTICENO =:no", nativeQuery = true)
	int sqlUpdateHitByNo(@Param("no") long no);
	
	Board findByNo(long no);
	
	@Query(value = "SELECT * FROM (SELECT b.* FROM noticeboardtbl7 a, noticeboardtbl7 b WHERE a.noticeno = :no AND 1 = CASE WHEN a.noticeno < b.noticeno THEN 1 ELSE 0 END ORDER BY b.noticeno ASC)WHERE ROWNUM = 1  ", nativeQuery = true)
	Board sqlPrevByNo(@Param("no") long no);
	
	@Query(value = "SELECT * FROM (SELECT b.* FROM noticeboardtbl7 a, noticeboardtbl7 b WHERE a.noticeno = :no AND 1 = CASE WHEN a.noticeno > b.noticeno THEN 1 ELSE  0 END ORDER BY b.noticeno DESC)WHERE ROWNUM = 1", nativeQuery = true)
	Board sqlNextByNo(@Param("no") long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM NOTICEBOARDTBL7 WHERE NOTICENO = :no", nativeQuery = true)
	int sqlDeleteByNo(@Param("no") long no);
	
	Optional<Board> findAllByNo(long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE NOTICEBOARDTBL7 SET (NOTICETITLE, NOTICECONTENT) = (:#{#vo.title}, :#{#vo.content}) WHERE NOTICENO = :#{#vo.no}", nativeQuery = true)
	int sqlUpdateByNo(@Param("vo") Board vo);
}
