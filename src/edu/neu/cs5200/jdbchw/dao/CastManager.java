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

import edu.neu.cs5200.jdbchw.model.Cast;

public class CastManager {

	DataSource ds;
	
	public CastManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	
	//create cast
	public void createCast(Cast newCast) {
		String sql = "insert into cast values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getActorId());
			statement.setInt(3, newCast.getMovieId());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	//retrieve all casts
	public List<Cast> readAllCast() {
		List<Cast>	casts = new ArrayList<Cast>();
		String sql = "select * from cast";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieId(results.getInt("movieId"));
				cast.setActorId(results.getInt("actorId"));
				casts.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return casts;
	}
	
	//retrieve all casts for actor
	public List<Cast> readAllCastForActor(int actorId) {
		List<Cast>	casts = new ArrayList<Cast>();
		String sql = "select * from cast where cast.actorId=?";
		
		try {
			
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet results = stmt.executeQuery();
		
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieId(results.getInt("movieId"));
				cast.setActorId(results.getInt("actorId"));
				casts.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return casts;
	}
	
	//retrieve all casts for movie
	public List<Cast> readAllCastForMovie(String movieId) {
		List<Cast>	casts = new ArrayList<Cast>();
		String sql = "select * from cast where cast.movieId=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, movieId);
			ResultSet results = stmt.executeQuery();
		
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieId(results.getInt("movieId"));
				cast.setActorId(results.getInt("actorId"));
				casts.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return casts;
	}
	
	
	//retrieve a cast by id - method signature changed to match id type
	public Cast readCastForId(int castId) {
		Cast cast = new Cast();
		String sql = "select * from cast where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, castId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				cast.setId(result.getInt("id"));
				cast.setCharacterName(result.getString("characterName"));
				cast.setMovieId(result.getInt("movieId"));
				cast.setActorId(result.getInt("actorId"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return cast;
		
	}
	
	
	//update a cast by id
	
	public void updateCast(int castId, Cast newCast) {

		String sql = "update cast set characterName=?, movieId=?, actorId=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getMovieId());
			statement.setInt(3, newCast.getActorId());
			statement.setInt(4, newCast.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//delete a cast by id
	public void deleteCast(int castId) {
		String sql = "delete from cast where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, castId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
