package com.nxsol.service;

import org.springframework.stereotype.Service;

import com.nxsol.entity.User;
import com.nxsol.repository.UserRepository;

@Service
public class UserServiceImpl extends BasicService<User, UserRepository> implements UserService{


}
