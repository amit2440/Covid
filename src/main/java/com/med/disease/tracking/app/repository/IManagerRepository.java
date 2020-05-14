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
	
	@Insert("INSERT INTO manager (mgr_first_name,mgr_last_name,team_name,is_active) VALUES (#{mgrFirstName}, #{mgrLastName}, #{teamName},#{isActive})")
	public int insert(Manager manager);

	
	@Results({
		@Result(property = "mgrId", column = "mgrID"),
		@Result(property = "mgrFirstName", column = "mgr_first_name"),
		@Result(property = "mgrLastName", column = "mgr_last_name"),
		@Result(property = "teamName", column = "team_name"),
      })
	@Select("SELECT mgrID,team_name,mgr_first_name,mgr_last_name from manager where is_active <> 0;")
	public List<Manager> findAll(); 
	
}
