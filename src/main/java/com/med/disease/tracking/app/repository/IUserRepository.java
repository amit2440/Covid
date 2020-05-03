package com.med.disease.tracking.app.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.User;

@Mapper
public interface IUserRepository {
	@Results({
		@Result(property = "uid", column = "uid"),
		@Result(property = "userName", column = "username"),
		@Result(property = "password", column = "password"),
		@Result(property = "enabled" ,column = "enabled"),
		@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name"),
		@Result(property = "middleName", column = "middle_name"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "role", column = "role"),
		@Result(property = "workLocation", column = "work_location")
      })
	@Select("select username,uid,password,enabled,first_name,last_name,middle_name,mobile,role,work_location from users")
	public List<UserDTO> findAll();

	@Results({
		@Result(property = "uid", column = "uid"),
		@Result(property = "userName", column = "username"),
		@Result(property = "password", column = "password"),
		@Result(property = "enabled" ,column = "enabled"),
		@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name"),
		@Result(property = "middleName", column = "middle_name"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "role", column = "role"),
		@Result(property = "workLocation", column = "work_location")
      })
	
	@Select("SELECT  username,uid,password,enabled,first_name,last_name,middle_name,mobile,role,work_location FROM users WHERE username = #{userName}")
	public Optional<UserDTO> findByUserName(String userName);

	@Delete("DELETE FROM users WHERE username = #{userName}")
	public int delete(String userName);

	@Insert("INSERT INTO users(username, password, enabled) VALUES (#{userName}, #{password}, #{enabled})")
	public int insert(UserDTO user);

	@Update("Update users set password=#{password}, enabled=#{enabled} where userName=#{userName}")
	public int update(UserDTO user);

	@Insert("INSERT INTO users(username,uid,password,enabled,first_name,last_name,middle_name,mobile,token,created_dtm,role,work_location) "
			+ "VALUES (#{userName},#{uid},#{password},#{enabled},#{firstName},#{lastName},#{middleName},#{mobile},#{token},#{createdDtm},#{role},#{workLocation})")
	public int insertEmployee(UserDTO user);
	
}
