package com.test.wah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.jaxb.JaxbEngine;
import com.test.model.BenefitInput;
import com.test.preprocessing.KafkaProducerService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class TestController {

	@Autowired
	JaxbEngine eng;
	
	@Autowired
	KafkaProducerService kafkaProducerService;
	
	@GetMapping(value="/test")
	public String getTest() {
		JaxbEngine eng2=new JaxbEngine();
		eng2.runMarshalling();
		return "hello waheed";
	}
	
	@PostMapping(value="/generateFeedXML")
	public ResponseEntity<String> generateXML(@RequestBody BenefitInput input) {
		System.out.println("input data :"+input.getProp().get(0).getAction());
		String res=eng.processBenefit(input.getProp());
		return new ResponseEntity<String>(res.toString(),HttpStatus.OK);
	}
	
	@RequestMapping (method = RequestMethod.GET)

    public String getProducerResponse() {

       return kafkaProducerService.PublishTestMsg("Test Hello....it is done!");

    }

}
