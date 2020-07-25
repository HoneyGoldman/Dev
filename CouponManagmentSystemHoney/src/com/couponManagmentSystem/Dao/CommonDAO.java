package com.couponManagmentSystem.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CommonDAO {
	public String getCategory(int categoryId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql="select * from categories where id='"+categoryId+"'";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String category = null;
			
			while (rs.next()) {
				
			category=rs.getString("name");
			} 
			ConnectionPool.getInstance().restoreConnection(con);
			return category;
			}
		catch (Exception e) {
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("cant get category- SQL statment=" + sql,e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	
	}
}
