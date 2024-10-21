package csw.t1.csw.controllers;

import csw.t1.csw.dto.user.RequestUserDTO;
import csw.t1.csw.dto.user.ResponseUserDTO;
import csw.t1.csw.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create-new-user")
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody @Valid RequestUserDTO dto) {
        return service.createUser(dto);
    }
}