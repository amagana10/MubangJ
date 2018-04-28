package com.revature.media;

public class Movie extends Media{
	
	private String producer;

	public Movie (String producer, String title, int yearPublished, String genre) {
		super();
		this.producer = producer;
		this.title = title;
		this.yearPublished = yearPublished;
		this.genre = genre;
	}
	
	public Movie() {
		super();
	}	

	public String getproducer() {
		return producer;
	}

	public void setproducer(String producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "producer: " + this.producer + "title: " + this.title + " yearPublished: " + this.yearPublished +" genre: " + this.genre;
	}
	
	
	
	
}
