package com.example.project;

public class MenuCategoryItem {
	String id = null;

	public MenuCategoryItem(String id, String img, String name,
			String description, String price, String class_id, String cookable,
			String e_time) {
		super();
		this.id = id;
		this.img = img;
		this.name = name;
		this.description = description;
		this.price = price;
		this.class_id = class_id;
		this.cookable = cookable;
		this.e_time = e_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getCookable() {
		return cookable;
	}

	public void setCookable(String cookable) {
		this.cookable = cookable;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String e_time) {
		this.e_time = e_time;
	}

	String img = null;
	String name = null;
	String description = null;
	String price = null;
	String class_id = null;
	String cookable = null;
	String e_time = null;
}
