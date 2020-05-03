package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.UserDTO;

public interface RegisterEmployeeService {

	public int registerEmployee(UserDTO user);

	public int registerEmpFormCSV(String line);

}
