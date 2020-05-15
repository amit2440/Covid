package com.med.disease.tracking.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.Manager;

@Mapper
public interface IManagerRepository {
	
//	@Insert("INSERT INTO manager (mgr_first_name,mgr_last_name,team_name,is_active) VALUES (#{mgrFirstName}, #{mgrLastName}, #{teamName},#{isActive})")
//	public int insert(Manager manager);

	
	@Results({
		@Result(property = "mgrId", column = "user_id"),
		@Result(property = "mgrFirstName", column = "first_name"),
		@Result(property = "mgrLastName", column = "last_name"),
//		@Result(property = "teamName", column = "team_name"),
      })
	@Select("SELECT first_name,last_name,user_id from user where is_active <> 0 and role='MANAGER';")
	public List<Manager> findAll(); 
	
}
