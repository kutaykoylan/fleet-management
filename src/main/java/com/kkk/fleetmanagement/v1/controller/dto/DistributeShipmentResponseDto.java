package com.kkk.fleetmanagement.v1.controller.dto;

import com.kkk.fleetmanagement.v1.data.modal.Deliveries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistributeShipmentResponseDto {
    private String vehicle;
    private List<Deliveries> route;
}
