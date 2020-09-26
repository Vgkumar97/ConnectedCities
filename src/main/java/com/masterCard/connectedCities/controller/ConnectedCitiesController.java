package com.masterCard.connectedCities.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masterCard.connectedCities.model.Constants;
import com.masterCard.connectedCities.service.ConnectedCitiesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.*;

/**
 * Rest Controller for Connected Cities API.
 *
 * @author Vijayakumar Gowda
 */
@RestController
@Api(value="Connected Cities Controller", description="Operations pertaining to Connected Cities")
public class ConnectedCitiesController {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectedCitiesController.class);
    
	@Autowired
	ConnectedCitiesService connectedCitiesService;

	/**
	 * Get API to check if two cities are connected.
	 *
	 * @return the String with "Yes" or "No" value.
	 */
	@ApiOperation(value = "Check two cities are connected", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully check two cities are connected"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/connected")
	public String checkConnectedCity(@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "destination", required = false) String destination) throws Exception {
		String checkconnection = Constants.NO_CONNECTION_EXISTS;
		checkconnection = connectedCitiesService.checkConnection(origin, destination);

		return checkconnection;
	}
}
