package org.aplas.basicappx;

public class Weight {
    private double gram;
    Weight(){
        this.gram = 0;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }
    public void setOunce(double ounce) {
        this.gram = ounce*28.3495231;
    }
    public void setPound(double pound) {
        this.gram = pound*453.59237;
    }

    public double getGram() {
        return gram;
    }
    public double getOunce() {
        return gram/28.3495231;
    }
    public double getPound() {
        return gram/453.59237;
    }

    double convert(String oriUnit, String convUnit, double value){
        if(oriUnit.equals("Grm")){
            if(convUnit.equals("Grm")){
                setGram(value);
                value=getGram();
            }
            else if (convUnit.equals("Onc")){
                setGram(value);
                value=getOunce();
            }
            else {
                setGram(value);
                value=getPound();
            }
        }
        else if(oriUnit.equals("Onc")){
            if(convUnit.equals("Grm")){
                setOunce(value);
                value=getGram();
            }
            else if (convUnit.equals("Onc")){
                setOunce(value);
                value=getOunce();
            }
            else {
                setOunce(value);
                value=getPound();
            }
        }
        else {
            if (convUnit .equals("Grm")) {
                setPound(value);
                value = getGram();
            } else if (convUnit .equals("Onc")) {
                setPound(value);
                value = getOunce();
            } else {
                setPound(value);
                value = getPound();
            }
        }
        return value;
    }
}
