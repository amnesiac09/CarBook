package aleksandrenaneishvili.carbook.impl;

import aleksandrenaneishvili.carbook.entity.User;
import aleksandrenaneishvili.carbook.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if(user == null){
      log.error("Username not found: " + username);
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }
}
