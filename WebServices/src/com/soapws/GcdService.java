package com.soapws;

import java.util.List;

import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(name="GcdProcessor")
public interface GcdService {

	public @WebResult(name="GreatestCommonDivisor") int gcd();
	public @WebResult(name="GcdVal") List<Integer> gcdList();
	public @WebResult(name="SumOfAllGcds") int gcdSum();
	
	
}
