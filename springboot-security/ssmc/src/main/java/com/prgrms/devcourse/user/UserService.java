package com.prgrms.devcourse.user;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  public User login(String username, String credentials) {
    User user = userRepository.findByLoginId(username)
        .orElseThrow(() -> new UsernameNotFoundException("Could not found user for " + username));
    user.checkPassword(passwordEncoder, credentials);
    return user;
  }

  @Transactional(readOnly = true)
  public Optional<User> findByLoginId(String loginId) {
    checkArgument(isNotEmpty(loginId), "loginId must be provided.");
    return userRepository.findByLoginId(loginId);
  }


  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByLoginId(username)
        .map(user ->
            org.springframework.security.core.userdetails.User.builder()
                .username(user.getLoginId())
                .password(user.getPasswd())
                .authorities(user.getGroup().getAuthorities())
                .build()
        )
        .orElseThrow(() -> new UsernameNotFoundException("Could not found user for " + username));
  }
}
