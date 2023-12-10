package com.kkk.fleetmanagement.v1.service;

import com.kkk.fleetmanagement.v1.data.modal.Deliveries;
import com.kkk.fleetmanagement.v1.data.modal.Delivery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class ShipmentStrategyTemplate {
    public Deliveries execute(Deliveries deliveries) {
        List<Delivery> deliveryWithStateList = new ArrayList<>();
        for(Delivery delivery: deliveries.getDeliveries()){
            if(StringUtils.isNotEmpty(delivery.getBarcode()) && delivery.getBarcode().charAt(0) == 'P'){
                unloadIfPackage(deliveryWithStateList, delivery);
            }else if (StringUtils.isNotEmpty(delivery.getBarcode()) && delivery.getBarcode().charAt(0) == 'C'){
                unloadIfSack(deliveryWithStateList, delivery);
            }
        }
        return new Deliveries(deliveries.getDeliveryPoint(),deliveryWithStateList);
    }
    public abstract void unloadIfPackage(List<Delivery> deliveryWithStateList, Delivery delivery);
    public abstract void unloadIfSack(List<Delivery> deliveryWithStateList, Delivery delivery);
}
