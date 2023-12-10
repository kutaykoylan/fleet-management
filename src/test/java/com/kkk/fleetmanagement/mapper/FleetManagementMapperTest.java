package com.kkk.fleetmanagement.mapper;

import com.kkk.fleetmanagement.util.TestEntityBuilder;
import com.kkk.fleetmanagement.v1.controller.dto.DistributeShipmentResponseDto;
import com.kkk.fleetmanagement.v1.data.modal.Shipment;
import com.kkk.fleetmanagement.v1.mapper.FleetManagementMapper;
import com.kkk.fleetmanagement.v1.mapper.FleetManagementMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.kkk.fleetmanagement.util.TestConstants.BARCODE_PACKAGE;
import static com.kkk.fleetmanagement.util.TestConstants.VEHICLE;

public class FleetManagementMapperTest {
    private final FleetManagementMapper fleetManagementMapper = new FleetManagementMapperImpl();
    @Test
    public void testToShipmentWithNullArgument(){
        //when
        Shipment shipment = fleetManagementMapper.toShipment(null);
        //then
        Assertions.assertNull(shipment);
    }
    @Test
    public void testToShipment(){
        //when
        Shipment shipment = fleetManagementMapper.toShipment(TestEntityBuilder.createDistributeShipmentRequestDto());
        //then
        Assertions.assertNotNull(shipment);
        Assertions.assertEquals(VEHICLE,shipment.getVehicle());
        Assertions.assertEquals(1,shipment.getRoute().size());
        Assertions.assertEquals(1,shipment.getRoute().get(0).getDeliveryPoint());
        Assertions.assertEquals(BARCODE_PACKAGE,shipment.getRoute().get(0).getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(1,shipment.getRoute().get(0).getDeliveries().get(0).getState());
    }
    @Test
    public void testToDistributeShipmenResponseDtoWithNullArgument(){
        //when
        DistributeShipmentResponseDto distributeShipmentResponseDto = fleetManagementMapper.toDistributeShipmenResponseDto(null);
        //then
        Assertions.assertNull(distributeShipmentResponseDto);
    }

    @Test
    public void testToDistributeShipmenResponseDto(){
        //when
        DistributeShipmentResponseDto distributeShipmentResponseDto = fleetManagementMapper.toDistributeShipmenResponseDto(TestEntityBuilder.createShipmentResult());
        //then
        Assertions.assertNotNull(distributeShipmentResponseDto);
        Assertions.assertEquals(VEHICLE,distributeShipmentResponseDto.getVehicle());
        Assertions.assertEquals(1,distributeShipmentResponseDto.getRoute().size());
        Assertions.assertEquals(1,distributeShipmentResponseDto.getRoute().get(0).getDeliveryPoint());
        Assertions.assertEquals(BARCODE_PACKAGE,distributeShipmentResponseDto.getRoute().get(0).getDeliveries().get(0).getBarcode());
        Assertions.assertEquals(1,distributeShipmentResponseDto.getRoute().get(0).getDeliveries().get(0).getState());
    }

}
