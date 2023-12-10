package com.kkk.fleetmanagement.v1.data.repository;

import com.kkk.fleetmanagement.v1.data.entity.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, Long> {
}
