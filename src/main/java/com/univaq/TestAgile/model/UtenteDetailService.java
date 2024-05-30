package com.univaq.TestAgile.model;

import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtenteDetailService implements UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;
    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmail(email);
        if(utente != null){
            return User.builder()
                    .username(utente.getEmail())
                    .password(utente.getPassword())
                    .roles(getRoles(utente))
                    .build();
        }else {
            throw new UsernameNotFoundException(email);
        }

    }

    private String[] getRoles(Utente user) {
        if (user.getTipoUtente() == null) {
            return new String[]{"USER"};
        }
        return user.getTipoUtente().split(",");
    }
}
