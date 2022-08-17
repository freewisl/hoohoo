package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CARTTBL7")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_CART7_NO", initialValue = 1, allocationSize = 1)
public class Cart {

	@Column(name = "CARTNO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private long no = 0L;

	@Column(name = "CARTCNT")
	private long cnt = 0L;

	@ManyToOne
	@JoinColumn(name = "ITEMNO")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "MEMNO")
	private Member member;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getCnt() {
		return cnt;
	}

	public void setCnt(long cnt) {
		this.cnt = cnt;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Cart() {
		super();
	}

	public Cart(long no, long cnt, Item item, Member member) {
		super();
		this.no = no;
		this.cnt = cnt;
		this.item = item;
		this.member = member;
	}

}
