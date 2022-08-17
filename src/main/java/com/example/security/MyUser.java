package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {

	// 추가할 변수명
	private String id = null;
	private String pw = null;
	private String name = null;

	public MyUser(String id, String pw, Collection<? extends GrantedAuthority> authorities, String name) {
		super(id, pw, authorities);
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}