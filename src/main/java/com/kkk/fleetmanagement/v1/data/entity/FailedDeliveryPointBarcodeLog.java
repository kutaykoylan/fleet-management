package com.kkk.fleetmanagement.v1.data.entity;

import com.kkk.fleetmanagement.v1.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "FAILED_DELIVERY_POINT_BARCODE_LOG")
@SequenceGenerator(name = "idgen", sequenceName = "LOG_SEQ")
public class FailedDeliveryPointBarcodeLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "ID")
    private Long id;
    @Column(name = "DELIVERY_POINT")
    private String deliveryPoint;
    @Column(name = "BARCODE")
    private String barcode;
}
