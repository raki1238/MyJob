package com.soapws;

import java.util.List;

import com.db.dao.GcdNumbersDAOImpl;
import com.google.common.base.Optional;
import com.utils.CalculateGcd;

public class GcdServiceImpl implements GcdService {

	@Override
	public int gcd() {
		// Fetch from JMS
		System.out.println("Executing Message Receiver");
		ReceiveMessageServlet receiveMessageServlet = new ReceiveMessageServlet();
		Optional<String> receiveMessages =  receiveMessageServlet.receiveMessages();
		String firstValue = receiveMessages.get();
		if(firstValue.contains(":")) {
			String[] split = firstValue.split(":");
			int a = Integer.parseInt(split[0]);
			int b= Integer.parseInt(split[1]);
			CalculateGcd gcd = new CalculateGcd(a, b);
			return gcd.calGcdValue();
		}
		return 0;
	}

	@Override
	public List<Integer> gcdList() {
		//fetch from databse
		GcdNumbersDAOImpl impl = new GcdNumbersDAOImpl();		
		List<Integer> gcdNumbers = impl.getGcdValues();
		return gcdNumbers;

	}

	@Override
	public int gcdSum() {
		// fetch from database and sumup
		GcdNumbersDAOImpl impl = new GcdNumbersDAOImpl();		
		return impl.gcdSum();
		
	}

}
