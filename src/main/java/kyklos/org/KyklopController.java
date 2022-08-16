package kyklos.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import javax.validation.Valid;


@RestController
@RequestMapping("/kyklos")

public class KyklopController {


    private KyklopService kService;

    @Autowired
    public KyklopController(KyklopService kService) {this.kService = kService;}


    // Get

    @GetMapping("/{kyklopId}")
    public ResponseEntity<Kyklop> getKyklop(@Valid @PathVariable("kyklopId") Integer kyklopId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body( kService.getKyklop(kyklopId));
    }

    // Put
    @PutMapping("/")
    public ResponseEntity<Kyklop> putKyklop(@Valid @RequestBody Kyklop kyklop){
        kService.putKyklop(kyklop);
        return ResponseEntity.status(HttpStatus.CREATED).body(kyklop);
    }

    // Post

    @PostMapping("/")
        public ResponseEntity<Kyklop> postKyklop(@Valid @RequestBody Kyklop newKyklop){
            kService.postKyklop(newKyklop);
            return ResponseEntity.status(HttpStatus.CREATED).body(newKyklop);
    }

    // Delete

    @DeleteMapping("/{kyklopId}")
    public ResponseEntity<String> deleteKyklop(@Valid @PathVariable("kyklopId") Integer kyklopId) throws InstanceNotFoundException {
        kService.deleteKyklop(kyklopId);
        return ResponseEntity.ok().body("User with ID "+ kyklopId +" deleted successfully");
    }
}
