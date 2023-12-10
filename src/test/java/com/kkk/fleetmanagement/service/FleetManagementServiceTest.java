package com.kkk.fleetmanagement.service;

import com.kkk.fleetmanagement.util.TestEntityBuilder;
import com.kkk.fleetmanagement.v1.data.modal.ShipmentResult;
import com.kkk.fleetmanagement.v1.data.repository.PackageRepository;
import com.kkk.fleetmanagement.v1.data.repository.PackageWithSackRepository;
import com.kkk.fleetmanagement.v1.data.repository.SackRepository;
import com.kkk.fleetmanagement.v1.service.FleetManagementService;
import com.kkk.fleetmanagement.v1.service.FleetManagementServiceImpl;
import com.kkk.fleetmanagement.v1.service.ShipmentStrategyFactory;
import com.kkk.fleetmanagement.v1.service.helper.FailedPairLogHelper;
import com.kkk.fleetmanagement.v1.service.helper.PackageWithSackHelper;
import com.kkk.fleetmanagement.v1.service.strategies.BranchShipmentStrategy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kkk.fleetmanagement.util.TestConstants.VEHICLE;

@ExtendWith(MockitoExtension.class)
public class FleetManagementServiceTest {
    private FleetManagementService fleetManagementService;
    @Mock
    private ShipmentStrategyFactory shipmentStrategyFactory;
    @Mock
    private PackageRepository packageRepository;
    @Mock
    private SackRepository sackRepository;
    @Mock
    private FailedPairLogHelper failedPairLogHelper;
    @Mock
    private PackageWithSackHelper packageWithSackHelper;

    @BeforeEach
    public void setup(){
        fleetManagementService = new FleetManagementServiceImpl(shipmentStrategyFactory);
    }

    @Test
    @SneakyThrows
    public void testDistributeShipment() {
        //given
        Mockito.when(shipmentStrategyFactory.getShipmentStrategy(1))
                .thenReturn(new BranchShipmentStrategy(packageRepository, sackRepository, failedPairLogHelper,packageWithSackHelper));
        //when
        ShipmentResult shipmentResult = fleetManagementService.distributeShipment(TestEntityBuilder.createShipment());
        //then
        Assertions.assertEquals(VEHICLE,shipmentResult.getVehicle());
        Assertions.assertEquals(1,shipmentResult.getRoute().get(0).getDeliveryPoint());
        //verify
        Mockito.verify(shipmentStrategyFactory).getShipmentStrategy(1);
    }
}
