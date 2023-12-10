package com.kkk.fleetmanagement.v1.service;

import com.kkk.fleetmanagement.v1.common.exception.ShipmentException;
import com.kkk.fleetmanagement.v1.data.repository.PackageRepository;
import com.kkk.fleetmanagement.v1.data.repository.SackRepository;
import com.kkk.fleetmanagement.v1.service.helper.FailedPairLogHelper;
import com.kkk.fleetmanagement.v1.service.helper.PackageWithSackHelper;
import com.kkk.fleetmanagement.v1.service.strategies.BranchShipmentStrategy;
import com.kkk.fleetmanagement.v1.service.strategies.DistributionCenterShipmentStrategy;
import com.kkk.fleetmanagement.v1.service.strategies.TransferCenterShipmentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentStrategyFactory {
    private final BranchShipmentStrategy branchShipmentStrategy;
    private final DistributionCenterShipmentStrategy distributionCenterShipmentStrategy;
    private final TransferCenterShipmentStrategy transferCenterShipmentStrategy;
    public ShipmentStrategyTemplate getShipmentStrategy(int deliveryPoint) throws ShipmentException {
        switch (deliveryPoint){
            case 1: return branchShipmentStrategy;
            case 2: return distributionCenterShipmentStrategy;
            case 3: return transferCenterShipmentStrategy;
            default: throw new ShipmentException("Delivery point is not valid!");
        }
    }
}
