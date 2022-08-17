package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NOTICEBOARDTBL7")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_NOTICE_BOARDTBL7_NO", initialValue = 1, allocationSize = 1)
public class Board {
	@Column(name = "NOTICENO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private long no = 0L;
	

	@Column(name = "NOTICETITLE", length = 30)
	private String title = null;
	
	@Lob
	@Column(name = "NOTICECONTENT")
	private String content = null;

	@Column(name = "NOTICEWRITER", length = 30)
	private String writer = null;

	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name = "NOTICEDATE")
	private Date date = null;

	@Column(name = "NOTICEHIT")
	private Long hit = 1L;

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

	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", date=" + date
				+ ", hit=" + hit + "]";
	}

	public Board() {
		super();
	}

	public Board(long no, String title, String content, String writer, Date date, Long hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.hit = hit;
	}

}
