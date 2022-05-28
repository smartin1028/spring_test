package com.spring_test.test;

import com.spring_test.learningtest.template.Calculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalcSumTest {

	Calculator calculator;
	String numFilePath;

	@BeforeEach
	public void beforeEach() {
		calculator = new Calculator();
		// '/'가 없으면 현재위치부터 찾음
		String findFile = "/numbers.txt";
		numFilePath = getClass().getResource(findFile).getPath();
	}

	@DisplayName("파일의 숫자 합을 계산하는 코드 테스트")
	@Test
	public void _V1() throws Exception {
		Assertions.assertThat(calculator.calcSum(numFilePath)).isEqualTo(10);
	}

	@DisplayName("파일의 숫자 합을 계산하는 코드 테스트 - v2")
	@Test
	public void _V2() throws Exception {
		Assertions.assertThat(calculator.calcSumV1(numFilePath)).isEqualTo(10);
	}

	@DisplayName("곱을 계산하는 콜백을 가진 calcMutiply")
	@Test
	public void multiply_V1() throws Exception {
		Assertions.assertThat(calculator.calcMultiply(numFilePath)).isEqualTo(24);
	}
	@DisplayName("문자열 합치기")
	@Test
	public void concatenate_V1() throws Exception {
		Assertions.assertThat(calculator.concatenate(numFilePath)).isEqualTo("1234");
	}


}