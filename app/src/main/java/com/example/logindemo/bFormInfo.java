package com.example.logindemo;

public class bFormInfo {

    String phonenumber,carbrand,carmodel,carcolor,carplate;
    String cal,timeOfDay, hourss;


    public bFormInfo() {
    }

    public bFormInfo(String phonenumber, String carbrand, String carmodel, String carcolor,String carplate,String cal,String timeOfDay,String hourss) {
        this.phonenumber = phonenumber;
        this.carbrand = carbrand;
        this.carmodel = carmodel;
        this.carcolor = carcolor;
        this.carplate = carplate;
        this.cal = cal;
        this.timeOfDay= timeOfDay;
        this.hourss = hourss;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public String getCarplate() {
        return carplate;
    }

    public void setCarplate(String carplate) {
        this.carplate = carplate;
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
