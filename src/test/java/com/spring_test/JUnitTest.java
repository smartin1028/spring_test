package com.spring_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class JUnitTest {
	static JUnitTest jUnitTest;
	static Set<JUnitTest> testObjects = new HashSet<>();

	@Test
	public void test01() throws Exception {
		assertThat(this).isNotSameAs(jUnitTest);
		assertThat(testObjects).doesNotHaveSameClassAs(this);
		testObjects.add(jUnitTest);
		jUnitTest = this;
	}

	@Test
	public void test02() throws Exception {
		assertThat(this).isNotSameAs(jUnitTest);
		assertThat(testObjects).doesNotHaveSameClassAs(this);
		testObjects.add(jUnitTest);


		System.out.println("jUnitTest = " + jUnitTest);
		System.out.println("this = " + this);
		jUnitTest = this;
	}

	@Test
	public void test03() throws Exception {
		assertThat(this).isNotSameAs(jUnitTest);
		assertThat(testObjects).doesNotHaveSameClassAs(this);
		testObjects.add(jUnitTest);

		System.out.println("jUnitTest = " + jUnitTest);
		System.out.println("this = " + this);
		jUnitTest = this;
	}


}
