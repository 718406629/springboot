package org.zyq.sbdemo.springboot.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.zyq.sbdemo.springboot.dto.AccessTokenDTO;
import org.zyq.sbdemo.springboot.dto.GithubUser;

import java.io.IOException;

@Component
public class GithubProvider {
    public  String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String token = string.split("&")[0].split("=")[1];
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
    }


       public GithubUser getUser(String accessToker){
           OkHttpClient client = new OkHttpClient();
           Request request = new Request.Builder()
                   .url("https://api.github.com/user?access_token="+accessToker)
                   .build();
           try {
               Response response = client.newCall(request).execute();
               String string = response.body().string();
               GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
               return githubUser;
           } catch (IOException e) {
           }
              return null;
       }
    }

