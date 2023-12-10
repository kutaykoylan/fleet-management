package com.kkk.fleetmanagement.v1.data.entity;

import com.kkk.fleetmanagement.v1.common.entity.BaseEntity;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(of = "barcode", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PACKAGES")
public class Packages extends BaseEntity {

    @Id
    @Column(name = "BARCODE",unique = true)
    private String barcode;

    @OneToOne
    @JoinColumn(name = "DELIVERY_POINT")
    private DeliveryPoint deliveryPoint;

    @Column(name = "DESI")
    private int desi;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private ShipmentState packageState = ShipmentState.CREATED;
}
