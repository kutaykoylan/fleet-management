package com.kkk.fleetmanagement.v1.data.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Delivery {
    private String barcode;
    private int state;
}
