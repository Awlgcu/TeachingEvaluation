package com.example.webdemo.Entity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class BaseResponse {

    private static final ObjectMapper mapper = new ObjectMapper();
    //0-失败 1-正确
    private int status=0;
    private Object data=null;
    private String message=null;

    public BaseResponse(){}
    public int getStatus(){return this.status;}
    public void setStatus(int status){this.status=status;}


    public Object getdata(){return this.data;}
    public void setData(Object data){this.data=data;}

    public String getMessage(){return this.message;}
    public void setMessage(String message){this.message=message;}
    public static String fail(int status, String message) throws JsonProcessingException {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(message);
        baseResponse.setStatus(status);
        return mapper.writeValueAsString(baseResponse);
    }
    public static String success(int status, String message, Object data) throws JsonProcessingException {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(status);
        baseResponse.setData(data);
        baseResponse.setMessage(message);
        return mapper.writeValueAsString(baseResponse);
    }
}
