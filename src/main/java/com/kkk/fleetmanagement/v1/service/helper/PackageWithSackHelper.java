package com.kkk.fleetmanagement.v1.service.helper;

import com.kkk.fleetmanagement.v1.data.entity.PackageWithSack;
import com.kkk.fleetmanagement.v1.data.entity.Packages;
import com.kkk.fleetmanagement.v1.data.entity.Sack;
import com.kkk.fleetmanagement.v1.data.enums.ShipmentState;
import com.kkk.fleetmanagement.v1.data.repository.PackageRepository;
import com.kkk.fleetmanagement.v1.data.repository.PackageWithSackRepository;
import com.kkk.fleetmanagement.v1.data.repository.SackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PackageWithSackHelper {
    private final PackageWithSackRepository packageWithSackRepository;
    private final PackageRepository packageRepository;
    private final SackRepository sackRepository;
    public void loadPackagesIntoSack(Sack sackEntity) {
        for(PackageWithSack packageWithSack:packageWithSackRepository.findAllByBarcodeOfSack(sackEntity)){
            if(packageWithSack.getBarcodeOfPackage().getPackageState().equals(ShipmentState.CREATED)){
                packageWithSack.getBarcodeOfPackage().setPackageState(ShipmentState.LOADED_INTO_SACK);
                packageRepository.save(packageWithSack.getBarcodeOfPackage());
            }
        }
    }

    public Sack unloadAllPackagesInSack(Sack sackEntity) {
        for(PackageWithSack packageWithSack:packageWithSackRepository.findAllByBarcodeOfSack(sackEntity)){
            packageWithSack.getBarcodeOfPackage().setPackageState(ShipmentState.UNLOADED);
            packageRepository.save(packageWithSack.getBarcodeOfPackage());
        }
        sackEntity.setSackState(ShipmentState.UNLOADED);
        return sackEntity;
    }

    public void checkIfSackUnloaded(Sack barcodeOfSack) {
        for (PackageWithSack packageWithSack:packageWithSackRepository.findAllByBarcodeOfSack(barcodeOfSack)){
            if(!packageWithSack.getBarcodeOfPackage().getPackageState().equals(ShipmentState.UNLOADED)){
                return;
            }
        }
        barcodeOfSack.setSackState(ShipmentState.UNLOADED);
        sackRepository.save(barcodeOfSack);
    }

    public Packages unloadPackageWithSack(Packages packageEntity) {
        Optional<PackageWithSack> packageWithSack = packageWithSackRepository.findByBarcodeOfPackage(packageEntity);
        if(packageWithSack.isPresent()){
            packageEntity.setPackageState(ShipmentState.UNLOADED);
            checkIfSackUnloaded(packageWithSack.get().getBarcodeOfSack());
        }else{
            packageEntity.setPackageState(ShipmentState.LOADED);
        }
        return packageEntity;
    }
    
}
