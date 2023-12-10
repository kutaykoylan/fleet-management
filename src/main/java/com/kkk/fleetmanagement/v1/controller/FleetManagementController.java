package com.kkk.fleetmanagement.v1.controller;

import com.kkk.fleetmanagement.v1.common.exception.ShipmentException;
import com.kkk.fleetmanagement.v1.common.modal.ResponseDto;
import com.kkk.fleetmanagement.v1.controller.dto.DistributeShipmentRequestDto;
import com.kkk.fleetmanagement.v1.controller.dto.DistributeShipmentResponseDto;
import com.kkk.fleetmanagement.v1.data.modal.ShipmentResult;
import com.kkk.fleetmanagement.v1.mapper.FleetManagementMapper;
import com.kkk.fleetmanagement.v1.service.FleetManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Log4j2
public class FleetManagementController {
    private final FleetManagementService fleetManagementService;
    private final FleetManagementMapper fleetManagementMapper;

    @PostMapping("/vehicles/{vehiclePlate}/distribute")
    public ResponseEntity<DistributeShipmentResponseDto> distributeShipment(@PathVariable String vehiclePlate, @RequestBody DistributeShipmentRequestDto distributeShipmentRequestDto) throws ShipmentException {
        log.debug("Shipment for "+vehiclePlate+" arrived.");
        ShipmentResult shipmentResult = fleetManagementService.distributeShipment(fleetManagementMapper.toShipment(distributeShipmentRequestDto));
        return new ResponseEntity<>(fleetManagementMapper.toDistributeShipmenResponseDto(shipmentResult), HttpStatus.OK);
    }

    @ExceptionHandler({ShipmentException.class})
    public ResponseEntity<ResponseDto> handleException(Exception e, HttpServletRequest request) {
        String error = e.getMessage();
        log.error(request.getRequestURI(),error);
        return new ResponseEntity<>(new ResponseDto(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

}
