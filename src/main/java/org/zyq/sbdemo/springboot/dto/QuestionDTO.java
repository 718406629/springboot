package org.zyq.sbdemo.springboot.dto;

import lombok.Data;
import org.zyq.sbdemo.springboot.model.User;

@Data
public class QuestionDTO {
    private  Integer id;
    private String title;
    private String description;
    private String tag;
    private  Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;

}
