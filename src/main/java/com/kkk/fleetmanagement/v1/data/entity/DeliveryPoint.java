package com.kkk.fleetmanagement.v1.data.entity;

import com.kkk.fleetmanagement.v1.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "DELIVERY_POINTS")
public class DeliveryPoint extends BaseEntity {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

}
