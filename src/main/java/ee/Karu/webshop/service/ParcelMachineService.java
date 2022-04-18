package ee.Karu.webshop.service;

import ee.Karu.webshop.model.input.OmnivaParcelMachine;
import ee.Karu.webshop.model.input.SmartPostParcelMachine;
import ee.Karu.webshop.model.output.ParcelMachines;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ParcelMachineService {
    String omnivaUrl = "https://www.omniva.ee/locations.json";
    String smartpostUrl = "https://www.smartpost.ee/places.json";

    @Autowired
    RestTemplate restTemplate;

    public ParcelMachines getParcelMachines(String country) {
        ParcelMachines parcelMachines = new ParcelMachines(); // parem klõps -> refactor -> rename
        parcelMachines.setOmnivaParcelMachines(fetchOmnivaParcelMachines(country));
        if (country.equals("EE")) {
            parcelMachines.setSmartpostParcelMachines(fetchSmartpostParcelMachines());
        } else {
            parcelMachines.setSmartpostParcelMachines(new ArrayList<>());
        }
        return parcelMachines;
    }

    public List<SmartPostParcelMachine> fetchSmartpostParcelMachines(){
        log.info("Taking Smart Post parcel machines");
        ResponseEntity<SmartPostParcelMachine[]> response = restTemplate
                .exchange(smartpostUrl, HttpMethod.GET, null,SmartPostParcelMachine[].class);

        List<SmartPostParcelMachine> smartPostParcelMachines = new ArrayList<>();
        if (response.getBody() != null) {
            smartPostParcelMachines = Arrays.asList(response.getBody());
            }
        return smartPostParcelMachines;
        }

    public List<OmnivaParcelMachine> fetchOmnivaParcelMachines(String country) {
        log.info("Taking Omniva parcel machines");
        ResponseEntity<OmnivaParcelMachine[]> response = restTemplate
                .exchange(omnivaUrl, HttpMethod.GET, null,OmnivaParcelMachine[].class);

        List<OmnivaParcelMachine> omnivaParcelMachines = new ArrayList<>();
        if (response.getBody() != null) {
            omnivaParcelMachines = Arrays.asList(response.getBody());
            omnivaParcelMachines = omnivaParcelMachines.stream()
                    .filter(p -> p.getA0_NAME().equals(country))
                    .collect(Collectors.toList());
        }
        return omnivaParcelMachines;
    }
}
