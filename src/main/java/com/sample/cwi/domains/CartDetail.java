package com.sample.cwi.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CartDetail implements Serializable{

    private long vehicleId;

    private String vehicleMake;

    private String vehicleModel;

    private double vehiclePrice;


    public CartDetail() {
    }

    public CartDetail(long vehicleId, String vehicleMake, String vehicleModel, double vehiclePrice) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehiclePrice = vehiclePrice;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "vehicleId=" + vehicleId +
                ", vehicleMake='" + vehicleMake + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehiclePrice=" + vehiclePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartDetail that = (CartDetail) o;

        if (vehicleId != that.vehicleId) return false;
        if (Double.compare(that.vehiclePrice, vehiclePrice) != 0) return false;
        if (vehicleMake != null ? !vehicleMake.equals(that.vehicleMake) : that.vehicleMake != null) return false;
        return vehicleModel != null ? vehicleModel.equals(that.vehicleModel) : that.vehicleModel == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (vehicleId ^ (vehicleId >>> 32));
        result = 31 * result + (vehicleMake != null ? vehicleMake.hashCode() : 0);
        result = 31 * result + (vehicleModel != null ? vehicleModel.hashCode() : 0);
        temp = Double.doubleToLongBits(vehiclePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
