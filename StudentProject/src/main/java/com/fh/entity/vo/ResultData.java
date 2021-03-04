package com.fh.entity.vo;



public class ResultData {

    private Integer code;

    private String message;

    private Object data;

    //声明私有的构造函数
    private ResultData(){

    }
    //成功的回调函数
    public  static  ResultData success(Object data){
        ResultData rs = new ResultData();
        rs.setCode(200);
        rs.setMessage("success");
        rs.setData(data);
        return rs;
    }
    //失败的回调函数
    public  static  ResultData error(Integer code, String message){
     ResultData rs=new ResultData();
        rs.setCode(code);
        rs.setMessage(message);
        return rs;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
