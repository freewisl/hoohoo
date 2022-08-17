package com.example.entity;

import java.util.Arrays;
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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ITEMTBL7")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_ITEMTBL7_NO", initialValue = 1, allocationSize = 1)
public class Item {

	@Column(name = "ITEMNO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private long no = 0L;

	@Column(name = "ITEMNAME", length = 30)
	private String name = null;

	@Lob
	@Column(name = "ITEMDETAIL")
	private String detail = null;

	@Column(name = "ITEMPRICE")
	private Long price = 0L;

	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name = "ITEMDATE")
	private Date date = null;

	@ManyToOne
	@JoinColumn(name = "ITEMCATEID")
	private ItemCate itemcate;

	@Lob
	@Column(name = "ITEMIMG")
	private byte[] itemimg;

	// byte[] => base64 string으로 변경해서 보관할 변수
	@Transient // 컬럼생성하지 않음
	private String base64;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ItemCate getItemcate() {
		return itemcate;
	}

	public void setItemcate(ItemCate itemcate) {
		this.itemcate = itemcate;
	}

	public byte[] getItemimg() {
		return itemimg;
	}

	public void setItemimg(byte[] itemimg) {
		this.itemimg = itemimg;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	@Override
	public String toString() {
		return "Item [no=" + no + ", name=" + name + ", detail=" + detail + ", price=" + price + ", date=" + date
				+ ", itemcate=" + itemcate + ", itemimg=" + Arrays.toString(itemimg) + ", base64=" + base64 + "]";
	}

	public Item() {
		super();
	}

	public Item(long no, String name, String detail, Long price, Date date, ItemCate itemcate, byte[] itemimg,
			String base64) {
		super();
		this.no = no;
		this.name = name;
		this.detail = detail;
		this.price = price;
		this.date = date;
		this.itemcate = itemcate;
		this.itemimg = itemimg;
		this.base64 = base64;
	}

}
