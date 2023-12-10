package com.kkk.fleetmanagement.v1.service;

import com.kkk.fleetmanagement.v1.common.exception.ShipmentException;
import com.kkk.fleetmanagement.v1.data.modal.Deliveries;
import com.kkk.fleetmanagement.v1.data.modal.Shipment;
import com.kkk.fleetmanagement.v1.data.modal.ShipmentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FleetManagementServiceImpl implements  FleetManagementService{
    private final ShipmentStrategyFactory shipmentStrategyFactory;
    public ShipmentResult distributeShipment(Shipment shipment) throws ShipmentException {
        List<Deliveries> deliveriesWithStatesList = new ArrayList<>();
        for (Deliveries deliveries:shipment.getRoute()) {
            deliveriesWithStatesList.add(shipmentStrategyFactory.getShipmentStrategy(deliveries.getDeliveryPoint()).execute(deliveries));
        }
        return new ShipmentResult(shipment.getVehicle(),deliveriesWithStatesList);
    }
}
