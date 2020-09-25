package com.masterCard.connectedCities.model;

/**
 * Class to store pair of connected city names.
 *
 * @author Vijayakumar Gowda
 */
public class ConnectedCities {
	
	private String source;
	private String destination;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "ConnectedCity [source=" + source + ", destination=" + destination + "]";
	}
}
