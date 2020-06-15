package com.acme.services;

import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ColorServiceTest {

	DataControllerRequest request;
	HashMap<String, Object> headers;

	DataControllerResponse response;

	HashMap<String, Object> cfgMap;
	HashMap<String, Object> inMap;
	HashMap<String, Object> outMap;
	Object[] maps;

	Pattern p = Pattern.compile("#[0-9a-fA-F]{6}");

	@BeforeTest
	public void prepare(){

		//Let's initialise the request, response, config, inputs, outputs and headers to pretend this is a Fabric runtime.

		//Mock the request.
		request = mock(DataControllerRequest.class);
		headers = (HashMap<String, Object>)request.getHeaderMap();

		//Mock the response.
		response = mock(DataControllerResponse.class);

		cfgMap = new HashMap <String, Object>(); //Configurations.
		inMap = new HashMap <String, Object>(); //Input parameters.
		outMap = new HashMap <String, Object>(); //Output parameters.
		maps = new Object[]{cfgMap, inMap, outMap};
	}

	@Test(testName = "Test color", enabled=true)
	public void testGetColor() throws Exception {

		ColorService service = new ColorService();
		Result result = service.invoke("getColor", maps, request, response);
		String hex = result.getParamByName("color").getValue();
		Matcher m = p.matcher(hex);

		assertTrue(m.matches());
	}

	@Test(testName = "Test pastel", enabled=true)
	public void testGetPastel() throws Exception {

		ColorService service = new ColorService();
		Result result = service.invoke("getPastel", maps, request, response);

		String hex = result.getParamByName("color").getValue();
		Matcher m = p.matcher(hex);

		assertTrue(m.matches());
	}

	@Test(testName = "Test undefined", enabled=true)
	public void testUndefined() throws Exception {

		ColorService hw = new ColorService();
		Result result = hw.invoke("", maps, request, response);
		assertEquals(result.getParamByName("message").getValue(), "No such operation!");
	}
}