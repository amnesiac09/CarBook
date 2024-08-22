package aleksandrenaneishvili.carbook.impl;

import aleksandrenaneishvili.carbook.dto.AuthDto;
import aleksandrenaneishvili.carbook.dto.UserDto;
import aleksandrenaneishvili.carbook.entity.User;
import aleksandrenaneishvili.carbook.repository.UserRepository;
import aleksandrenaneishvili.carbook.security.JwtService;
import aleksandrenaneishvili.carbook.service.UserService;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  @Override
  public UserDto registerUser(UserDto userDTO) throws RuntimeException{
    if(userRepository.existsByUsername(userDTO.getUsername())) {
      log.error("Username: " + userDTO.getUsername() + " already exists!");
      throw new RuntimeException("Username: " + userDTO.getUsername() + " already exists!");
    }
    try {
      User user = new User();
      user.setUsername(userDTO.getUsername());
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
      user = userRepository.save(user);
      return new UserDto(user.getId(), user.getUsername(), user.getPassword());
    } catch (Exception e) {
      throw new InternalException("Cannot register provided user");
    }
  }

  @Override
  public AuthDto login(UserDto userDTO) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
    if(authentication.isAuthenticated()){
      return AuthDto.builder()
          .accessToken(jwtService.GenerateToken(userDTO.getUsername())).build();
    } else {
      throw new UsernameNotFoundException("Invalid username or password");
    }
  }

  @Override
  public User getCurrentUser() {
    try {
      String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
      return userRepository.findByUsername(currentUserName);
    } catch (Exception e) {
      throw new InternalException("Cannot get user from session");
    }
  }
}
