package com.couponManagmentSystem.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CompanyDBDAO implements CompaniesDAO {
	private static CustomerDBDAO customerDAO=new CustomerDBDAO();
	@Override
	public boolean isCompanyExists(String email, String password) throws CouponSystemException {
		System.out.println("is company exist");
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			System.out.println("got connection");
			String sqlIsExists = "select * from companies where email ='" + email + " ' and password=' " + password
					+ "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlIsExists);
			if (rs != null) {
				ConnectionPool.getInstance().restoreConnection(con);
				return true;
			}

			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {
			throw new CouponSystemException("Company does't Exist",e);
		}
		return false;

	}

	@Override
	public void addCompany(Company company) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			String sqlAddCompany = "INSERT INTO companies (name, email, password) VALUES(?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(sqlAddCompany);

			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ConnectionPool.getInstance().restoreConnection(con);
			e.printStackTrace();
			throw new CouponSystemException("Failed to Add Company",e);

		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
	}

	@Override
	public void updateCompany(Company company) throws CouponSystemException {
		if (!isCompanyExists(company.getEmail(), company.getPassword())) {
			throw new CouponSystemException("the company doesn't exists");
		} else {
			Connection con = ConnectionPool.getInstance().getConnection();
			try {
				String sqlUpdateCompany = "update companies set name=? , email=?, password=? where id=?";

				PreparedStatement pstmt = con.prepareStatement(sqlUpdateCompany);
				pstmt.setString(1, company.getName());
				pstmt.setString(2, company.getEmail());
				pstmt.setString(3, company.getPassword());
				pstmt.setInt(4, company.getId());

				pstmt.executeUpdate();
			} catch (SQLException e) {

				ConnectionPool.getInstance().restoreConnection(con);
				e.printStackTrace();
				throw new CouponSystemException("Failed to update Company",e);
			} finally {
				ConnectionPool.getInstance().restoreConnection(con);

			}
		}

	}

	@Override
	public void deleteCompany(int companyID) throws CouponSystemException {
		Company company = getCompany(companyID);
		Connection con = ConnectionPool.getInstance().getConnection();
		if (!isCompanyExists(company.getEmail(), company.getPassword())) {
			throw new CouponSystemException("the company doesn't exists");
		} else {
			try {
				String sqlDeleteCompany = "delete from companies where id='" + companyID + "'";
				Statement stmt = con.createStatement();
				stmt.execute(sqlDeleteCompany);
				ConnectionPool.getInstance().restoreConnection(con);
			} catch (SQLException e) {
				ConnectionPool.getInstance().restoreConnection(con);
				throw new CouponSystemException("Failed To Delete Company",e);
			} finally {
				ConnectionPool.getInstance().restoreConnection(con);

			}
		}
	}

	@Override
	public ArrayList<Company> getAllCompanies() throws CouponSystemException {
		ArrayList<Company> allCompanies = new ArrayList<Company>();
		String sqlGetAllCompanies = "select * from companies";

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetAllCompanies);

			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setEmail(rs.getString("email"));
				company.setName(rs.getString("name"));
				company.setPassword(rs.getString("password"));

				allCompanies.add(company);

			}

		} catch (SQLException e) {
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed To get all the Companies",e);

		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}

		return allCompanies;
	}

	@Override
	public Company getCompany(int companyID) throws CouponSystemException {
		return companyGetter("select * from companies where id=" + companyID);

	}

	@Override
	public Company getCompany(String email) throws CouponSystemException {
		return companyGetter("select * from companies where email= '" + email);
	}

	public Company getCompany(String email, String password) throws CouponSystemException {
		return companyGetter("select * from companies where email= '" + email + "' and password='" + password + "'");
	}

	@Override
	public Boolean login(String email, String password) throws CouponSystemException {

		return isCompanyExists(email, password);
	}

	@Override
	public Company companyGetter(String sql) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Company temp = new Company();
			while (rs.next()) {

				temp.setId(rs.getInt("id"));
				temp.setEmail(rs.getString("email"));
				temp.setName(rs.getString("name"));
				temp.setPassword(rs.getString("password"));
			}
			ConnectionPool.getInstance().restoreConnection(con);
			return temp;
		} catch (Exception e) {
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("cant get company- SQL statment=" + sql,e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}
	@Override
	public Company getCompanyByEmail(String email) throws CouponSystemException {
		return companyGetter("SELECT * FROM companies where email='" + email + "'");
	}
	@Override
	public Company getCompanyByName(String name) throws CouponSystemException {
		return companyGetter("SELECT * FROM companies where name='" + name + "'");
	}
	@Override
	public  int getCategory(Category category) throws CouponSystemException {
	 return	customerDAO.categoryGetter(category);
	}
	
	
	
}
