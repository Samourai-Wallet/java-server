package com.samourai.javaserver.persistence.repositories;

import com.samourai.javaserver.persistence.to.ServerUserTO;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ServerUserRepository<U extends ServerUserTO> extends CrudRepository<U, Long> {

  Optional<U> findByLogin(String login);
}
