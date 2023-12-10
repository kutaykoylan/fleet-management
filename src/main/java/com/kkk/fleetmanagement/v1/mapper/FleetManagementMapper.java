package com.kkk.fleetmanagement.v1.mapper;

import com.kkk.fleetmanagement.v1.controller.dto.DistributeShipmentRequestDto;
import com.kkk.fleetmanagement.v1.controller.dto.DistributeShipmentResponseDto;
import com.kkk.fleetmanagement.v1.data.modal.Shipment;
import com.kkk.fleetmanagement.v1.data.modal.ShipmentResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FleetManagementMapper {
    Shipment toShipment(DistributeShipmentRequestDto distributeShipmentRequestDto);
    DistributeShipmentResponseDto toDistributeShipmenResponseDto(ShipmentResult shipmentResult);
}
