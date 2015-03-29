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

import edu.neu.cs5200.jdbchw.model.Comment;

public class CommentManager {

	DataSource ds;
	
	public CommentManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	
	//create comment
	public void createComment(Comment newComment) {
		String sql = "insert into comment values (null,?,?)";
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
	public List<Comment> readAllComments() {
		List<Comment>	comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	//retrieve all comments for user
	public List<Comment> readAllCommentsForUsername(String username) {
		List<Comment>	comments = new ArrayList<Comment>();
		String sql = "select * from comment, user where comment.userId=username";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserId(results.getString("userId"));
				comment.setMovieId(results.getInt("movieId"));
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	//retrieve all comments for movie
	public List<Comment> readAllCommentsForMovie(String movieId) {
		List<Comment>	comments = new ArrayList<Comment>();
		String sql = "select * from comment, movie where comment.movieId=movieId";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserId(results.getString("userId"));
				comment.setMovieId(results.getInt("movieId"));
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	
	//retrieve a comment by id - method signature changed to match id type
	public Comment readCommentForId(int commentId) {
		Comment comment = new Comment();
		String sql = "select * from comment where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				comment.setId(result.getInt("id"));
				comment.setComment(result.getString("comment"));
				comment.setDate(result.getDate("date"));
				comment.setUserId(result.getString("userId"));
				comment.setMovieId(result.getInt("movieId"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return comment;
		
	}
	
	
	//update a comment by id
	
	public void updateComment(String commentId, String newComment) {

		String sql = "update comment set comment=? where id=?";
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
	
	//delete a comment by id
	public void deleteComment(int commentId) {
		String sql = "delete from comment where id=?";
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
