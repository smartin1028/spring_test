package com.spring_test.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CalcSumTest {

	@DisplayName("파일의 숫자 합을 계산하는 코드 테스트")
	@Test
	public void _V1() throws Exception {

		Calculator calculator = new Calculator();
		// '/'가 없으면 현재위치부터 찾음
		String findFile = "/numbers.txt";
		URL resource = getClass().getResource(findFile);
		System.out.println("resource = " + resource);

		System.out.println(getClass().getResource(findFile).getPath());
		int sum = calculator.calcSum(getClass().getResource(findFile).getPath());
		Assertions.assertThat(sum).isEqualTo(10);
	}


}