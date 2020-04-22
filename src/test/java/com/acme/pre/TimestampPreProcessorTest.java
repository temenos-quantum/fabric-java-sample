package com.acme.pre;

import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class TimestampPreProcessorTest {

	DataControllerRequest request;
	DataControllerResponse response;
	HashMap<String, Object> inMap;
	Result result;

	@BeforeTest
	public void prepare(){

		//Let's initialise the request and response to pretend this is a Fabric runtime.

		//Mock the request.
		request = mock(DataControllerRequest.class);

		//Mock the response.
		response = mock(DataControllerResponse.class);

		inMap = new HashMap <String, Object>(); //Input parameters.
		result = new Result();
	}

	@Test(testName = "Test username input", enabled=true)
	public void testTimestampInput() throws Exception {
		TimestampPreProcessor tsp = new TimestampPreProcessor();
		String timeStamp = new SimpleDateFormat(TimestampPreProcessor.TIMESTAMP_FORMAT).format(new Date());
		boolean success = tsp.execute(inMap, request, response, result);
		assertEquals(success, true);
		assertEquals(inMap.get("timestamp"), timeStamp);
	}

	@Test(testName = "Test username header", enabled=false)
	public void testTimestampHeader() throws Exception {
		TimestampPreProcessor tsp = new TimestampPreProcessor();
		String timeStamp = new SimpleDateFormat(TimestampPreProcessor.TIMESTAMP_FORMAT).format(new Date());
		boolean success = tsp.execute(inMap, request, response, result);
		assertEquals(success, true);

		//TODO: Figure out why the headers don't seem to be updated.
		assertEquals(request.getHeaderMap().get("x-timestamp"), timeStamp);
	}
}