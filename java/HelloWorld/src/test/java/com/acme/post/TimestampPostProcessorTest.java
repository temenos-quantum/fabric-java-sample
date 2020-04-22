package com.acme.post;

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

public class TimestampPostProcessorTest {

	DataControllerRequest request;
	DataControllerResponse response;

	@BeforeTest
	public void prepare(){

		//Let's initialise the request and response to pretend this is a Fabric runtime.

		//Mock the request.
		request = mock(DataControllerRequest.class);

		//Mock the response.
		response = mock(DataControllerResponse.class);
	}

	@Test(testName = "Test timestamp", enabled=true)
	public void testTimestamp() throws Exception {
		TimestampPostProcessor tsp = new TimestampPostProcessor();
		Result result = tsp.execute(new Result(), request, response);
		String timeStamp = new SimpleDateFormat(TimestampPostProcessor.TIMESTAMP_FORMAT).format(new Date());
		assertEquals(result.getParamByName("timestamp").getValue(), timeStamp);
	}
}