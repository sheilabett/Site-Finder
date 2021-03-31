package com.app.code;

public class reqinfo {
    private String info;
    private  float Mp,Lp,Fp;


    public reqinfo(String Minfo,float Mprice, float Lprice,float Fprice){

        this.info=Minfo;
        this.Mp=Mprice;
        this.Lp=Lprice;
        this.Fp=Fprice;
    }

    public String getinfo() {
        return info;
    }
    public float getMprice() {
        return Mp;
    }
    public float getLprice() {
        return Lp;
    }
    public float getFprice() {
        return Fp;
    }
    /*public void setinfo(String name) {
       // this.name = name;
    }
    public void setName(String name) {
       // this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }*/




}
