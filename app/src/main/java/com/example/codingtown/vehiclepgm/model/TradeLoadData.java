package com.example.codingtown.vehiclepgm.model;

/**
 * Created by CodingTown on 16-12-2016.
 */
public class TradeLoadData {


    String date,billno, farmername, coconuttype,tradecoconut, fromlocation, tolocation,vehicleno,drivername;

    public TradeLoadData() {

    }

    public TradeLoadData(String date,String billno, String farmername, String coconuttype,
                         String tradecoconut, String fromlocation, String tolocation,String vehicleno,String drivername) {

        this.date=date;
        this.billno=billno;
        this.farmername=farmername;
        this.coconuttype=coconuttype;
        this.tradecoconut=tradecoconut;
        this.fromlocation=fromlocation;
        this.tolocation=tolocation;
        this.vehicleno=vehicleno;
        this.drivername=drivername;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getFarmername() {
        return farmername;
    }

    public void setFarmername(String farmername) {
        this.farmername = farmername;
    }

    public String getCoconuttype() {
        return coconuttype;
    }

    public void setCoconuttype(String coconuttype) {
        this.coconuttype = coconuttype;
    }

    public String getTradecoconut() {
        return tradecoconut;
    }

    public void setTradecoconut(String tradecoconut) {
        this.tradecoconut = tradecoconut;
    }

    public String getFromlocation() {
        return fromlocation;
    }

    public void setFromlocation(String fromlocation) {
        this.fromlocation = fromlocation;
    }

    public String getTolocation() {
        return tolocation;
    }

    public void setTolocation(String tolocation) {
        this.tolocation = tolocation;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }
}
