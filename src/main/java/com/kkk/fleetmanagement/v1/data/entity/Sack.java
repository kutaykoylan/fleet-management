package com.kkk.fleetmanagement.v1.data.entity;

import com.kkk.fleetmanagement.v1.common.entity.BaseEntity;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "barcode", callSuper = false)
@Entity
@Table(name = "SACKS")
public class Sack extends BaseEntity {

    @Id
    @Column(name = "BARCODE",unique = true)
    private String barcode;

    @OneToOne
    @JoinColumn(name = "DELIVERY_POINT")
    private DeliveryPoint deliveryPoint;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private ShipmentState sackState = ShipmentState.CREATED;
}
