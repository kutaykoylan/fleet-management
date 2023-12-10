package com.kkk.fleetmanagement.service.strategies;

import com.kkk.fleetmanagement.v1.data.entity.PackageWithSack;
import com.kkk.fleetmanagement.v1.data.entity.Packages;
import com.kkk.fleetmanagement.v1.data.entity.Sack;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import com.kkk.fleetmanagement.v1.data.modal.Deliveries;
import com.kkk.fleetmanagement.v1.data.repository.PackageRepository;
import com.kkk.fleetmanagement.v1.data.repository.PackageWithSackRepository;
import com.kkk.fleetmanagement.v1.data.repository.SackRepository;
import com.kkk.fleetmanagement.v1.service.helper.FailedPairLogHelper;
import com.kkk.fleetmanagement.v1.service.helper.PackageWithSackHelper;
import com.kkk.fleetmanagement.v1.service.strategies.DistributionCenterShipmentStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.kkk.fleetmanagement.util.TestConstants.BARCODE_PACKAGE;
import static com.kkk.fleetmanagement.util.TestConstants.BARCODE_SACK;
import static com.kkk.fleetmanagement.util.TestEntityBuilder.*;

@ExtendWith(MockitoExtension.class)
public class DistributionCenterShipmentStrategyTest {
    @InjectMocks
    private DistributionCenterShipmentStrategy distributionCenterShipmentStrategy;
    @Mock
    private PackageRepository packageRepository;
    @Mock
    private SackRepository sackRepository;
    @Mock
    private PackageWithSackRepository packageWithSackRepository;
    @Mock
    private FailedPairLogHelper failedPairLogHelper;
    @Mock
    private PackageWithSackHelper packageWithSackHelper;



    @Test
    public void testExecuteDistributionCenterShipmentStrategyWithDeliveriesContainPackageWithDeliveryPointAsBranch(){
        //given
        Deliveries deliveries = createDeliveriesToDistributionCenter();
        Packages aPackage = createPackageWithBranchAsDeliveryPoint();
        Mockito.when(packageRepository.findByBarcode(BARCODE_PACKAGE))
                .thenReturn(Optional.of(aPackage));
        //when
        Deliveries shipmentResult = distributionCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(3,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(packageRepository).findByBarcode(BARCODE_PACKAGE);

    }

    @Test
    public void testExecuteDistributionCenterShipmentStrategyWithDeliveriesContainPackageWithDeliveryPointAsDistributionCenter(){
        //given
        Deliveries deliveries = createDeliveriesToDistributionCenter();
        Packages aPackage = createPackageWithDistributionCenterAsDeliveryPoint();
        Mockito.when(packageRepository.findByBarcode(BARCODE_PACKAGE))
                .thenReturn(Optional.of(aPackage));
        //when
        Deliveries shipmentResult = distributionCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(4,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(packageRepository).findByBarcode(BARCODE_PACKAGE);

    }

    @Test
    public void testDistributionCenterShipmentStrategyWithDeliveriesContainSackWithDeliveryPointAsDistributionCenter(){
        //given
        Deliveries deliveries = createDeliveriesToDistributionCenterWithSack();
        Sack aSack = createSackWithDistributionCenterAsDeliveryPoint();
        Mockito.when(sackRepository.findByBarcode(BARCODE_SACK))
                .thenReturn(Optional.of(aSack));
        Mockito.when(packageWithSackHelper.unloadAllPackagesInSack(aSack))
                .then(invocation -> {
                   aSack.setSackState(ShipmentState.UNLOADED);
                    return null;
                });
        //when
        Deliveries shipmentResult = distributionCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_SACK,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(4,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(sackRepository).findByBarcode(BARCODE_SACK);
        Mockito.verify(packageWithSackHelper).unloadAllPackagesInSack(aSack);
    }

    @Test
    public void testDistributionCenterShipmentStrategyWithDeliveriesContainSackWithDeliveryPointAsBranch(){
        //given
        Deliveries deliveries = createDeliveriesToBranchWithSack();
        Sack aSack = createSackWithBranchAsDeliveryPoint();
        Mockito.when(sackRepository.findByBarcode(BARCODE_SACK))
                .thenReturn(Optional.of(aSack));
        //when
        Deliveries shipmentResult = distributionCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_SACK,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(3,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(sackRepository).findByBarcode(BARCODE_SACK);
    }
}
