package ee.Karu.webshop.api;

import ee.Karu.webshop.dao.PersonRepository;
import ee.Karu.webshop.model.database.Person;
import ee.Karu.webshop.model.input.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("signup")
    public ResponseEntity<Boolean> signup(@RequestBody Person person) {
//        person.setPassword(); hashing
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        personRepository.save(person);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("login")
    public ResponseEntity<Boolean> login(@RequestBody LoginData loginData) {
//        person.setPassword();

        return ResponseEntity.ok().body(true);
    }
}
