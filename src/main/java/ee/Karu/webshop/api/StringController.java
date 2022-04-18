package ee.Karu.webshop.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // annotation --- import
public class StringController {

    @GetMapping("hello")
    public String getString() {
        return "Hello World!";
    }

    // pathvariable
    @GetMapping("string/{returnString}")
    public String returnString(@PathVariable String returnString) {
        return returnString;
    }

    @GetMapping("add/{nr1}/{nr2}") // localhost:8080/api/add/nr1/nr2
    public int add(@PathVariable int nr1, @PathVariable int nr2) {
        return nr1 + nr2;
    }


}
