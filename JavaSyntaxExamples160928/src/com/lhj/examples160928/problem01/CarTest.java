// CarTest.java
package com.lhj.examples160928.problem01;

public class CarTest {
	public static void main(String[] args) {
		
		Car myCar = new Car();
		
		System.out.println("제작 회사: " + myCar.company);
		System.out.println("모델 명: " + myCar.model);
		System.out.println("색깔: " + myCar.color);
		System.out.println("최고 속도: " + myCar.maxSpeed);
		System.out.println("현재 속도: " + myCar.speed);
		
		myCar.speed = 60;
		System.out.println("수정된 속도: " + myCar.speed);
	}
}