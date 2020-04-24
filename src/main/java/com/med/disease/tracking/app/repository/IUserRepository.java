package com.med.disease.tracking.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.med.disease.tracking.app.model.User;

@Mapper
public interface IUserRepository {

	@Select("select * from users")
	public List<User> findAll();

	@Select("SELECT * FROM users WHERE username = #{userName}")
	public User findByUserName(String userName);

	@Delete("DELETE FROM users WHERE username = #{userName}")
	public int delete(String userName);

	@Insert("INSERT INTO users(username, password, enabled) VALUES (#{userName}, #{password}, #{isActive})")
	public int insert(User user);

	@Update("Update users set password=#{password}, enabled=#{isActive} where userName=#{userName}")
	public int update(User user);

}
