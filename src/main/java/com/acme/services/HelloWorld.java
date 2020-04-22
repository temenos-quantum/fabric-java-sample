package com.acme.services;

//import com.google.gson.Gson;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorld implements JavaService2{

	private static final Logger LOGGER = Logger.getLogger( HelloWorld.class.getName() );

	public Result invoke(String opId, Object[] maps, DataControllerRequest request, DataControllerResponse response){

		Result result = new Result();
		try{
			Param message = new Param("message", "Hello World!", "string");
			result.addParam(message);
		}
		catch (Exception e){

			LOGGER.log(Level.SEVERE, "Hello world failed.\n\t" +
							"due to exception: {1}\n\t" +
							"with message: {2}\n\t" +
							"Stack trace: {3}",
					new Object[]{
							opId,
							e.getClass(),
							e.getMessage(),
							ExceptionUtils.getStackTrace(e)
					}
			);
		}
		finally {
			return result;
		}
	}
}
