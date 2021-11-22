package br.com.leon.service;

import br.com.leon.model.Authentication;
import br.com.leon.repository.AuthenticationRepository;
import br.com.leon.repository.ManagerRepository;
import br.com.leon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Authentication authentication = authenticationRepository.findByEmail(email);

        if (authentication == null) {
            logger.error("Authentication user not found: " + email);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Authentication user found: " + email);

        return authentication;
    }
}
