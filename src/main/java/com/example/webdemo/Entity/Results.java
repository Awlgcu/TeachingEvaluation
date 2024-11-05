package com.example.webdemo.Entity;

public class Results {
    private Object date = null;
    private String message;
    public Results(Object date, String message){
        this.date = date;
        this.message = message;
    }
    public Object getDate(){
        return this.date;
    }

    public static Results success(Object data){
        Results results = new Results(data, "后端获取数据成功");
        return results;
    }
    public static Results failed(){
        Results results = new Results(null, "后端获取数据失败");
        return results;
    }
}
