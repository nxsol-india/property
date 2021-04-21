package com.nxsol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nxsol.entity.Property;
import com.nxsol.service.PropertyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductAPI {
	
	@Autowired
	PropertyService services;

	@ApiOperation(value = "get all search", notes = "get all search\"")
	@GetMapping("/search/")
	public Page<Property> search(Pageable pageable, @RequestParam("searchText") String searchText) {
		return services.search(pageable, searchText);
	}
	
	@ApiOperation(value = "get by id", notes = "get by id")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public Property read(@PathVariable Long id) {
		return services.findById(id).orElse(null);
	}
	
	@ApiOperation(value = "get all", notes = "get all")
	@GetMapping()
	public Page<Property> readAll(Pageable pageable) {
		return services.findAll(pageable);
	}

	@ApiOperation(value = "save", notes = "save")
	@ApiParam(name = "To save", value = "To save", required = true)
	@PostMapping()
	public Property create(@RequestBody Property request) {
		return services.save(request);
	}
	
	@ApiOperation(value = "update", notes = "update")
	@ApiParam(name = "To update", value = "To update", required = true)
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Property request, @PathVariable Long id) {
		Property entity = services.findById(id).orElse(null);
		if (entity == null)
			return new ResponseEntity<>("ID not found", HttpStatus.BAD_REQUEST);

		entity.setName(request.getName());
		entity.setCode(request.getCode());
		entity.setAddress(request.getAddress());
		request = services.save(entity);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@ApiOperation(value = "delete", notes = "delete")
	@ApiParam(name = "To Delete", value = "To Delete", required = true)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Property entity = services.findById(id).orElse(null);
		if (entity == null)
			return new ResponseEntity<>("Record Does not exists!", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(services.deleteById(id), HttpStatus.OK);
	}

}
