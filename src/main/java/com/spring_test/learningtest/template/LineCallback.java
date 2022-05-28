package com.spring_test.learningtest.template;

import org.springframework.data.relational.core.sql.In;

public interface LineCallback {
	Integer doSomeThingWithLine(String line, Integer value);
}
