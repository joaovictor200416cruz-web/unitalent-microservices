package br.com.unitalent.gateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
public class RootController {
    @GetMapping("/")
    public Map<String,Object> root() {
        return Map.of("sistema","UniTalent","status","online","gateway","8080");
    }
}
