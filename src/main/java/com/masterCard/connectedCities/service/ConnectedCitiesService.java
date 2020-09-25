package com.masterCard.connectedCities.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masterCard.connectedCities.config.BuildConnectedCitiesGraphFromFile;
import com.masterCard.connectedCities.model.Constants;
import com.masterCard.connectedCities.service.ConnectedCitiesGraph.Vertex;

/**
 * Service class for Connected Cities API.
 *
 * @author Vijayakumar Gowda
 */
@Service
public class ConnectedCitiesService {
	
	@Autowired
	BuildConnectedCitiesGraphFromFile buildConnectedCitiesGraphFromFile;

	ConnectedCitiesGraph connectedCitiesGraph;

	/**
	 * Method to check if two cities are connected.
	 *
	 * @param origin is origin city
	 * @param destination is destination city
	 * @return response is a string with 'Yes' or 'No' value.
	 */
	public String checkConnection(String origin, String destination) throws Exception {
		connectedCitiesGraph = buildConnectedCitiesGraphFromFile.buildCitiesGraph();
		
		String connectionExists = Constants.NO_CONNECTION_EXISTS;
		List<Vertex> adjVertices2 = connectedCitiesGraph.getAdjVertices(origin);
		for (Vertex s : adjVertices2) {
			if (s.cityName.equals(destination)) {
				connectionExists = Constants.YES_CONNECTION_EXISTS;
				return connectionExists;
			}
		}
		
		connectionExists = checkIndirectConnection(origin, destination);
		return connectionExists;
	}

	/**
	 * Method to check if two cities are connected indirectly.
	 *
	 * @param origin is origin city
	 * @param destination is destination city
	 * @return response is a string with 'Yes' or 'No' value.
	 */
	private String checkIndirectConnection(String origin, String destination) {
		Map<Vertex, List<Vertex>> adjVertices3 = connectedCitiesGraph.adjVertices;

		for (Map.Entry<Vertex, List<Vertex>> entry : adjVertices3.entrySet()) {

			List<Vertex> value = entry.getValue();
			boolean originPresent = value.stream().anyMatch(x -> x.cityName.equals(origin));
			boolean destinationPresent = value.stream().anyMatch(x -> x.cityName.equals(destination));

			if (originPresent && destinationPresent) {
				return Constants.YES_CONNECTION_EXISTS;
			}

		}

		return Constants.NO_CONNECTION_EXISTS;
	}
}