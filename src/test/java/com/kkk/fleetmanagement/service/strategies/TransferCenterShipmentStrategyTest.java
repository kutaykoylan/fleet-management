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
import com.kkk.fleetmanagement.v1.service.strategies.BranchShipmentStrategy;
import com.kkk.fleetmanagement.v1.service.strategies.TransferCenterShipmentStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.kkk.fleetmanagement.util.TestConstants.BARCODE_PACKAGE;
import static com.kkk.fleetmanagement.util.TestConstants.BARCODE_SACK;
import static com.kkk.fleetmanagement.util.TestEntityBuilder.*;
import static com.kkk.fleetmanagement.util.TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint;

@ExtendWith(MockitoExtension.class)
public class TransferCenterShipmentStrategyTest {
    @InjectMocks
    private TransferCenterShipmentStrategy transferCenterShipmentStrategy;
    @Mock
    private PackageRepository packageRepository;
    @Mock
    private SackRepository sackRepository;
    @Mock
    private FailedPairLogHelper failedPairLogHelper;
    @Mock
    private PackageWithSackHelper packageWithSackHelper;

    @Test
    public void testExecuteTransferCenterShipmentStrategyWithDeliveriesContainPackageWithDeliveryPointAsBranch(){
        //given
        Deliveries deliveries = createDeliveriesToDistributionCenter();
        Packages aPackage = createPackageWithBranchAsDeliveryPoint();
        Mockito.when(packageRepository.findByBarcode(BARCODE_PACKAGE))
                .thenReturn(Optional.of(aPackage));
        //when
        Deliveries shipmentResult = transferCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(3,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(packageRepository).findByBarcode(BARCODE_PACKAGE);

    }

    @Test
    public void testExecuteTransferCenterShipmentStrategyWithDeliveriesContainPackageWithDeliveryPointAsTransferCenter(){
        //given
        Deliveries deliveries = createDeliveriesToTransferCenter();
        Packages aPackage = createPackageWithTransferCenterAsDeliveryPoint();
        Mockito.when(packageRepository.findByBarcode(BARCODE_PACKAGE))
                .thenReturn(Optional.of(aPackage));
        Mockito.when(packageWithSackHelper.unloadPackageWithSack(aPackage))
                .then(invocation -> {
                    aPackage.setPackageState(ShipmentState.LOADED);
                    return null;
                });
        //when
        Deliveries shipmentResult = transferCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(3,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(packageRepository).findByBarcode(BARCODE_PACKAGE);
        Mockito.verify(packageWithSackHelper).unloadPackageWithSack(aPackage);

    }

    @Test
    public void testExecuteTransferCenterShipmentStrategyWithDeliveriesContainPackageInSackWithDeliveryPointAsTransferCenter(){
        //given
        Deliveries deliveries = createDeliveriesToTransferCenter();
        Packages aPackage = createPackageWithTransferCenterAsDeliveryPoint();
        Mockito.when(packageRepository.findByBarcode(BARCODE_PACKAGE))
                .thenReturn(Optional.of(aPackage));
        Mockito.when(packageWithSackHelper.unloadPackageWithSack(aPackage))
                .then(invocation -> {
                    aPackage.setPackageState(ShipmentState.UNLOADED);
                    return null;
                });
        //when
        Deliveries shipmentResult = transferCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(4,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(packageRepository).findByBarcode(BARCODE_PACKAGE);
        Mockito.verify(packageWithSackHelper).unloadPackageWithSack(aPackage);
    }

    @Test
    public void testExecuteTransferCenterShipmentStrategyWithDeliveriesContainUnloadedPackageInSackWithDeliveryPointAsTransferCenter(){
        //given
        Deliveries deliveries = createDeliveriesToTransferCenter();
        Packages aPackage = createPackageWithTransferCenterAsDeliveryPoint();
        Mockito.when(packageRepository.findByBarcode(BARCODE_PACKAGE))
                .thenReturn(Optional.of(aPackage));
        Mockito.when(packageWithSackHelper.unloadPackageWithSack(aPackage))
                .then(invocation -> {
                    aPackage.setPackageState(ShipmentState.UNLOADED);
                    return null;
                });
        //when
        Deliveries shipmentResult = transferCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(4,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(packageRepository).findByBarcode(BARCODE_PACKAGE);
        Mockito.verify(packageWithSackHelper).unloadPackageWithSack(aPackage);
    }

    @Test
    public void testTransferCenterShipmentStrategyWithDeliveriesContainSackWithDeliveryPointAsTransferCenter(){
        //given
        Deliveries deliveries = createDeliveriesToTransferCenterWithSack();
        Sack aSack = createSackWithTransferCenterAsDeliveryPoint();
        Mockito.when(sackRepository.findByBarcode(BARCODE_SACK))
                .thenReturn(Optional.of(aSack));
        Mockito.when(packageWithSackHelper.unloadAllPackagesInSack(aSack))
                .then(invocation -> {
                    aSack.setSackState(ShipmentState.UNLOADED);
                    return null;
                });
        //when
        Deliveries shipmentResult = transferCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_SACK,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(4,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(sackRepository).findByBarcode(BARCODE_SACK);
        Mockito.verify(packageWithSackHelper).unloadAllPackagesInSack(aSack);
    }

    @Test
    public void testTransferCenterShipmentStrategyWithDeliveriesContainSackWithDeliveryPointAsDistributionCenter(){
        //given
        Deliveries deliveries = createDeliveriesToTransferCenterWithSack();
        Sack aSack = createSackWithDistributionCenterAsDeliveryPoint();
        Mockito.when(sackRepository.findByBarcode(BARCODE_SACK))
                .thenReturn(Optional.of(aSack));
        //when
        Deliveries shipmentResult = transferCenterShipmentStrategy.execute(deliveries);
        //then
        Assertions.assertEquals(BARCODE_SACK,shipmentResult.getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(3,shipmentResult.getDeliveries().get(0).getState());
        //verify
        Mockito.verify(sackRepository).findByBarcode(BARCODE_SACK);
    }
}
