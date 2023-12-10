package com.kkk.fleetmanagement.service;

import com.kkk.fleetmanagement.v1.common.exception.ShipmentException;
import com.kkk.fleetmanagement.v1.service.ShipmentStrategyTemplate;
import com.kkk.fleetmanagement.v1.service.ShipmentStrategyFactory;
import com.kkk.fleetmanagement.v1.service.strategies.BranchShipmentStrategy;
import com.kkk.fleetmanagement.v1.service.strategies.DistributionCenterShipmentStrategy;
import com.kkk.fleetmanagement.v1.service.strategies.TransferCenterShipmentStrategy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShipmentStrategyFactoryTest {
    @InjectMocks
    private ShipmentStrategyFactory shipmentStrategyFactory;

    @Test
    @SneakyThrows
    public void testGetShipmentStrategyWithDeliveryPoint1(){
        //when
        ShipmentStrategyTemplate shipmentStrategyTemplate = shipmentStrategyFactory.getShipmentStrategy(1);
        //then
        Assertions.assertTrue(shipmentStrategyTemplate instanceof BranchShipmentStrategy);
    }

    @Test
    @SneakyThrows
    public void testGetShipmentStrategyWithDeliveryPoint2(){
        //when
        ShipmentStrategyTemplate shipmentStrategyTemplate = shipmentStrategyFactory.getShipmentStrategy(2);
        //then
        Assertions.assertTrue(shipmentStrategyTemplate instanceof DistributionCenterShipmentStrategy);
    }

    @Test
    @SneakyThrows
    public void testGetShipmentStrategyWithDeliveryPoint3(){
        //when
        ShipmentStrategyTemplate shipmentStrategyTemplate = shipmentStrategyFactory.getShipmentStrategy(3);
        //then
        Assertions.assertTrue(shipmentStrategyTemplate instanceof TransferCenterShipmentStrategy);
    }

    @Test
    public void testGetShipmentStrategyWithNotValidDeliveryPoint(){
        //when&then
        Assertions.assertThrows(ShipmentException.class,()->shipmentStrategyFactory.getShipmentStrategy(4));
    }
}
