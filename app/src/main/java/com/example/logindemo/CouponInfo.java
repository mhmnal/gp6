package com.example.logindemo;


import android.app.Application;

public class CouponInfo{

    //////////////////////////VARIABLES//////////////////////////
    public String cal,timeOfDay, hourss;

    public CouponInfo(){

    }

    public CouponInfo(String cal,String timeOfDay,String hourss){
        this.cal = cal;
        this.timeOfDay= timeOfDay;
        this.hourss = hourss;
    }





    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getHourss() {
        return hourss;
    }

    public void setHourss(String hourss) {
        this.hourss = hourss;
    }
}
