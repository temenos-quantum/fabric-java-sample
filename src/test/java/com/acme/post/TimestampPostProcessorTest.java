package com.acme.post;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddDatePostProcessorTest {

	@Test(testName = "Test timestamp", enabled=true)
	public void testExecute() throws Exception {
		AddDatePostProcessor ts = new AddDatePostProcessor();
		//ts.execute(result, request, response);
	}
}