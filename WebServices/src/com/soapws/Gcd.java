package com.soapws;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="GcdResponseforMethods")
public class Gcd {


	
	private int gcd;
	private List<Integer> gcdList;
	private int gcdSum;
	
	
	public int getGcd() {
		return gcd;
	}
	public void setGcd(int gcd) {
		this.gcd = gcd;
	}
	public List<Integer> getGcdList() {
		return gcdList;
	}
	public void setGcdList(List<Integer> gcdList) {
		this.gcdList = gcdList;
	}
	public int getGcdSum() {
		return gcdSum;
	}
	public void setGcdSum(int gcdSum) {
		this.gcdSum = gcdSum;
	}
	
	

	
}
