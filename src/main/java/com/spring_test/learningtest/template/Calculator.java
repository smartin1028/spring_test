package com.spring_test.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public Integer calcSumV1(String filePath) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			Integer sum = 0;
			String line;
			while ((line = br.readLine()) != null) {
				sum += Integer.valueOf(line);
			}
			return sum;
		} catch (IOException e) {
			System.out.println("e.getMessage() = " + e.getMessage());
			throw e;
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException io) {
			}
		}
	}

	public Integer calcSum(String filePath) throws IOException {
//		LineCallback sumCallback = (line, value) -> value + Integer.valueOf(line)
		return lineReadTemplate(filePath,(line, value) -> value + Integer.valueOf(line), 0);
	}
	public Integer calcSumV2(String filePath) throws IOException {
//		BufferedReaderCallback sumCallBack = br -> {
//			Integer sum = 0;
//			String line;
//			while ((line = br.readLine()) != null) {
//				sum += Integer.valueOf(line);
//			}
//			return sum;
//		};
		return calcSum(filePath, br -> {
			Integer sum = 0;
			String line;
			while ((line = br.readLine()) != null) {
				sum += Integer.valueOf(line);
			}
			return sum;
		});
	}

	public Integer calcMultiply(String filePath) throws IOException {
		// tests
		return lineReadTemplate(filePath,(line, value) -> value * Integer.valueOf(line), 1);

	}
	public Integer calcMultiplyV1(String filePath) throws IOException {
		return calcSum(filePath, br -> {
			Integer multiply = 1;
			String line;
			while ((line = br.readLine()) != null) {
				multiply *= Integer.valueOf(line);
			}
			return multiply;
		});
	}

	public Integer calcSum(String filePath, BufferedReaderCallback callback) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			Integer sum = callback.doSomethingWithReader(br);
			return sum;
		} catch (IOException e) {
			System.out.println("e.getMessage() = " + e.getMessage());
			throw e;
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException io) {
			}
		}
	}


	public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			Integer res = initVal;
			String line;
			while ((line = br.readLine()) != null) {
				res = callback.doSomeThingWithLine(line, res);
			}
			return res;
		} catch (IOException e) {
			System.out.println("e.getMessage() = " + e.getMessage());
			throw e;
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException io) {
			}
		}
	}

}
