package ee.Karu.webshop.api;

import ee.Karu.webshop.model.request.output.ParcelMachines;
import ee.Karu.webshop.service.ParcelMachineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ParcelMachineController {

    @Autowired
    ParcelMachineService parcelMachineService;

    @Operation(description = "Nii omniva kui SmartPost pakiautomaatide kättesaamine")
    @GetMapping("parcel-machines/{country}")
    public ResponseEntity<ParcelMachines> getParcelMachines(@PathVariable String country) {
        log.info("Taking parcel machines {}", country);
        country = country.toUpperCase();
        return ResponseEntity.ok()
        .body(parcelMachineService.getParcelMachines(country));
    }


}
