package com.masterCard.connectedCities.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Class to store connected cities as a graph.
 *
 * @author Vijayakumar Gowda
 */
@Component
public class ConnectedCitiesGraph {
	public Map<Vertex, List<Vertex>> adjVertices;

	public ConnectedCitiesGraph() {
		this.adjVertices = new HashMap<Vertex, List<Vertex>>();
	}

	/**
	 * Method to add node to the graph.
	 *
	 * @param label is city name.
	 * @return void.
	 */
	public void addVertex(String label) {
		adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	/**
	 * Method to add edge between to cities in the graph.
	 *
	 * @param cityName1 is city name.
	 * @param cityName2 is city name.
	 * @return void.
	 */
	public void addEdge(String cityName1, String cityName2) {
		Vertex v1 = new Vertex(cityName1);
		Vertex v2 = new Vertex(cityName2);
		adjVertices.get(v1).add(v2);
		adjVertices.get(v2).add(v1);
	}

	List<Vertex> getAdjVertices(String label) {
		return adjVertices.get(new Vertex(label));
	}

	/**
	 * Class to implement node in the graph.
	 *
	 */
	class Vertex {
		String cityName;

		Vertex(String cityName) {
			this.cityName = cityName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (cityName == null) {
				if (other.cityName != null)
					return false;
			} else if (!cityName.equals(other.cityName))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return cityName;
		}

		private ConnectedCitiesGraph getOuterType() {
			return ConnectedCitiesGraph.this;
		}
	}
}