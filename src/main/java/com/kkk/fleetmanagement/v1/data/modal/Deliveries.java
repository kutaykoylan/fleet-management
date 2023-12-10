package com.kkk.fleetmanagement.v1.data.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Deliveries {
    private int deliveryPoint;
    private List<Delivery> deliveries;
}
