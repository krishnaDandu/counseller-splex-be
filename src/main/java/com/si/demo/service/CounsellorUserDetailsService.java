package com.si.demo.service;

import com.si.demo.entity.Counsellor;
import com.si.demo.repo.CounsellorRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CounsellorUserDetailsService implements UserDetailsService {

    private CounsellorRepo counsellorRepo;
    public CounsellorUserDetailsService(CounsellorRepo counsellorRepo){
        this.counsellorRepo = counsellorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{


            Counsellor counsellor = counsellorRepo.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "Invalid Counsellor email Id"
                            ));

            return User.builder()
                    .username(counsellor.getEmail())
                    .password(counsellor.getPwd())
                    .roles("COUNSELLOR")
                    .build();
    }
}
