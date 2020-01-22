package com.samourai.javaserver.config.security;

import com.samourai.javaserver.persistence.repositories.ServerUserRepository;
import com.samourai.javaserver.persistence.to.ServerUserTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ServerUserDetailsService<U extends ServerUserTO, R extends ServerUserRepository<U>>
    implements UserDetailsService {
  protected R userRepository;

  public ServerUserDetailsService(R userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    U userTO =
        userRepository
            .findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("no such user"));
    List<GrantedAuthority> authorities =
        userTO
            .getPrivilegesList()
            .parallelStream()
            .map(privilege -> new SimpleGrantedAuthority(privilege))
            .collect(Collectors.toList());
    return new User(
        userTO.getLogin(), userTO.getPasswordHash(), true, true, true, true, authorities);
  }
}
