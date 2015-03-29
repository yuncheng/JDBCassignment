package edu.neu.cs5200.jdbchw.model;

import java.sql.Date;

public class Comment {
	private int id;
	private String comment;
	private Date date;
	private String userId;
	private int movieId;
	
	public Comment() {
		super();
	}

	public Comment(int id, String comment, Date date, String userId, int movieId) {
		super();
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.movieId = movieId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


}
