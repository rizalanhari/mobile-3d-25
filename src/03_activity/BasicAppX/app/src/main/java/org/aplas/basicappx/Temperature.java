package org.aplas.basicappx;

public class Temperature {
    private double celcius;
    Temperature(){
        this.celcius =0;
    }

    public void setCelcius(double celcius) {
        this.celcius = celcius;
    }

    public void setFahrenheit(double fahrenheit){
        celcius = (fahrenheit - 32)*5/9;
    }

    public void setKelvins(double kelvin){
        celcius = kelvin - 273.15;
    }

    double getCelcius(){
        return celcius;
    }

    double getFahrenheit(){
        return (celcius*9/5)+32;
    }

    double getKelvins(){
        return celcius+273.15;
    }

    double convert(String oriUnit, String convUnit, double value){
        if(oriUnit.equals("°C")){
            if(convUnit.equals("°F")){
                setCelcius(value);
                value=getFahrenheit();
            }
            else if (convUnit.equals("K")){
                setCelcius(value);
                value=getKelvins();
            }
            else {
                setCelcius(value);
                value=getCelcius();
            }
        }
        else if (oriUnit.equals("°F")){
            if (convUnit.equals("°C")){
                setFahrenheit(value);
                value=getCelcius();
            }
            else if (convUnit.equals("K")){
                setFahrenheit(value);
                value=getKelvins();
            }
            else {
                setFahrenheit(value);
                value=getFahrenheit();
            }
        }
        else {
            if (convUnit.equals("°C")){
                setKelvins(value);
                value=getCelcius();
            }
            else if (convUnit.equals("°F")){
                setKelvins(value);
                value=getFahrenheit();
            }
            else {
                setKelvins(value);
                value=getKelvins();
            }
        }
        return value;
    }
}
