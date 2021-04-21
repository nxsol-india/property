package com.nxsol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nxsol.entity.User;
import com.nxsol.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService service;

	@ApiOperation(value = "create user", notes = "create user")
	@ApiParam(name = "user", value = "user to save", required = true)
	@PostMapping()
	public ResponseEntity<User> create(@RequestBody User entity) {
		entity = service.save(entity);
		return new ResponseEntity<User>(entity, HttpStatus.CREATED);
	}

	@ApiOperation(value = "get User by id", notes = "get User by id")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public User read(@PathVariable Long id) {
		return service.findById(id).get();
	}

	@ApiOperation(value = "get all", notes = "get all")
	@GetMapping()
	public Page<User> readAll(Pageable pageable) {
		return service.findAll(pageable);
	}
}
