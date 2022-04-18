package ee.Karu.webshop.api;

import ee.Karu.webshop.model.input.OmnivaParcelMachine;
import ee.Karu.webshop.model.output.ParcelMachines;
import ee.Karu.webshop.service.ParcelMachineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParcelMachineController {

    @Autowired
    ParcelMachineService parcelMachineService;

    @Operation(description = "Nii omniva kui SmartPost pakiautomaatide kättesaamine")
    @GetMapping("parcel-machines/{country}")
    public ParcelMachines getParcelMachines(@PathVariable String country) {
        country = country.toUpperCase();
        return parcelMachineService.getParcelMachines(country);
    }


}
