package kyklos.org.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Alle Einträge ausgeben
    @GetMapping("/")

    @Operation(summary = "Ausgeben aller User")
    public ResponseEntity<List<User>> findAll(@Valid @RequestBody User user){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Eintrag ausgeben nach ID
    @GetMapping("/{userId}")

    @Operation(summary = "Ausgeben eines Users nach ID")
    public ResponseEntity<User> findById(@Valid @PathVariable("userId") Integer userId)throws InstanceNotFoundException {

        return ResponseEntity.ok().body(userService.findById(userId));
    }

    // Eintrag erstellen
    @PostMapping(value = "/")

    @Operation(summary = "Erstellen eines neuen Users")

    public ResponseEntity<User> postUser(@Valid @RequestBody User newUser)throws InstanceAlreadyExistsException {
        userService.postUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Einträge Aktuallisieren
    @PutMapping("/")

    @Operation(summary = "Aktuallisieren eines Users")
    public ResponseEntity<User> putUser(@Valid @RequestBody User updateUser){

        userService.putUser(updateUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
    }

    // Entrag löschen
    @DeleteMapping("/{userId}")

    @Operation(summary = "Löschen eines Users")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable("userId") Integer userId )throws InstanceNotFoundException {

        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User with ID "+ userId +" deleted successfully");
    }
    //Exception Handler
    @ExceptionHandler
    public ResponseEntity<String> exeptionHandler(Exception request){
        return ResponseEntity.status(400).body(request.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> exeptionHandler(InstanceNotFoundException request){
        return ResponseEntity.status(404).body(request.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler(InstanceAlreadyExistsException request){
        return ResponseEntity.status(406).body(request.getMessage());
    }

}
