package it.cd79.test.security.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.cd79.test.security.model.Example;

@RestController
@RequestMapping
public class MainController {

	@RequestMapping(value = "/secured", method = GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Example> secured() {
		return new ResponseEntity<Example>(new Example("OK"), HttpStatus.OK);
	}

	@RequestMapping(value = "/not_secured", method = GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Example> notSecured() {
		return new ResponseEntity<Example>(new Example("OK"), HttpStatus.OK);
	}

}
