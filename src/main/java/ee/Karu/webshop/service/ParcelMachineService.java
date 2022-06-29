package ee.Karu.webshop.service;

import ee.Karu.webshop.model.request.output.ParcelMachines;

public interface ParcelMachineService {

    ParcelMachines getParcelMachines(String country);
}
