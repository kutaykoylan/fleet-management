package com.kkk.fleetmanagement.v1.data.repository;

import com.kkk.fleetmanagement.v1.data.entity.Packages;
import com.kkk.fleetmanagement.v1.data.entity.PackageWithSack;
import com.kkk.fleetmanagement.v1.data.entity.Sack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageWithSackRepository extends JpaRepository<PackageWithSack, Long> {
    List<PackageWithSack> findAllByBarcodeOfSack(Sack barcodeOfSack);
    Optional<PackageWithSack> findByBarcodeOfPackage(Packages barcodeOfPackage);
}
