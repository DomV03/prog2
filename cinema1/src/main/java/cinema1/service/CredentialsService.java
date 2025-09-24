package cinema1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema1.model.Credentials;
import cinema1.repository.CredentialsRepository;

@Service
public class CredentialsService implements UserDetailsService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Tentativo di login per l'utente: " + username);
        Optional<Credentials> credentials = this.credentialsRepository.findByUsername(username);

        if (credentials.isEmpty()) {
            System.out.println("Utente non trovato: " + username);
            throw new UsernameNotFoundException("Credentials with username: " + username + " not found");
        }

        System.out.println("Utente trovato, nome utente: " + credentials.get().getUsername());
        return (UserDetails) credentials.get();
    }

    @Transactional
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        //credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

    public Optional<Credentials> findByUsername(String username) {
        return credentialsRepository.findByUsername(username);
    }
}