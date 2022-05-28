package com.spring_test.learningtest.template;

import org.springframework.data.relational.core.sql.In;

public interface LineCallback<T>{
	T doSomeThingWithLine(String line, T value);
}
