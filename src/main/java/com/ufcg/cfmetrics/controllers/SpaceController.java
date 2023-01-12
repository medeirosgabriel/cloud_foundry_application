package com.ufcg.cfmetrics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.cfmetrics.services.SpaceService;

@RestController
public class SpaceController {
	
	@Autowired
	private SpaceService spaceService;
	
	@GetMapping("/space")
    public ResponseEntity<?> getSpaces() {
        return new ResponseEntity<>(spaceService.getSpaces(), HttpStatus.OK);
	}
	
	@GetMapping("/space/{spaceId}")
    public ResponseEntity<?> getSpaces(@PathVariable("spaceId") String spaceId) {
        return new ResponseEntity<>(spaceService.getSpaceById(spaceId), HttpStatus.OK);
	}
	
	@GetMapping("spacequota/")
    public ResponseEntity<?> getSpacesQuota() {
        return new ResponseEntity<>(spaceService.getSpacesQuota(), HttpStatus.OK);
	}
	
	@GetMapping("spacequota/{spaceId}")
    public ResponseEntity<?> getSpacesQuota(@PathVariable("spaceId") String spaceId) {
        return new ResponseEntity<>(spaceService.getSpaceQuotaById(spaceId), HttpStatus.OK);
	}
}
