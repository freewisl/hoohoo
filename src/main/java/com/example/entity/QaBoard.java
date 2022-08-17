package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "QABOARDTBL7")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_QA_BOARDTBL7_NO", initialValue = 1, allocationSize = 1)
public class QaBoard {

	@Column(name = "QANO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private long no = 0L;

	@Column(name = "QATITLE", length = 30)
	private String title = null;

	@Lob
	@Column(name = "QACONTENT")
	private String content = null;

	@Column(name = "QAWRITER", length = 30)
	private String writer = null;

	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name = "QADATE")
	private Date date = null;

	@Column(name = "QAHIT")
	private Long hit = 1L;

	@OneToMany(mappedBy = "board")
	private List<QaBoardReply> board = new ArrayList<>();

	@Column(name = "QAREPCNT")
	private Long repcnt = 0L;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public List<QaBoardReply> getBoard() {
		return board;
	}

	public void setBoard(List<QaBoardReply> board) {
		this.board = board;
	}

	public Long getRepcnt() {
		return repcnt;
	}

	public void setRepcnt(Long repcnt) {
		this.repcnt = repcnt;
	}

	public QaBoard() {
		super();
	}

	public QaBoard(long no, String title, String content, String writer, Date date, Long hit, List<QaBoardReply> board,
			Long repcnt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.hit = hit;
		this.board = board;
		this.repcnt = repcnt;
	}

	@Override
	public String toString() {
		return "QaBoard [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", date="
				+ date + ", hit=" + hit + ", board=" + board + ", repcnt=" + repcnt + "]";
	}

}
