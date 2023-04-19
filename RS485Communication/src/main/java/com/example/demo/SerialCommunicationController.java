package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerialCommunicationController {
	
	@Autowired SerialCommunicationService serialCommunicationService;

	@GetMapping(value = "/ports")
	public List<String> get() {
		return serialCommunicationService.getPorts();
	}
	
	@GetMapping(value = "/communication")
	public String communication() {
		return serialCommunicationService.communicate();
	}
	
}
