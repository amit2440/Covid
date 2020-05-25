package com.med.disease.tracking.app.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.med.disease.tracking.app.dto.UserDTO;

@Mapper
public interface IUserRepository {
	@Results({
//		@Result(property = "uid", column = "uid"),
//		@Result(property = "userName", column = "username"),
		@Result(property = "userId", column = "user_id"),
//		@Result(property = "password", column = "password"),
		@Result(property = "isActive" ,column = "is_active"),
		@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name"),
//		@Result(property = "middleName", column = "middle_name"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "role", column = "role"),
		@Result(property = "workLocation", column = "work_location")
      })
//	@Select("select user_id,uid,password,enabled,first_name,last_name,middle_name,mobile,role,work_location from users")
	@Select("select user_id,is_active,first_name,last_name,mobile,role,work_location from user")
	public List<UserDTO> findAll();

	@Results({
//		@Result(property = "uid", column = "uid"),
//		@Result(property = "userName", column = "username"),
		@Result(property = "userId", column = "user_id"),
//		@Result(property = "password", column = "password"),
		@Result(property = "isActive" ,column = "is_active"),
		@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name"),
//		@Result(property = "middleName", column = "middle_name"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "role", column = "role"),
		@Result(property = "workLocation", column = "work_location")
     
      })
	
	@Select("select user_id,is_active,first_name,last_name,mobile,token,role,work_location from user WHERE mobile = #{userName}")
	public Optional<UserDTO> findByUserName(String userName);

	@Delete("DELETE FROM user WHERE mobile = #{userName}")
	public int delete(String userName);

	@Insert("INSERT INTO user(mobile,first_name,Last_name) VALUES (#{mobile}, #{firstName}, #{lastName})")
	public int insert(UserDTO user);

	@Update("Update user set token=#{token}, is_active=#{isActive} where mobile=#{mobile}")
	public int update(UserDTO user);

	@Insert("INSERT INTO user(is_active,first_name,last_name,mobile,token,role,work_location,mgr_id) "
			+ "VALUES (#{isActive},#{firstName},#{lastName},#{mobile},#{token},#{role},#{workLocation},#{mgrID})")
	public int insertEmployee(UserDTO user);
	
	@Results({
//		@Result(property = "uid", column = "uid"),
//		@Result(property = "userName", column = "username"),
		@Result(property = "userId", column = "user_id"),
//		@Result(property = "password", column = "password"),
		@Result(property = "isActive" ,column = "is_active"),
		@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name"),
//		@Result(property = "middleName", column = "middle_name"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "role", column = "role"),
		@Result(property = "workLocation", column = "work_location")
     
      })
	@SelectProvider(UserSqlProvider.class)
	public List<UserDTO> getUsersByName(UserDTO userDTO);
}
