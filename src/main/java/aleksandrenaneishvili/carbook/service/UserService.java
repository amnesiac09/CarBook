package aleksandrenaneishvili.carbook.service;

import aleksandrenaneishvili.carbook.dto.AuthDto;
import aleksandrenaneishvili.carbook.dto.UserDto;
import aleksandrenaneishvili.carbook.entity.User;

public interface UserService {
  UserDto registerUser(UserDto userDTO);
  AuthDto login(UserDto userDTO);

  User getCurrentUser();
}
