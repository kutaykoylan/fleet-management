package com.kkk.fleetmanagement.v1.data.repository;

import com.kkk.fleetmanagement.v1.data.entity.FailedDeliveryPointBarcodeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedDeliveryPointBarcodeLogRepository extends JpaRepository<FailedDeliveryPointBarcodeLog, Long> {
}
