package org.zyq.sbdemo.springboot.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    //首页
    private  boolean showFirstPage;
    //前一页
    private  boolean showPrevious;
    //当前页
    private  boolean currentPage;
    //后一页
    private  boolean showNext;
    //尾页
    private boolean showEndPage;

    //总页数


   //页码数组
    private  List<Integer> pages;

    public void setpagination(Integer page, Integer size, Integer totalCount) {
          Integer totalPage;
       if(totalCount%size==0){
           totalPage=totalCount/size;
       }else{
           totalPage=totalCount/size+1;
       }

       //是否显示上一页
        if(page==1){
            showPrevious=false;
        }else {
            showPrevious = true;
        }
        //是否显示下一页
        if(page==totalPage){
            showNext=false;
        }
        else {
            showNext=true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;

        }
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }

    }
}
