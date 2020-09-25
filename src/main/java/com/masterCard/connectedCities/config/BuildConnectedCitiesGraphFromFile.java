package com.masterCard.connectedCities.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.masterCard.connectedCities.model.ConnectedCities;
import com.masterCard.connectedCities.service.ConnectedCitiesGraph;

import org.springframework.context.annotation.Configuration;

/**
 * Class to read the file and populate the connected cities graph.
 *
 * @author Vijayakumar Gowda
 */
@Configuration
public class BuildConnectedCitiesGraphFromFile {

	@Value("${city.file.path}")
	public String filePath;

	private static ConnectedCitiesGraph connectedCitiesGraph;
	
    private static final Logger LOG = LoggerFactory.getLogger(BuildConnectedCitiesGraphFromFile.class);
    
	/**
	 * Method read the file and populate the connected cities graph.
	 *
	 * @return response is a connected cities graph.
	 */
    @Bean
    public ConnectedCitiesGraph buildCitiesGraph() throws IOException {
		try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
			List<ConnectedCities> connectedCitiesList = linesStream.map(line -> populateConnectedCities(line)).collect(Collectors.toList());
			connectedCitiesGraph = populateConnectedCitiesGraph(connectedCitiesList);
		} catch (IOException e) {
			LOG.error("Exception Occured buildCitiesGraph :{}",e.getMessage());
		}
		
		return connectedCitiesGraph;
    }
    
	/**
	 * Method to populate pair of connected cities.
	 *
	 * @return response is a class with connected cities.
	 */
	private ConnectedCities populateConnectedCities(String line) {
		String[] lineSplit = line.split(",");
		
		ConnectedCities connectedCities = new ConnectedCities();
		connectedCities.setSource(lineSplit[0].trim());
		connectedCities.setDestination(lineSplit[1].trim());
		
		return connectedCities;
	}
	
	/**
	 * Method to build connected cities graph.
	 *
	 * @param connectedCitiesList is a list of connected cities pair.
	 * @return response is a connected cities graph.
	 */
	private ConnectedCitiesGraph populateConnectedCitiesGraph(List<ConnectedCities> connectedCitiesList) {
		ConnectedCitiesGraph connectedCitiesGraph = new ConnectedCitiesGraph();

		List<String> cityNames = getUniqueCityNames(connectedCitiesList);
		for (String cityName : cityNames) {
			connectedCitiesGraph.addVertex(cityName);
		}

		for (ConnectedCities connectedCities : connectedCitiesList) {
			connectedCitiesGraph.addEdge(connectedCities.getSource(), connectedCities.getDestination());
		}
		
		return connectedCitiesGraph;
	}
	
	/**
	 * Method to return list of unique city names.
	 *
	 * @param connectedCitiesList is a list of connected cities pair.
	 * @return response is list of unique city names.
	 */
	private List<String> getUniqueCityNames(List<ConnectedCities> connectedCitiesList) {
		Set<String> cityNameSet = new HashSet<>();
		
		List<String> uniqueCityNames = null;
		for (ConnectedCities connectedCities : connectedCitiesList) {
			cityNameSet.add(connectedCities.getDestination());
			cityNameSet.add(connectedCities.getSource());
		}
		
		uniqueCityNames = new ArrayList<String>(cityNameSet);
		return uniqueCityNames;
	}
	
	public static ConnectedCitiesGraph getConnectedCitiesGraph() {
		return connectedCitiesGraph;
	}
}
