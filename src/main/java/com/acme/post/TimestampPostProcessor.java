package com.acme.post;

import com.konylabs.middleware.common.DataPostProcessor2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDatePostProcessor implements DataPostProcessor2{
	private static final Logger LOGGER = Logger.getLogger( AddDatePostProcessor.class.getName() );

	public Object execute(Result result, DataControllerRequest request, DataControllerResponse response) throws Exception {
		try{
			String timeStamp = new SimpleDateFormat("yyyy-MM-ddTHH:mm").format(new Date());
			Param message = new Param("timestamp", timeStamp, "string");
			result.addParam(message);
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
		return result;
	}
}
