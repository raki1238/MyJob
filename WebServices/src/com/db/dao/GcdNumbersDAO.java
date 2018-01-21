package com.db.dao;

import java.util.List;

import com.db.models.GcdNumbers;

public interface GcdNumbersDAO {

	public String storeNumsAndGcd(GcdNumbers gcdNumbers);
	public List<GcdNumbers> getGcdNumbers();
	public List<Integer> getGcdValues();
	public int gcdSum();

}
