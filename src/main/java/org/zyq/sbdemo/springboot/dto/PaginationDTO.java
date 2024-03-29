package org.zyq.sbdemo.springboot.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    //首页
    private  boolean showFirstPage;
    //前一页
    private  boolean showPrevious;
    //当前页
    private  Integer page;
    //后一页
    private  boolean showNext;
    //尾页
    private boolean showEndPage;

    private  Integer totalPage;




   //页码数组
    private  List<Integer> pages=new ArrayList<>();

    public void setPagination(Integer page, Integer size, Integer totalCount) {


       if(totalCount%size==0){
           totalPage=totalCount/size;
       }else{
           totalPage=totalCount/size+1;
       }

        this.page=page;
       //加入当前页码
       pages.add(page);
       for(int i=1;i<=3;i++){
           if(page-i>0){
               pages.add(0,page-i);
           }
           if(page+i<=totalPage){
               pages.add(page+i);
           }
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
        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }

    }
}
