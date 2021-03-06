package edu.neu.cs5200.jdbchw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.jdbchw.model.Movie;

public class MovieManager {

	DataSource ds;
	
	public MovieManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	
	//create movie
	public void createMovie(Movie newMovie) {
		String sql = "insert into movie values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getTitle());
			statement.setString(2, newMovie.getPosterImage());
			statement.setDate(3, newMovie.getReleaseDate());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	//retrieve all movies
	public List<Movie> readAllMovies() {
		List<Movie>	movies = new ArrayList<Movie>();
		String sql = "select * from movie";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Movie movie = new Movie();
				movie.setId(results.getInt("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				movies.add(movie);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movies;
	}
	
	//retrieve a movie by id - method signature changed to match id type
	public Movie readMovie(int id) {
		Movie movie = new Movie();
		String sql = "select * from movie where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				movie.setId(result.getInt("id"));
				movie.setTitle(result.getString("title"));
				movie.setPosterImage(result.getString("posterImage"));
				movie.setReleaseDate(result.getDate("releaseDate"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return movie;
		
	}
	
	
	//update a movie by id
	
	public void updateMovie(int id, Movie movie) {

		String sql = "update movie set title=?, posterImage=?, releaseDate=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movie.getReleaseDate());
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//delete a movie by id
	public void deleteMovie(int id) {
		String sql = "delete from movie where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
