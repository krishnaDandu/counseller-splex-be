package com.si.demo.service;

import com.si.demo.dto.LoginRequestDto;
import com.si.demo.dto.RegistrationRequestDto;
import com.si.demo.entity.Counsellor;
import com.si.demo.exception.CounsellorNotFoundException;
import com.si.demo.repo.CounsellorRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    private CounsellorRepo counsellorRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public CounsellorServiceImpl(
            CounsellorRepo counsellorRepo,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.counsellorRepo = counsellorRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean saveCounsellor(Counsellor counsellor) {
        return false;
    }

    @Override
    public String register(RegistrationRequestDto requestDto) {

        // 1. Check email already exists
        if(counsellorRepo.existsByEmail(requestDto.getEmail())){
            return "Email already registered";
        }

        // 2. Create Entity
        Counsellor counsellor = new Counsellor();
        counsellor.setName(requestDto.getName());
        counsellor.setEmail(requestDto.getEmail());
        counsellor.setPhno(requestDto.getPhno());
        counsellor.setPwd(passwordEncoder.encode(requestDto.getPwd()));
        //counsellor.setPwd(requestDto.getPwd());
/*
////
////        // 3. Encrypt password
////        counsellor.setPwd(
////                passwordEncoder.encode(request.getPwd())
////        );
*/

        // 4. Save to MySQL
        counsellorRepo.save(counsellor);

        return "Registration successful";
    }

//    @Override
//    public Counsellor login(String email, String pwd) {
//        Optional<Counsellor> opt = counsellorRepo.findByEmailAndPwd(email, pwd);
//        Counsellor Counsellor = null;
//        return opt.orElse(null);
//    }


    @Override
    public Counsellor login(LoginRequestDto request) {

        String email = request.getEmail().trim();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        request.getPwd()
                )
        );

        Counsellor counsellor = counsellorRepo.findByEmail(email)
                .orElseThrow(() ->
                        new CounsellorNotFoundException("Invalid Counsellor email Id"));
        return counsellor;
    }

// Without AUTH Implementaion for login=========
    //@Override
//    public Counsellor login(LoginRequestDto request) {
//
//        String email = request.getEmail().trim();
//
//
//        Counsellor counsellor = counsellorRepo.findByEmail(email)
//                .orElseThrow(() ->
//                    new CounsellorNotFoundException("Invalid Counsellor email Id"));
//
//        if (!counsellor.getPwd().equals(request.getPwd())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return counsellor;
//    }


//    @Override
//    public Counsellor login(LoginRequestDto request) {
//
//        String email = request.getEmail().trim();
//
//        System.out.println("Login Email: [" + email + "]");
//
//        Optional<Counsellor> result = counsellorRepo.findByEmail(email);
//
//        if (result.isEmpty()) {
//            throw new RuntimeException("Counsellor not found for email: " + email);
//        }
//
//        Counsellor counsellor = result.get();
//
//        if (!counsellor.getPwd().equals(request.getPwd())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return counsellor;
//    }


}
