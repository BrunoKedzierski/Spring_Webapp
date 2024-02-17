package com.example.masproj.Services;

import com.example.masproj.Repositories.DelegateRepository;
import com.example.masproj.Repositories.FighterRepository;
import com.example.masproj.config.SecurityUser;
import com.example.masproj.model.Delegate;
import com.example.masproj.model.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private FighterRepository fighterRepository;
    private DelegateRepository delegateService;

    @Autowired
    public UserDetailsService(FighterRepository fighterRepository, DelegateRepository delegateService) {
        this.fighterRepository = fighterRepository;
        this.delegateService = delegateService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Fighter> fighter =  fighterRepository.findByEmail(username);
        if (fighter.isPresent())
            return new SecurityUser(fighter.get());
        else{
            Optional<Delegate> delegate =  delegateService.findByEmail(username);
            if (delegate.isPresent())
                return new SecurityUser(delegate.get());
        }

        throw  new UsernameNotFoundException("Email not found!");
    }
}
