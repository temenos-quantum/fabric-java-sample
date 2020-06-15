package com.acme.services;

//import com.google.gson.Gson;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Returns a random color.
 */
public class ColorService implements JavaService2{

	private static final Logger LOGGER = Logger.getLogger( ColorService.class.getName() );

	public Result invoke(String opId, Object[] maps, DataControllerRequest request, DataControllerResponse response){

		Result result = new Result();
		try{

			HashMap<String, Object> inMap = (HashMap<String, Object>)maps[1];
			HashMap<String, Object> headers = (HashMap<String, Object>)request.getHeaderMap();

			//TODO: Need a better mechanism for switching ops than elseif.
			if (opId.equals("getColor")) result = getColor(inMap, headers);
			else  if (opId.equals("getPastel")) result = getPastel(inMap, headers);
			else {
				Param error = new Param("message", "No such operation!", "string");
				result.addParam(error);
			}
		}
		catch (Exception e){

			LOGGER.log(Level.SEVERE, "ColorService failed.\n\t" +
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

	private String getHex(Color color){
		String hex = Integer.toHexString(color.getRGB() & 0xffffff);
		if (hex.length() < 6) {
			hex = "0" + hex;
		}
		hex = "#" + hex;
		return hex;
	}

	private Result buildResult(String hex){
		Result result = new Result();
		Param message = new Param("color", hex, "string");
		result.addParam(message);
		return result;
	}

	private Result getColor(HashMap<String, Object> inMap, HashMap<String, Object> headers){

		Random random = new Random();

		// Generate a totally random color.
		float r = random.nextFloat();
		float g = random.nextFloat();
		float b = random.nextFloat();
		Color color = new Color(r, g, b);

		String hex = getHex(color);
		return buildResult(hex);
	}

	private Result getPastel(HashMap<String, Object> inMap, HashMap<String, Object> headers){

		Random random = new Random();

		//Generate a pastel color.
		float hue = random.nextFloat();
		// Saturation between 0.1 and 0.3
		float saturation = (random.nextInt(2000) + 1000) / 10000f;
		float luminance = 0.9f;

		Color color = Color.getHSBColor(hue, saturation, luminance);

		String hex = getHex(color);
		return buildResult(hex);
	}
}
