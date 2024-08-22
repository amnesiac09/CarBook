package aleksandrenaneishvili.carbook.controller;

import aleksandrenaneishvili.carbook.dto.AuthDto;
import aleksandrenaneishvili.carbook.dto.UserDto;
import aleksandrenaneishvili.carbook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("register")
  public ResponseEntity<?> registerUser(@RequestBody UserDto userDTO) {
    try {
      UserDto response = userService.registerUser(userDTO);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("login")
  public ResponseEntity<?> AuthenticateAndGetToken(@RequestBody UserDto userDTO){
    try {
      AuthDto response = userService.login(userDTO);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
