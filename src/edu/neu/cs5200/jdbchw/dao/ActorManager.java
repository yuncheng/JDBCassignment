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

import edu.neu.cs5200.jdbchw.model.Actor;

public class ActorManager {

	DataSource ds;
	
	public ActorManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	
	//create actor
	public void createActor(Actor newActor) {
		String sql = "insert into actor values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getFirstName());
			statement.setString(2, newActor.getLastName());
			statement.setDate(3, newActor.getDateOfBirth());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	//retrieve all actors
	public List<Actor> readAllActors() {
		List<Actor>	actors = new ArrayList<Actor>();
		String sql = "select * from actor";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Actor actor = new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actors.add(actor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actors;
	}
	
	//retrieve a actor by id - method signature changed to match id type
	public Actor readActor(int actorId) {
		Actor actor = new Actor();
		String sql = "select * from actor where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				actor.setId(result.getInt("id"));
				actor.setFirstName(result.getString("firstName"));
				actor.setLastName(result.getString("lastName"));
				actor.setDateOfBirth(result.getDate("dateOfBirth"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return actor;
		
	}
	
	
	//update a actor by id - method signature changed to match actorId type
	
	public void updateActor(int actorId, Actor actor) {

		String sql = "update actor set firstName=?, lastName=?, dateOfBirth=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.setInt(4, actorId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//delete a actor by id
	public void  deleteActor(int actorId) {
		String sql = "delete from actor where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
