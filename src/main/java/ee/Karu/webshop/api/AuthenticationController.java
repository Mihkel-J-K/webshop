package ee.Karu.webshop.api;

import ee.Karu.webshop.authentication.TokenGenerator;
import ee.Karu.webshop.dao.PersonRepository;
import ee.Karu.webshop.model.database.Person;
import ee.Karu.webshop.model.request.input.LoginData;
import ee.Karu.webshop.model.request.output.AuthData;
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

    @Autowired
    TokenGenerator tokenGenerator;

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
    public ResponseEntity<AuthData> login(@RequestBody LoginData loginData) {
//        person.setPassword();
        Person person = personRepository.getByEmail(loginData.getEmail());
        boolean matches = passwordEncoder.matches(loginData.getPassword(), person.getPassword());
        if (matches) {
            AuthData authData = tokenGenerator.createAuthToken(loginData.getEmail());
            return ResponseEntity.ok().body(authData);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
