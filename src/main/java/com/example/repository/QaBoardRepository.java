package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.QaBoard;

public interface QaBoardRepository extends JpaRepository<QaBoard, Long> {

	// SELECT * FROM QABOARDTBL7 WHERE QATITLE LIKE '%' || '사과' || '%' ORDER BY
	// QANO DESC
	List<QaBoard> findAllByTitleIgnoreCaseContainingOrderByNoDesc(String txt, Pageable pageable);

	@Transactional
	@Query(value = "SELECT QANO, QACONTENT, QATITLE, QADATE, QAHIT, QAWRITER, (SELECT COUNT(*) FROM QAREPLY7 WHERE QANO = QABOARDTBL7.QANO) AS QAREPCNT FROM QABOARDTBL7 WHERE QATITLE LIKE '%' || :txt || '%' ORDER BY QANO DESC", nativeQuery = true)
	public List<QaBoard> countQueryWherQano(String txt, Pageable pageable);

	long countByTitleIgnoreCaseContaining(String txt);

	QaBoard findByNo(long no);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM QABOARDTBL7 WHERE QANO =:no", nativeQuery = true)
	int sqlDeleteByNo(@Param("no") long no);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE QABOARDTBL7 SET QAHIT = QAHIT+1 WHERE QANO =:no", nativeQuery = true)
	int sqlUpdateHitByNo(@Param("no") long no);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE QABOARDTBL7 SET QAREPCNT = (SELECT COUNT(*) FROM QAREPLY7 WHERE QANO = :no) WHERE QANO =:no", nativeQuery = true)
	int sqlUpdateRepcntByNo(@Param("no") long no);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE QABOARDTBL7 SET QAREPCNT = (SELECT COUNT(*) FROM QAREPLY7 WHERE QANO = QABOARDTBL7.QANO) WHERE QANO =QABOARDTBL7.QANO", nativeQuery = true)
	int sqlUpdateRepcntAll();
	
	@Query(value = "SELECT * FROM (SELECT b.* FROM QABOARDTBL7 a, QABOARDTBL7 b WHERE a.qano = :no AND 1 = CASE WHEN a.qano < b.qano THEN 1 ELSE 0 END ORDER BY b.qano ASC) WHERE ROWNUM = 1", nativeQuery = true)
	QaBoard sqlPrevByNo(@Param("no") long no);
	
	@Query(value = "SELECT * FROM (SELECT b.* FROM QABOARDTBL7 a, QABOARDTBL7 b WHERE a.qano = :no AND 1 = CASE WHEN a.qano > b.qano THEN 1 ELSE  0 END ORDER BY b.qano DESC) WHERE ROWNUM = 1", nativeQuery = true)
	QaBoard sqlNextByNo(@Param("no") long no);
	
	Optional<QaBoard> findAllByNo(long no);
}
