package com.spring_test.test;

import com.spring_test.learningtest.template.Calculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalcSumTest {

	@DisplayName("파일의 숫자 합을 계산하는 코드 테스트")
	@Test
	public void _V1() throws Exception {

		Calculator calculator = new Calculator();
		// '/'가 없으면 현재위치부터 찾음
		String findFile = "/numbers.txt";
		String path = getClass().getResource(findFile).getPath();
		int sum = calculator.calcSum(path);
		Assertions.assertThat(sum).isEqualTo(10);

//		System.out.println("path = " + path);
//		System.out.println("sum = " + sum);
	}

	@DisplayName("파일의 숫자 합을 계산하는 코드 테스트 - v2")
	@Test
	public void _V2() throws Exception {

		Calculator calculator = new Calculator();
		String findFile = "/numbers.txt";
		int sum = calculator.calcSumV1(getClass().getResource(findFile).getPath());
		Assertions.assertThat(sum).isEqualTo(10);

//		System.out.println("sum = " + sum);

	}


}