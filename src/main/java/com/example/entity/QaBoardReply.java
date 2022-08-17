package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "QAREPLY7") // 생성될 테이블 명
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_QA_BOARD_REPLY_NO", initialValue = 1, allocationSize = 1) // 생성될
																												// 시퀀스 명
public class QaBoardReply {
	@Column(name = "QAREPNO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long no = 0L;

	@Lob
	@Column(name = "QAREPCONTENT", length = 50)
	private String content = null;

	@ManyToOne
	@JoinColumn(name = "QANO")
	private QaBoard board = null;

	@Column(name = "QAREPWRITER", length = 30)
	private String writer = null;

	@CreationTimestamp // CURRENT_DATE와 같은기능
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name = "QAREPDATE")
	private Date date;

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public QaBoard getBoard() {
		return board;
	}

	public void setBoard(QaBoard board) {
		this.board = board;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public QaBoardReply() {
		super();
	}

	public QaBoardReply(Long no, String content, QaBoard board, String writer, Date date) {
		super();
		this.no = no;
		this.content = content;
		this.board = board;
		this.writer = writer;
		this.date = date;
	}

}
