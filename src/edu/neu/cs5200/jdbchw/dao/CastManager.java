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
			statement.setString(1, newComment.getComment());
			statement.setDate(2, newComment.getDate());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	//retrieve all comments
	public List<Cast> readAllComments() {
		List<Cast>	comments = new ArrayList<Cast>();
		String sql = "select * from cast";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setComment(results.getString("cast"));
				cast.setDate(results.getDate("date"));
				comments.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	//retrieve all comments for user
	public List<Cast> readAllCommentsForUsername(String username) {
		List<Cast>	comments = new ArrayList<Cast>();
		String sql = "select * from cast, user where cast.userId=username";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setComment(results.getString("cast"));
				cast.setDate(results.getDate("date"));
				cast.setUserId(results.getString("userId"));
				cast.setMovieId(results.getInt("movieId"));
				comments.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	//retrieve all comments for movie
	public List<Cast> readAllCommentsForMovie(String movieId) {
		List<Cast>	comments = new ArrayList<Cast>();
		String sql = "select * from cast, movie where cast.movieId=movieId";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setComment(results.getString("cast"));
				cast.setDate(results.getDate("date"));
				cast.setUserId(results.getString("userId"));
				cast.setMovieId(results.getInt("movieId"));
				comments.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	
	//retrieve a cast by id - method signature changed to match id type
	public Cast readCommentForId(int commentId) {
		Cast cast = new Cast();
		String sql = "select * from cast where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				cast.setId(result.getInt("id"));
				cast.setComment(result.getString("cast"));
				cast.setDate(result.getDate("date"));
				cast.setUserId(result.getString("userId"));
				cast.setMovieId(result.getInt("movieId"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return cast;
		
	}
	
	
	//update a cast by id
	
	public void updateComment(String commentId, String newComment) {

		String sql = "update cast set cast=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setString(2, commentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//delete a cast by id
	public void deleteComment(int commentId) {
		String sql = "delete from cast where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
