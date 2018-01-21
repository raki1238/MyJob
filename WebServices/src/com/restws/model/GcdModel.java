package com.restws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GcdModel {
		
	private Integer number1;
	private Integer number2;

	public Integer getNumber1() {
		return number1;
	}

	public void setNumber1(Integer number1) {
		this.number1 = number1;
	}

	public Integer getNumber2() {
		return number2;
	}

	public void setNumber2(Integer number2) {
		this.number2 = number2;
	}


}
