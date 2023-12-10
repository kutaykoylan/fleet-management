package com.kkk.fleetmanagement.util;

import com.kkk.fleetmanagement.v1.controller.dto.DistributeShipmentRequestDto;
import com.kkk.fleetmanagement.v1.data.entity.DeliveryPoint;
import com.kkk.fleetmanagement.v1.data.entity.FailedDeliveryPointBarcodeLog;
import com.kkk.fleetmanagement.v1.data.entity.Packages;
import com.kkk.fleetmanagement.v1.data.entity.Sack;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import com.kkk.fleetmanagement.v1.data.modal.Deliveries;
import com.kkk.fleetmanagement.v1.data.modal.Delivery;
import com.kkk.fleetmanagement.v1.data.modal.Shipment;
import com.kkk.fleetmanagement.v1.data.modal.ShipmentResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.kkk.fleetmanagement.util.TestConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestEntityBuilder {

    public static DistributeShipmentRequestDto createDistributeShipmentRequestDto() {
        DistributeShipmentRequestDto distributeShipmentRequestDto = new DistributeShipmentRequestDto();
        distributeShipmentRequestDto.setVehicle(VEHICLE);
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(1);
        deliveries.setDeliveries(List.of(new Delivery(BARCODE_PACKAGE, ShipmentState.CREATED.ordinal() + 1)));
        distributeShipmentRequestDto.setRoute(List.of(deliveries));
        return distributeShipmentRequestDto;
    }

    public static Shipment createShipment() {
        Shipment shipment = new Shipment();
        shipment.setVehicle(VEHICLE);
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(1);
        deliveries.setDeliveries(List.of(new Delivery(BARCODE_PACKAGE, ShipmentState.CREATED.ordinal() + 1)));
        shipment.setRoute(List.of(deliveries));
        return shipment;
    }

    public static ShipmentResult createShipmentResult() {
        ShipmentResult shipmentResult = new ShipmentResult();
        shipmentResult.setVehicle(VEHICLE);
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(1);
        deliveries.setDeliveries(List.of(new Delivery(BARCODE_PACKAGE, ShipmentState.CREATED.ordinal() + 1)));
        shipmentResult.setRoute(List.of(deliveries));
        return shipmentResult;
    }

    public static FailedDeliveryPointBarcodeLog createFailedDeliveryPointBarcodeLog() {
        FailedDeliveryPointBarcodeLog failedDeliveryPointBarcodeLog = new FailedDeliveryPointBarcodeLog();
        failedDeliveryPointBarcodeLog.setDeliveryPoint("1");
        failedDeliveryPointBarcodeLog.setBarcode(BARCODE_PACKAGE);
        return failedDeliveryPointBarcodeLog;
    }

    public static Sack createSackWithBranchAsDeliveryPoint() {
        Sack aSack = new Sack();
        aSack.setBarcode(BARCODE_SACK);
        aSack.setDeliveryPoint(new DeliveryPoint(1L, "Branch"));
        aSack.setSackState(ShipmentState.CREATED);
        return aSack;
    }

    public static Sack createSackWithDistributionCenterAsDeliveryPoint() {
        Sack aSack = new Sack();
        aSack.setBarcode(BARCODE_SACK);
        aSack.setDeliveryPoint(new DeliveryPoint(2L, "Distribution Center"));
        aSack.setSackState(ShipmentState.CREATED);
        return aSack;
    }
    public static Sack createSackWithTransferCenterAsDeliveryPoint() {
        Sack aSack = new Sack();
        aSack.setBarcode(BARCODE_SACK);
        aSack.setDeliveryPoint(new DeliveryPoint(3L, "Transfer Center"));
        aSack.setSackState(ShipmentState.CREATED);
        return aSack;
    }

    public static Packages createPackageWithBranchAsDeliveryPoint() {
        Packages aPackage = new Packages();
        aPackage.setBarcode(BARCODE_PACKAGE);
        aPackage.setDeliveryPoint(new DeliveryPoint(1L, "Branch"));
        aPackage.setPackageState(ShipmentState.CREATED);
        return aPackage;
    }
    public static Packages createUnloadedPackageWithBranchAsDeliveryPoint() {
        Packages aPackage = new Packages();
        aPackage.setBarcode(BARCODE_PACKAGE);
        aPackage.setDeliveryPoint(new DeliveryPoint(1L, "Branch"));
        aPackage.setPackageState(ShipmentState.UNLOADED);
        return aPackage;
    }

    public static Packages createPackageWithDistributionCenterAsDeliveryPoint() {
        Packages aPackage = new Packages();
        aPackage.setBarcode(BARCODE_PACKAGE);
        aPackage.setDeliveryPoint(new DeliveryPoint(2L, "Distribution Center"));
        aPackage.setPackageState(ShipmentState.CREATED);
        return aPackage;
    }
    public static Packages createPackageWithTransferCenterAsDeliveryPoint() {
        Packages aPackage = new Packages();
        aPackage.setBarcode(BARCODE_PACKAGE);
        aPackage.setDeliveryPoint(new DeliveryPoint(3L, "Transfer Center"));
        aPackage.setPackageState(ShipmentState.CREATED);
        return aPackage;
    }

    public static Deliveries createDeliveriesToBranch() {
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(1);
        Delivery delivery = new Delivery();
        delivery.setBarcode(BARCODE_PACKAGE);
        deliveries.setDeliveries(List.of(delivery));
        return deliveries;
    }

    public static Deliveries createDeliveriesToDistributionCenter() {
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(2);
        Delivery delivery = new Delivery();
        delivery.setBarcode(BARCODE_PACKAGE);
        deliveries.setDeliveries(List.of(delivery));
        return deliveries;
    }

    public static Deliveries createDeliveriesToTransferCenter() {
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(3);
        Delivery delivery = new Delivery();
        delivery.setBarcode(BARCODE_PACKAGE);
        deliveries.setDeliveries(List.of(delivery));
        return deliveries;
    }

    public static Deliveries createDeliveriesToBranchWithSack() {
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(1);
        Delivery delivery = new Delivery();
        delivery.setBarcode(BARCODE_SACK);
        deliveries.setDeliveries(List.of(delivery));
        return deliveries;
    }
    public static Deliveries createDeliveriesToDistributionCenterWithSack() {
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(2);
        Delivery delivery = new Delivery();
        delivery.setBarcode(BARCODE_SACK);
        deliveries.setDeliveries(List.of(delivery));
        return deliveries;
    }

    public static Deliveries createDeliveriesToTransferCenterWithSack() {
        Deliveries deliveries = new Deliveries();
        deliveries.setDeliveryPoint(3);
        Delivery delivery = new Delivery();
        delivery.setBarcode(BARCODE_SACK);
        deliveries.setDeliveries(List.of(delivery));
        return deliveries;
    }
}
