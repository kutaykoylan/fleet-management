package com.kkk.fleetmanagement.v1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kkk.fleetmanagement.v1.data.entity.Packages;

import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Packages, String> {
    Optional<Packages> findByBarcode(String barcode);
}
