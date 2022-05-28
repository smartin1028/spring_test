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


}