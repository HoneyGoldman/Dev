package Client;

import java.util.List;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Dao.CouponDBDAO;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CouponExpirationDailyJob  extends HttpServlet implements Runnable{

	private boolean isOn;
	private CouponDBDAO couponDao = new CouponDBDAO();
	private Thread thread=new Thread();
	
	


	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	@Override
	public void run() {
		System.out.println("Job Started");
		isOn = true;
		while (isOn) {
			try {
				List<Coupon> expiredCoupons = couponDao.getAllExpiredCoupons();
				for (Coupon coupon : expiredCoupons) {
					couponDao.removeCoupon(coupon.getId());
				}
				System.out.println(" Found Expired Coupons" + expiredCoupons.toString());
			} catch (CouponSystemException e1) {
				e1.printStackTrace();
				
			}
			try {
				System.out.println("waiting for tomorrow");
				Thread.sleep(1000 * 60 * 60 * 24);
			

			} catch (InterruptedException e) {
				break;
			}
		}

		System.out.println("Job Stopped");
	}

	public void stopJob() {
		isOn = false;
		thread.interrupt();
	}

}
