package de.lacodev.staffcore.extensionshook.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class MySQL {
	

	public static String HOST;
	public static String PORT;
	public static String DATABASE;
	public static String USER;
	public static String PASSWORD;
	private Connection con;
	
    private static String mysql = "§cSC-Extensions §8(§7MySQL§8) §8- ";
	  
	public MySQL(String host, String database, String user, String password) {
		
		HOST = host;
	    DATABASE = database;
	    USER = user;
	    PASSWORD = password;
	    
	    connect();
	}

	public void connect() {
		
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":"+ PORT +"/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
			
			Bukkit.getConsoleSender().sendMessage(mysql + "§aSuccessfully §8connected to database §a" + DATABASE);
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(mysql + "§cFailed to §8connect to database §c" + DATABASE);
			Bukkit.getConsoleSender().sendMessage(mysql + "§cErrorCode: §8" + e.getSQLState() + ":" + e.getErrorCode());
		}
		
	}
	
	public void close() {
		
		try {
			if (this.con != null) {
		        this.con.close();
		    }
		} catch(SQLException e) {
			Bukkit.getConsoleSender().sendMessage(mysql + "§cErrorCode: §7" + e.getSQLState() + ":" + e.getErrorCode());
		}
		
	}
	
	public boolean isConnected() {
		return con == null ? false : true;
	}
	
	public void update(String qry) {
		try {
	      Statement st = this.con.createStatement();
	      st.executeUpdate(qry);
	      st.close();
	    } catch (SQLException e) {
	      connect();
	      Bukkit.getConsoleSender().sendMessage(mysql + "§cErrorCode: §7" + e.getSQLState() + ":" + e.getErrorCode());
	    }
	}
	
	public void updateTables(String qry) {
		try {
	      Statement st = this.con.createStatement();
	      st.executeUpdate(qry);
	      st.close();
	    } catch (SQLException e) {
	      
	    }
	}
	
	public ResultSet query(String qry) {
		ResultSet rs = null;
		try {
			Statement st = this.con.createStatement();
			rs = st.executeQuery(qry);
		} catch (SQLException e) {
			connect();
			Bukkit.getConsoleSender().sendMessage(mysql + "§cErrorCode: §7" + e.getSQLState() + ":" + e.getErrorCode());
		}
		return rs;
	}
	
}
