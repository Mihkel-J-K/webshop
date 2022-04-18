package ee.Karu.webshop.api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ListController {

    public List<String> strings = new ArrayList<>(Arrays.asList("Yks", "Kaks", "Kolm"));

    @GetMapping("get-strings") //localhost:8080/get-strings
    public List<String> getStrings() {
        return strings;
    }

    @PostMapping("add-string/{newString}") // localhost:8080/add-string/{newString}
    public void addString(@PathVariable String newString) {
        strings.add(newString);
    }

    @DeleteMapping("delete-string/{index}") // localhost:8080/delete-string/{index}
    public void deleteString(@PathVariable int index){
        strings.remove(index);
    }
}
