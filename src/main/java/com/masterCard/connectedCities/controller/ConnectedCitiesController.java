package com.masterCard.connectedCities.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masterCard.connectedCities.model.Constants;
import com.masterCard.connectedCities.service.ConnectedCitiesService;

/**
 * Rest Controller for Connected Cities API.
 *
 * @author Vijayakumar Gowda
 */
@RestController
public class ConnectedCitiesController {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectedCitiesController.class);
    
	@Autowired
	ConnectedCitiesService connectedCitiesService;

	/**
	 * Get API to check if two cities are connected.
	 *
	 * @return the String with "Yes" or "No" value.
	 */
	@GetMapping("/connected")
	public String checkConnectedCity(@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "destination", required = false) String destination) throws Exception {
		String checkconnection = Constants.NO_CONNECTION_EXISTS;
		checkconnection = connectedCitiesService.checkConnection(origin, destination);

		return checkconnection;
	}
}
