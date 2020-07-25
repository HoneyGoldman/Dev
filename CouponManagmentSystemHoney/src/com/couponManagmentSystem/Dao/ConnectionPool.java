package com.couponManagmentSystem.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

/*
 * this class make a single connection pool that keep the max connection number as 10 
 */
public class ConnectionPool {
	private static Set<Connection> connections = new HashSet<>();
	private static int maxConnections = 10;
	private boolean isClose = false;
	private static ConnectionPool instance;

	public static ConnectionPool getInstance() throws CouponSystemException {
		if (instance != null) {
			return instance;
		} else
			return instance = new ConnectionPool();
	}

	String url = "jdbc:mysql://127.0.0.1:3306/couponsystemdb?useSSL=false&allowPublicKeyRetrieval=true";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "Hg234567";

	public static int getMaxConnections() {
		return maxConnections;
	}

	public static void setMaxConnections(int maxConnections) {
		ConnectionPool.maxConnections = maxConnections;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isClose() {
		return isClose;
	}

	private ConnectionPool() throws CouponSystemException {
		// add 10 connections to the set
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			throw new CouponSystemException("class not found");
		}
		for (int i = 0; i < maxConnections; i++) {
			try {
				connections.add(DriverManager.getConnection(url, userName, password));
				connections.iterator().next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				throw new CouponSystemException("could not create connection " + e.getMessage());
			}

		}
		System.out.println("connection pool opened " + connections.size() + " connctions out of " + maxConnections);
	}

	public synchronized Connection getConnection() throws CouponSystemException {
		try {
			while (connections.size() == 0 && !isClose) {
				System.out.println("waiting for connection");
				wait();
			}
		}

		catch (InterruptedException e) {
			throw new CouponSystemException("Failed to get connection");
		}
		Connection availableConnection = ConnectionPool.getConnections().iterator().next();
		getConnections().remove(availableConnection);

		return availableConnection;
	}

	public static Set<Connection> getConnections() {
		return connections;
	}

	public synchronized void restoreConnection(Connection connection) {
		connections.add(connection);
		notifyAll();

	}

	public synchronized void closeAllConnections() throws InterruptedException, SQLException, CouponSystemException {
		try {
			this.isClose = true;
			while (connections.size() < maxConnections) {
				wait();
			}
			
			Iterator<Connection> it = connections.iterator();
			int c = 0;
			while(it.hasNext()) {
				Connection con = it.next();
				con.close();
				it.remove();
				c++;
			}


			System.out.println("connection pool closed " + c + " connctions out of " + maxConnections);
		} catch (Exception e) {
			// TODO: handle exception
			throw new CouponSystemException("could not close all the connections" + e.getMessage());
		}
	}
}
