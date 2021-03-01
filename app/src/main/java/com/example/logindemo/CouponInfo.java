package com.example.logindemo;

public class CouponInfo {

    //////////////////////////VARIABLES//////////////////////////
    public String cal,timeOfDay, Hourss;



    public CouponInfo(){

    }

    public CouponInfo(String cal,String timeOfDay,String Hourss){
        this.cal = cal;
        this.timeOfDay= timeOfDay;
        this.Hourss = Hourss;
    }

    public String getCal() {

        return cal;
    }

    public void  setCal(String userDate) {

        this.cal = cal;
    }

    public String getTimeOfDay() {

        return timeOfDay;
    }

    public void  setTimeOfDay(String userTimeOfDay) {

        this.timeOfDay = userTimeOfDay;
    }

    public String getHourss(){

        return Hourss;
    }

    public void getHourss(String Hourss) {

        this.Hourss = Hourss;
    }




}
