package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.User;

@Mapper
public interface UserDao {
	/*@Select("<script>select * from user_tb <where>id=${id} <if test='value!=null'>AND value=${value}</if></where></script>")*/
	User findUserByName(@Param("id")Integer id,@Param("value")String value);
	/*@Select("select * from user_tb"
			+ "ORDER BY ${orderName}  ${orderWay} LIMIT #{pageNum},#{pageSize}")*/
	List<User> browsePagingUser(@Param("orderName") String orderName,@Param("orderWay") String orderWay,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
	/*@Insert("INSERT IGNORE INTO user_tb "
			+ "(value,password,timestamp,dt) "
			+ "VALUES (#{value},#{password},#{timestamp},#{dt})")*/
	public boolean addUser(User user);
/*	@Update("UPDATE user_tb "
			+ "set value=value+1,password=#{password},timestamp=#{timestamp},dt=#{dt} "
			+ "WHERE id=${id}")
*/
	public boolean updateUser(User user);
	
}
