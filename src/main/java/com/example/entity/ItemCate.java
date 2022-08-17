package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMCATE7")
public class ItemCate {

	@Id
	@Column(name = "ITEMCATEID", length = 30)
	private String id;

	@OneToMany(mappedBy = "itemcate")
	private List<Item> items = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	/*
	 * @Override public String toString() { return "ItemCate [id=" + id + ", items="
	 * + items + "]"; }
	 */

	public ItemCate() {
		super();
	}

	public ItemCate(String id, List<Item> items) {
		super();
		this.id = id;
		this.items = items;
	}

}
