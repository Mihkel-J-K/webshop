package ee.Karu.webshop.model.request.output;

import ee.Karu.webshop.model.request.input.OmnivaParcelMachine;
import ee.Karu.webshop.model.request.input.SmartPostParcelMachine;
import lombok.Data;

import java.util.List;

@Data
public class ParcelMachines {
    private List<OmnivaParcelMachine> omnivaParcelMachines;
    private List<SmartPostParcelMachine> smartpostParcelMachines;
}
