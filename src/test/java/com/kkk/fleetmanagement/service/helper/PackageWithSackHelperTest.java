package com.kkk.fleetmanagement.service.helper;

import com.kkk.fleetmanagement.util.TestEntityBuilder;
import com.kkk.fleetmanagement.v1.data.entity.PackageWithSack;
import com.kkk.fleetmanagement.v1.data.entity.Packages;
import com.kkk.fleetmanagement.v1.data.entity.Sack;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import com.kkk.fleetmanagement.v1.data.repository.PackageRepository;
import com.kkk.fleetmanagement.v1.data.repository.PackageWithSackRepository;
import com.kkk.fleetmanagement.v1.data.repository.SackRepository;
import com.kkk.fleetmanagement.v1.service.helper.PackageWithSackHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PackageWithSackHelperTest {
    @InjectMocks
    private PackageWithSackHelper packageWithSackHelper;
    @Mock
    private PackageRepository packageRepository;
    @Mock
    private SackRepository sackRepository;
    @Mock
    private PackageWithSackRepository packageWithSackRepository;

    @Test
    public void testLoadPackageIntoSack(){
        //given
        Sack sack = TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint();
        Packages packages = TestEntityBuilder.createPackageWithBranchAsDeliveryPoint();
        Mockito.when(packageWithSackRepository.findAllByBarcodeOfSack(sack))
                .thenReturn(List.of(new PackageWithSack(1L
                        , packages
                        ,sack)));
        //when
        packageWithSackHelper.loadPackagesIntoSack(sack);
        //then
        Assertions.assertEquals(ShipmentState.LOADED_INTO_SACK,packages.getPackageState());
        //verify
        Mockito.verify(packageWithSackRepository).findAllByBarcodeOfSack(sack);
        Mockito.verify(packageRepository).save(packages);
    }

    @Test
    public void testUnloadAllPackagesInSack(){
        //given
        Sack sack = TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint();
        Packages packages = TestEntityBuilder.createPackageWithBranchAsDeliveryPoint();
        Mockito.when(packageWithSackRepository.findAllByBarcodeOfSack(sack))
                .thenReturn(List.of(new PackageWithSack(1L
                        , packages
                        ,sack)));
        //when
        packageWithSackHelper.unloadAllPackagesInSack(sack);
        //then
        Assertions.assertEquals(ShipmentState.UNLOADED,packages.getPackageState());
        Assertions.assertEquals(ShipmentState.UNLOADED,sack.getSackState());
        //verify
        Mockito.verify(packageWithSackRepository).findAllByBarcodeOfSack(sack);
        Mockito.verify(packageRepository).save(packages);
    }

    @Test
    public void testCheckIfSackUnloadedWithASackContainingACreatedStatePackage(){
        //given
        Sack sack = TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint();
        Packages packages = TestEntityBuilder.createPackageWithBranchAsDeliveryPoint();
        packages.setPackageState(ShipmentState.CREATED);
        Mockito.when(packageWithSackRepository.findAllByBarcodeOfSack(sack))
                .thenReturn(List.of(new PackageWithSack(1L
                        , packages
                        ,sack)));
        //when
        packageWithSackHelper.checkIfSackUnloaded(sack);
        //then
        Assertions.assertEquals(ShipmentState.CREATED,sack.getSackState());
        //verify
        Mockito.verify(packageWithSackRepository).findAllByBarcodeOfSack(sack);
    }

    @Test
    public void testCheckIfSackUnloadedWithASackContainingOnlyUnloadedStatePackage(){
        //given
        Sack sack = TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint();
        Packages packages = TestEntityBuilder.createPackageWithBranchAsDeliveryPoint();
        packages.setPackageState(ShipmentState.UNLOADED);
        Mockito.when(packageWithSackRepository.findAllByBarcodeOfSack(sack))
                .thenReturn(List.of(new PackageWithSack(1L
                        , packages
                        ,sack)));
        //when
        packageWithSackHelper.checkIfSackUnloaded(sack);
        //then
        Assertions.assertEquals(ShipmentState.UNLOADED,sack.getSackState());
        //verify
        Mockito.verify(packageWithSackRepository).findAllByBarcodeOfSack(sack);
        Mockito.verify(sackRepository).save(sack);
    }

    @Test
    public void testUnloadPackageWithSackWithAPackageInASack(){
        //given
        Sack sack = TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint();
        Packages packages = TestEntityBuilder.createPackageWithBranchAsDeliveryPoint();
        Mockito.when(packageWithSackRepository.findByBarcodeOfPackage(packages))
                .thenReturn(Optional.of(new PackageWithSack(1L
                        , packages
                        ,sack)));
        //when
        packageWithSackHelper.unloadPackageWithSack(packages);
        //then
        Assertions.assertEquals(ShipmentState.UNLOADED,packages.getPackageState());
        Assertions.assertEquals(ShipmentState.UNLOADED,sack.getSackState());
        //verify
        Mockito.verify(packageWithSackRepository).findByBarcodeOfPackage(packages);
        Mockito.verify(sackRepository).save(sack);
    }

    @Test
    public void testUnloadPackageWithSackWithAPackageNotInASack(){
        //given
        Sack sack = TestEntityBuilder.createSackWithDistributionCenterAsDeliveryPoint();
        Packages packages = TestEntityBuilder.createPackageWithBranchAsDeliveryPoint();
        Mockito.when(packageWithSackRepository.findByBarcodeOfPackage(packages))
                .thenReturn(Optional.empty());
        //when
        packageWithSackHelper.unloadPackageWithSack(packages);
        //then
        Assertions.assertEquals(ShipmentState.LOADED,packages.getPackageState());
        Assertions.assertNotEquals(ShipmentState.UNLOADED,sack.getSackState());
        //verify
        Mockito.verify(packageWithSackRepository).findByBarcodeOfPackage(packages);
    }

}
