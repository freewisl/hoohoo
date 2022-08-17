package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.QaBoard;
import com.example.entity.QaBoardReply;

@Repository
public interface QaReplyRepository extends JpaRepository<QaBoardReply, Long> {
	List<QaBoardReply> findAllByOrderByNoDesc();

	QaBoardReply findByNo(long no);

	@Query(value = "SELECT * FROM QAREPLY7 WHERE QANO = :no ORDER BY QAREPNO ASC", nativeQuery = true)
	public List<QaBoardReply> selectQueryWhereQano(@Param("no") long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM QAREPLY7 WHERE QANO = :no", nativeQuery = true)
	int sqlDeleteByQano(@Param("no") long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM QAREPLY7 WHERE QAREPNO =:repno", nativeQuery = true)
	int sqlDeleteByRepno(@Param("repno") long repno);

	long countByBoard(QaBoard board);

}
