package com.ivy.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Book implements Serializable{
	
	private long bookId;// 图书ID

	private String name;// 图书名称

	private int number;// 馆藏数量

	
/*	public Book(long bookId, String name, int number) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.number = number;
	}*/


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", number=" + number + "]";
	}

	// 省略构造方法，getter和setter方法，toString方法

}
