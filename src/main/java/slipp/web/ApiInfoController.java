package slipp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiInfoController {
    @GetMapping("/api/info")
    public Map<String, String> info(HttpServletRequest request) {
        Map<String, String> infos = new HashMap<>();
        infos.put("localAddr", request.getLocalAddr());
        infos.put("localName", request.getLocalName());
        return infos;
    }
}
