package Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.couponManagmentSystem.Dao.CommonDAO;
import com.couponManagmentSystem.Dao.ConnectionPool;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CommonFacade {
	
	static CommonDAO commonDao=new CommonDAO();
	public static String getCategory(int categoryId) throws CouponSystemException {
		return commonDao.getCategory(categoryId);
		
	}
	public static List<String> getCategories() throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			ArrayList<String> categories=new ArrayList<>();
			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT * FROM categories");
			while (rs.next()) {
				categories.add(rs.getString("name"));
			}

			ConnectionPool.getInstance().restoreConnection(con);
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed to get Categories (from categories getter)",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

}
