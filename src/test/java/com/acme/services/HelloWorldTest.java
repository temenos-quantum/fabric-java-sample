package com.acme.services;

import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class HelloWorldTest {

	@org.testng.annotations.BeforeMethod
	public void setUp() throws Exception {
	}

	@org.testng.annotations.AfterMethod
	public void tearDown() throws Exception {
	}

	@org.testng.annotations.Test
	public void testInvoke() throws Exception {

		//Mock the request.
		DataControllerRequest request = mock(DataControllerRequest.class);
		HashMap<String, Object> headers = (HashMap<String, Object>)request.getHeaderMap();

		//Mock the response.
		DataControllerResponse response = mock(DataControllerResponse.class);

		HashMap<String, Object> cfgMap = new HashMap <String, Object>(); //Configurations.
		HashMap<String, Object> inMap = new HashMap <String, Object>(); //Input parameters.
		HashMap<String, Object> outMap = new HashMap <String, Object>(); //Output parameters.
		Object[] maps = new Object[]{cfgMap, inMap, outMap};

		HelloWorld hw = new HelloWorld();
		Result result = hw.invoke("doFoo", maps, request, response);
		assertEquals(result.getParamByName("message").getValue(), "Hello World!");
	}
}