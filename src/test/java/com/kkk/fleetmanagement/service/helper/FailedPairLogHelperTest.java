package com.kkk.fleetmanagement.service.helper;

import com.kkk.fleetmanagement.util.TestEntityBuilder;
import com.kkk.fleetmanagement.v1.data.entity.FailedDeliveryPointBarcodeLog;
import com.kkk.fleetmanagement.v1.data.repository.FailedDeliveryPointBarcodeLogRepository;
import com.kkk.fleetmanagement.v1.service.helper.FailedPairLogHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kkk.fleetmanagement.util.TestConstants.BARCODE_PACKAGE;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FailedPairLogHelperTest {

    @InjectMocks
    private FailedPairLogHelper failedPairLogHelper;
    @Mock
    private FailedDeliveryPointBarcodeLogRepository failedDeliveryPointBarcodeLogRepository;

    @Test
    public void testLogFailedPair(){
        //given
        Mockito.when(failedDeliveryPointBarcodeLogRepository.save(any()))
                .thenReturn(TestEntityBuilder.createFailedDeliveryPointBarcodeLog());
        //when
        FailedDeliveryPointBarcodeLog actualFailedDeliveryPointBarcodeLog = failedPairLogHelper.logFailedPair(BARCODE_PACKAGE, "1");
        //then
        Assertions.assertEquals(BARCODE_PACKAGE,actualFailedDeliveryPointBarcodeLog.getBarcode());
        Assertions.assertEquals("1",actualFailedDeliveryPointBarcodeLog.getDeliveryPoint());
        //verify
        Mockito.verify(failedDeliveryPointBarcodeLogRepository).save(any());

    }
}
