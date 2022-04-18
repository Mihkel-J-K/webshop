package ee.Karu.webshop.api;

import ee.Karu.webshop.model.input.OmnivaParcelMachine;
import ee.Karu.webshop.service.ParcelMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParcelMachineController {

    @Autowired
    ParcelMachineService parcelMachineService;

    @GetMapping("parcel-machines/{country}")
    public List<OmnivaParcelMachine> getParcelMachines(@PathVariable String country) {
        return parcelMachineService.getParcelMachines(country);

    }


}
