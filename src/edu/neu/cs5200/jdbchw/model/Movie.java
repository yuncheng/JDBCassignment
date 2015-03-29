package edu.neu.cs5200.jdbchw.model;

import java.sql.Date;
import java.util.List;

public class Movie {
	private int id; //changed id type to int because String unnecessary
	private String title;
	private String posterImage;
	private Date releaseDate;
	
	private List<User> likes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<User> getLikes() {
		return this.likes;
	}
	
	public void setLikes(List<User> l) {
		this.likes = l;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterImage() {
		return posterImage;
	}
	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	
	public Movie() {
		super();
	}
	public Movie(int id, String title, String posterImage, Date releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}

	
	
	
}
