package kyklos.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/kyklos")

public class KyklopController {


    private KyklopService kService;
    @Autowired
    public KyklopController(KyklopService kService) {this.kService = kService;}



        @GetMapping("/")
    public String test() {
            return "BEER IS THE TRUTH";

        }

    @PostMapping(value = "/")
        public ResponseEntity<Kyklop> postKyklop(@Valid @RequestBody Kyklop newKyklop){
            kService.postKyklop(newKyklop);
            return ResponseEntity.status(HttpStatus.CREATED).body(newKyklop);
    }
}
