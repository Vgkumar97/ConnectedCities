package com.masterCard.connectedCities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.masterCard.connectedCities.controller.ConnectedCitiesController;
import com.masterCard.connectedCities.service.ConnectedCitiesService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ConnectedCitiesController.class)
public class ConnectedCitiesControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConnectedCitiesService connectedCitiesService;

	@Test
	public void testConnectedYes() throws Exception {

		Mockito.when(connectedCitiesService.checkConnection(Mockito.anyString(), Mockito.anyString())).thenReturn("Yes");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected?origin=Boston&destination=Newark")
															  .accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		assertEquals(result.getResponse().getContentAsString(), "Yes");
	}
	
	@Test
	public void testConnectedNo() throws Exception {

		Mockito.when(connectedCitiesService.checkConnection(Mockito.anyString(), Mockito.anyString())).thenReturn("No");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/connected?origin=Philadelphia&destination=Albany")
															  .accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		assertEquals(result.getResponse().getContentAsString(), "No");
	}
}
