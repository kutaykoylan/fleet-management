package com.kkk.fleetmanagement.v1.service;

import com.kkk.fleetmanagement.v1.common.exception.ShipmentException;
import com.kkk.fleetmanagement.v1.data.modal.Shipment;
import com.kkk.fleetmanagement.v1.data.modal.ShipmentResult;

public interface FleetManagementService {
    ShipmentResult distributeShipment(Shipment shipment) throws ShipmentException;
}
