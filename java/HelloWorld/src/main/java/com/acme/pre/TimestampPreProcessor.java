package com.acme.pre;

import com.konylabs.middleware.common.DataPreProcessor2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;


/**
 * Adds a timestamp to both as an input parameter and as a custom header.
 */
public class TimestampPreProcessor implements DataPreProcessor2{

	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm";

	private static final Logger LOGGER = Logger.getLogger( TimestampPreProcessor.class.getName() );

	public boolean execute(HashMap inMap, DataControllerRequest request, DataControllerResponse response, Result result) throws Exception {
		String timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
		inMap.put("timestamp", timeStamp);
		request.getHeaderMap().put("x-timestamp", timeStamp);
		return true;
	}
}
