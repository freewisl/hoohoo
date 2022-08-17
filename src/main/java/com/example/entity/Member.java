package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "MEMBERTBL7")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_MEMBERTBL7_NO", initialValue = 1, allocationSize = 1)
public class Member {
	
	@Column(name = "MEMNO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private long no = 0L;
	
	@Column(name = "MEMID", length = 30)
	private String id = null;
	
	@Column(name = "MEMPW", length = 200)
	private String pw = null;
	
	@Column(name = "MEMNAME", length = 30)
	private String name = null;
	
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name = "MEMDATE")
	private Date date = null;
	
	@Column(name = "MEMEMAIL", length = 50)
	private String email = null;
	
	@Column(name = "MEMROLE", length = 30)
	private String role = "FAMILY";

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", date=" + date + ", email=" + email + ", role=" + role + "]";
	}

	public Member() {
		super();
	}

	public Member(long no, String id, String pw, String name, Date date, String email, String role) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.date = date;
		this.email = email;
		this.role = role;
	}
}
