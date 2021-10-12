package com.stackroute.favouriteservice.model;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	String id;
	String bookkey;
	int userId;
	String title;
	String authorskey;
	String authorsname;

	public Book() {
	}

	public Book(String id, String bookkey, int userId, String title, String authorskey, String authorsname) {
		super();
		this.id = id;
		this.bookkey = bookkey;
		this.userId = userId;
		this.title = title;
		this.authorskey = authorskey;
		this.authorsname = authorsname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookkey() {
		return bookkey;
	}

	public void setBookkey(String bookkey) {
		this.bookkey = bookkey;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorskey() {
		return authorskey;
	}

	public void setAuthorskey(String authorskey) {
		this.authorskey = authorskey;
	}

	public String getAuthorsname() {
		return authorsname;
	}

	public void setAuthorsname(String authorsname) {
		this.authorsname = authorsname;
	}

}
