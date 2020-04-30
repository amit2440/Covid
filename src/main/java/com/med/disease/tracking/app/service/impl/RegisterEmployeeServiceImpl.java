package com.med.disease.tracking.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.repository.IUserRepository;
import com.med.disease.tracking.app.service.RegisterEmployeeService;

@Service
public class RegisterEmployeeServiceImpl implements RegisterEmployeeService {

	@Autowired
	private IUserRepository userRepository;
	
	
	@Override
	public int registerEmployee(User user) {
		// TODO Auto-generated method stub
		
//		Need to add validation here and some more stuff if needed like token generation logic and all
		int res = userRepository.insert(user);
		return res;
	}

}
