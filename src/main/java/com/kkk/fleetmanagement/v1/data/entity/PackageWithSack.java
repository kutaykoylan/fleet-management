package com.kkk.fleetmanagement.v1.data.entity;

import com.kkk.fleetmanagement.v1.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "PACKAGE_SACK")
public class PackageWithSack extends BaseEntity {
    @Id
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "BARCODE_OF_PACKAGE")
    private Packages barcodeOfPackage;

    @OneToOne
    @JoinColumn(name = "BARCODE_OF_SACK")
    private Sack barcodeOfSack;


}
