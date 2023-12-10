package com.kkk.fleetmanagement.v1.data.repository;

import com.kkk.fleetmanagement.v1.data.entity.Sack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SackRepository extends JpaRepository<Sack, String> {
    Optional<Sack> findByBarcode(String barcode);
}
