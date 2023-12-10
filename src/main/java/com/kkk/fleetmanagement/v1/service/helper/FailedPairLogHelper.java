package com.kkk.fleetmanagement.v1.service.helper;

import com.kkk.fleetmanagement.v1.data.entity.FailedDeliveryPointBarcodeLog;
import com.kkk.fleetmanagement.v1.data.repository.FailedDeliveryPointBarcodeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FailedPairLogHelper {
    private final FailedDeliveryPointBarcodeLogRepository failedDeliveryPointBarcodeLogRepository;

    public FailedDeliveryPointBarcodeLog logFailedPair(String barcode,String deliveryPoint){
        FailedDeliveryPointBarcodeLog failedDeliveryPointBarcodeLog = new FailedDeliveryPointBarcodeLog();
        failedDeliveryPointBarcodeLog.setBarcode(barcode);
        failedDeliveryPointBarcodeLog.setDeliveryPoint(deliveryPoint);
        return failedDeliveryPointBarcodeLogRepository.save(failedDeliveryPointBarcodeLog);
    }

}
