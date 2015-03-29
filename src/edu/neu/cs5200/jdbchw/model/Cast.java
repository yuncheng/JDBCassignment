package edu.neu.cs5200.jdbchw.model;

public class Cast {
	
	private int id;
	private String characterName;
	private int movieId;
	private int actorId;

	public Cast() {
		super();
	}

	public Cast(int id, String characterName, int movieId, int actorId) {
		super();
		this.id = id;
		this.characterName = characterName;
		this.movieId = movieId;
		this.actorId = actorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}


}
