package ee.Karu.webshop.model.output;

import ee.Karu.webshop.model.input.OmnivaParcelMachine;
import ee.Karu.webshop.model.input.SmartPostParcelMachine;
import lombok.Data;

import java.util.List;

@Data
public class ParcelMachines {
    private List<OmnivaParcelMachine> omnivaParcelMachines;
    private List<SmartPostParcelMachine> smartpostParcelMachines;
}
