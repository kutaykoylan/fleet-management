package com.kkk.fleetmanagement.v1.service.strategies;

import com.kkk.fleetmanagement.v1.data.entity.Packages;
import com.kkk.fleetmanagement.v1.data.entity.Sack;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import com.kkk.fleetmanagement.v1.data.modal.Delivery;
import com.kkk.fleetmanagement.v1.data.repository.PackageRepository;
import com.kkk.fleetmanagement.v1.data.repository.SackRepository;
import com.kkk.fleetmanagement.v1.service.helper.FailedPairLogHelper;
import com.kkk.fleetmanagement.v1.service.ShipmentStrategyTemplate;
import com.kkk.fleetmanagement.v1.service.helper.PackageWithSackHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class BranchShipmentStrategy extends ShipmentStrategyTemplate {
    private final PackageRepository packageRepository;
    private final SackRepository sackRepository;
    private final FailedPairLogHelper failedPairLogHelper;
    private final PackageWithSackHelper packageWithSackHelper;

    public void unloadIfSack(List<Delivery> deliveryWithStateList, Delivery delivery) {
        Optional<Sack> aSack = sackRepository.findByBarcode(delivery.getBarcode());
        if(aSack.isPresent()){
            Sack sackEntity = aSack.get();
            packageWithSackHelper.loadPackagesIntoSack(sackEntity);
            sackEntity.setSackState(ShipmentState.LOADED);
            sackRepository.save(sackEntity);
            failedPairLogHelper.logFailedPair(delivery.getBarcode(),"1");
            deliveryWithStateList.add(new Delivery(sackEntity.getBarcode(),sackEntity.getSackState().ordinal()+1));

        }
    }

    public void unloadIfPackage(List<Delivery> deliveryWithStateList, Delivery delivery) {
        Optional<Packages> aPackage = packageRepository.findByBarcode(delivery.getBarcode());
        if(aPackage.isPresent()){
            Packages packageEntity = aPackage.get();
            if(aPackage.get().getDeliveryPoint().getId() == 1 ){
                packageEntity.setPackageState(ShipmentState.UNLOADED);
            }else{
                packageEntity.setPackageState(ShipmentState.LOADED);
                failedPairLogHelper.logFailedPair(delivery.getBarcode(),"1");

            }
            packageRepository.save(packageEntity);
            deliveryWithStateList.add(new Delivery(packageEntity.getBarcode(),packageEntity.getPackageState().ordinal()+1));
        }
    }

}
