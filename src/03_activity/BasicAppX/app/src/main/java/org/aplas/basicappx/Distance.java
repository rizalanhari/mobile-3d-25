package org.aplas.basicappx;

public class Distance {
    private double meter;
    Distance(){
        this.meter = 0;
    }

    public void setMeter(double meter) {
        this.meter = meter;
    }
    public void setInch(double inch) {
        meter = inch / 39.3701;
    }
    public void setMile(double mile) {
        meter = mile / 0.000621371;
    }
    public void setFoot(double feet) {
        meter = feet / 3.28084;
    }

    public double getMeter() {
        return meter;
    }
    public double getInch() {
        return meter*39.3701;
    }
    public double getMile() {
        return meter*0.000621371;
    }
    public double getFoot() {
        return meter*3.28084;
    }
    double convert(String oriUnit, String convUnit, double value){
        if(oriUnit.equals("Mtr")){
            if(convUnit.equals("Inc")){
                setMeter(value);
                value=getInch();
            }
            else if (convUnit.equals("Mil")){
                setMeter(value);
                value=getMile();
            }
            else if (convUnit.equals("Ft")){
                setMeter(value);
                value=getFoot();
            }
            else {
                setMeter(value);
                value=getMeter();
            }
        }
        else if (oriUnit.equals("Inc")){
            if(convUnit.equals("Inc")){
                setInch(value);
                value=getInch();
            }
            else if (convUnit.equals("Mil")){
                setInch(value);
                value=getMile();
            }
            else if (convUnit.equals("Ft")){
                setInch(value);
                value=getFoot();
            }
            else {
                setInch(value);
                value=getMeter();
            }
        }
        else if (oriUnit.equals("Mil")){
            if(convUnit.equals("Inc")){
                setMile(value);
                value=getInch();
            }
            else if (convUnit.equals("Mil")){
                setMile(value);
                value=getMile();
            }
            else if (convUnit.equals("Ft")){
                setMile(value);
                value=getFoot();
            }
            else {
                setMile(value);
                value=getMeter();
            }
        }
        else{
            if(convUnit.equals("Inc")){
                setFoot(value);
                value=getInch();
            }
            else if (convUnit.equals("Mil")){
                setFoot(value);
                value=getMile();
            }
            else if (convUnit.equals("Ft")){
                setFoot(value);
                value=getFoot();
            }
            else {
                setFoot(value);
                value=getMeter();
            }
        }
        return value;
    }
}
