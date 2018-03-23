package com.example.miseon.braille;

/**
 * Created by miseon on 2018-02-24.
 */

public class ListVO {
    private String name;
    private String address;
    private String detail;
    private String url;

    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
    public String getaddress(){
        return address;
    }
    public void setaddress(String address){
        this.address=address;
    }
    public void setdetail(String detail){
        this.detail=detail;
    }
    public String getdetail(){
        return detail;
    }
    public String geturl(){
        return url;
    }
    public void seturl(String url){
        this.url=url;
    }

}
