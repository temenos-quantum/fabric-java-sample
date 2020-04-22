package com.acme.services;

//import com.google.gson.Gson;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Says hello or goodbye to the world.
 */
public class HelloWorldService implements JavaService2{

	private static final Logger LOGGER = Logger.getLogger( HelloWorldService.class.getName() );

	public Result invoke(String opId, Object[] maps, DataControllerRequest request, DataControllerResponse response){

		Result result = new Result();
		try{

			HashMap<String, Object> inMap = (HashMap<String, Object>)maps[1];
			HashMap<String, Object> headers = (HashMap<String, Object>)request.getHeaderMap();

			//TODO: Need a better mechanism for switching ops than elseif.
			if (opId.equals("sayHello")) result = sayHello(inMap, headers);
			else if(opId.equals("sayGoodbye")) result = sayGoodbye(inMap, headers);
			else {
				Param error = new Param("message", "No such operation!", "string");
				result.addParam(error);
			}
		}
		catch (Exception e){

			LOGGER.log(Level.SEVERE, "Hello world failed.\n\t" +
							"due to exception: {0}\n\t" +
							"with message: {1}\n\t" +
							"Stack trace: {2}",
					new Object[]{
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

	private Result sayHello(HashMap<String, Object> inMap, HashMap<String, Object> headers){
		Result result = new Result();
		Param message = new Param("message", "Hello World!", "string");
		result.addParam(message);
		return result;
	}

	private Result sayGoodbye(HashMap<String, Object> inMap, HashMap<String, Object> headers){
		Result result = new Result();
		Param message = new Param("message", "Goodbye World!", "string");
		result.addParam(message);
		return result;
	}
}
