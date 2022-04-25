package ee.Karu.webshop.api;

import ee.Karu.webshop.dao.PersonRepository;
import ee.Karu.webshop.model.database.Person;
import ee.Karu.webshop.model.input.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("signup")
    public ResponseEntity<Boolean> signup(@RequestBody Person person) {
//        person.setPassword(); hashing
        personRepository.save(person);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("login")
    public ResponseEntity<Boolean> login(@RequestBody LoginData loginData) {
//        person.setPassword();

        return ResponseEntity.ok().body(true);
    }
}
