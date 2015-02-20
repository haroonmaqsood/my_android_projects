package com.example.project;

public class MenuCategory {
	String class_id;
	String class_name;
	String class_avail;

	String item_id;
	String item_img;
	String item_name;
	String item_desc;
	String item_price;
	String item_class_id;
	String item_cookable;
	String item_e_time;

	public MenuCategory(String class_id, String class_name, String class_avail,
			String item_id, String item_img, String item_name,
			String item_desc, String item_price, String item_class_id,
			String item_cookable, String item_e_time) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.class_avail = class_avail;
		this.item_id = item_id;
		this.item_img = item_img;
		this.item_name = item_name;
		this.item_desc = item_desc;
		this.item_price = item_price;
		this.item_class_id = item_class_id;
		this.item_cookable = item_cookable;
		this.item_e_time = item_e_time;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getClass_avail() {
		return class_avail;
	}

	public void setClass_avail(String class_avail) {
		this.class_avail = class_avail;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_img() {
		return item_img;
	}

	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public String getItem_price() {
		return item_price;
	}

	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}

	public String getItem_class_id() {
		return item_class_id;
	}

	public void setItem_class_id(String item_class_id) {
		this.item_class_id = item_class_id;
	}

	public String getItem_cookable() {
		return item_cookable;
	}

	public void setItem_cookable(String item_cookable) {
		this.item_cookable = item_cookable;
	}

	public String getItem_e_time() {
		return item_e_time;
	}

	public void setItem_e_time(String item_e_time) {
		this.item_e_time = item_e_time;
	}
}
