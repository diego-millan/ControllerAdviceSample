package com.example.controlleradvice.controller;

import com.example.controlleradvice.model.City;
import com.example.controlleradvice.service.ICityService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private ICityService cityService;

  @GetMapping(value = "/cities/{id}")
  public ResponseEntity<City> getCity(@PathVariable Long id) {

    ResponseEntity<City> response;

    try {
      City result = cityService.findById(id);
      response = ResponseEntity.ok(result);

    } catch (Exception e) {
      response = ResponseEntity.badRequest().build();
    }

    return response;
  }

  @PostMapping(
      value = "/cities",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<City> createCity(@RequestBody @Valid City city) {

    City result = cityService.save(city);

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping(value = "/cities")
  public ResponseEntity<List<City>> findAll() {

    List<City> cities = cityService.findAll();
    return ResponseEntity.ok(cities);
  }
}
