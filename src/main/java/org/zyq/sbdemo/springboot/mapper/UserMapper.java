package org.zyq.sbdemo.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.zyq.sbdemo.springboot.model.User;
@Repository
@Mapper
public interface UserMapper {

   @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatarUrl) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
  @Select("select * from user where token=#{token}")
   User findByToken(@Param("token") String token);
   @Select("select * from user where id=#{creator}")
   User findById(@Param("creator") Integer creator);
}
