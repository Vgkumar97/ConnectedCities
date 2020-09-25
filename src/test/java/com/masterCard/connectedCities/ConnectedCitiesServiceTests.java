package com.masterCard.connectedCities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.masterCard.connectedCities.config.BuildConnectedCitiesGraphFromFile;
import com.masterCard.connectedCities.service.ConnectedCitiesGraph;
import com.masterCard.connectedCities.service.ConnectedCitiesService;

@RunWith(MockitoJUnitRunner.class)
public class ConnectedCitiesServiceTests {

	@InjectMocks
	private ConnectedCitiesService connectedCitiesService = new ConnectedCitiesService();
	
	@Mock
	BuildConnectedCitiesGraphFromFile buildConnectedCitiesGraphFromFile;
	
	ConnectedCitiesGraph connectedCitiesGraphObj;
	
    @Before
    public void setUp() {
    	connectedCitiesGraphObj = new ConnectedCitiesGraph();

    	connectedCitiesGraphObj.addVertex("Boston");
    	connectedCitiesGraphObj.addVertex("New York");
    	connectedCitiesGraphObj.addVertex("Philadelphia");
    	connectedCitiesGraphObj.addVertex("Newark");
    	connectedCitiesGraphObj.addVertex("Trenton");
    	connectedCitiesGraphObj.addVertex("Albany");
    	
    	connectedCitiesGraphObj.addEdge("Boston", "New York");
    	connectedCitiesGraphObj.addEdge("Philadelphia", "Newark");
    	connectedCitiesGraphObj.addEdge("Newark", "Boston");
    	connectedCitiesGraphObj.addEdge("Trenton", "Albany");
    }
    
    @Test
    public void testCheckConnectionDirect() throws Exception {
        when(buildConnectedCitiesGraphFromFile.buildCitiesGraph()).thenReturn(connectedCitiesGraphObj);

        String connectionExists = connectedCitiesService.checkConnection("Boston", "Newark");
        
        verify(buildConnectedCitiesGraphFromFile, times(1)).buildCitiesGraph();
	    verifyNoMoreInteractions(buildConnectedCitiesGraphFromFile);
	    
        assertEquals(connectionExists, "Yes");
    }
    
    @Test
    public void testCheckConnectionInDirect() throws Exception {
        when(buildConnectedCitiesGraphFromFile.buildCitiesGraph()).thenReturn(connectedCitiesGraphObj);

        String connectionExists = connectedCitiesService.checkConnection("Boston", "Philadelphia");
        
        verify(buildConnectedCitiesGraphFromFile, times(1)).buildCitiesGraph();
	    verifyNoMoreInteractions(buildConnectedCitiesGraphFromFile);
	    
        assertEquals(connectionExists, "Yes");
    }
    
    @Test
    public void testCheckConnectionNo() throws Exception {
        when(buildConnectedCitiesGraphFromFile.buildCitiesGraph()).thenReturn(connectedCitiesGraphObj);

        String connectionExists = connectedCitiesService.checkConnection("Philadelphia", "Albany");
        
        verify(buildConnectedCitiesGraphFromFile, times(1)).buildCitiesGraph();
	    verifyNoMoreInteractions(buildConnectedCitiesGraphFromFile);
	    
        assertEquals(connectionExists, "No");
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testCheckConnectionException() throws Exception {
    	ConnectedCitiesGraph connectedCitiesGraph = new ConnectedCitiesGraph();
    	
        when(buildConnectedCitiesGraphFromFile.buildCitiesGraph()).thenReturn(connectedCitiesGraph);

        thrown.expect(Exception.class);
        
        String connectionExists = connectedCitiesService.checkConnection("Boston", "Newark");
    }
}
