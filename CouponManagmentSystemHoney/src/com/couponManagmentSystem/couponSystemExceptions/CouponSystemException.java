package com.couponManagmentSystem.couponSystemExceptions;

public class CouponSystemException extends Exception {

	/**
	 * this class define the coupons system exceptions*
	 */
	private static final long serialVersionUID = 1L;

	public CouponSystemException() {
		super();
	}

	public CouponSystemException(String error) {
		super(error);
	}

	public CouponSystemException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public CouponSystemException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CouponSystemException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	

}
