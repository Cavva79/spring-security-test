package it.cd79.test.security.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.View;

import it.cd79.test.security.contexts.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class }, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(listeners = { WithSecurityContextTestExecutionListener.class })
@WebAppConfiguration
public class MainControllerIT {

	@InjectMocks
	MainController controller;

	@Mock
	View mockView;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	@Bean
	public ServletContext documentationPluginsBootstrapper() {
		return Mockito.mock(ServletContext.class);
	}

	MockMvc mvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = standaloneSetup(controller).setSingleView(mockView).apply(springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	@WithMockUser
	public void testSecuredXml() throws Exception {
		ResultActions perform = mvc.perform(get("/secured").accept(MediaType.APPLICATION_XML));
		perform.andExpect(status().isOk());
	}

	@Test
	public void testNotSecuredJson() throws Exception {
		ResultActions perform = mvc.perform(get("/not_secured").accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
	}

}
