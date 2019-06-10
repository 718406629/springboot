package org.zyq.sbdemo.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.zyq.sbdemo.springboot.model.Question;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description}," +
            "#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{size},#{page}")
    List<Question> selectAll(@Param("size") Integer size,@Param("page") Integer page);

    @Select("select count(1) from question")
    Integer selectAllRecord();
}
