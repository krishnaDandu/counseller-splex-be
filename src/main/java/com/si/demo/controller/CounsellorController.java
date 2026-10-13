package com.si.demo.controller;

import com.si.demo.dto.CounsellorResponseDto;
import com.si.demo.dto.LoginRequestDto;
import com.si.demo.dto.RegistrationRequestDto;
import com.si.demo.entity.Counsellor;
import com.si.demo.entity.Enquiry;
import com.si.demo.service.CounsellorService;
import com.si.demo.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counsellors")
//@CrossOrigin(origins = "http://localhost:4200")
public class CounsellorController {

    private final CounsellorService counsellorService;
    private final JwtService jwtService;

    public CounsellorController(CounsellorService counsellorService, JwtService jwtService) {

        this.counsellorService = counsellorService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegistration(@RequestBody RegistrationRequestDto requestDto){

        String result = counsellorService.register(requestDto);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/login")
    public ResponseEntity<CounsellorResponseDto> handleLogin(
            @RequestBody LoginRequestDto loginRequestDto ) {

        Counsellor counsellor = counsellorService.login(loginRequestDto);

        //Token Based
        String token = jwtService.generateToken(counsellor);

        CounsellorResponseDto response = new CounsellorResponseDto();

        response.setCounsellorId(counsellor.getCounsellorId());
        response.setName(counsellor.getName());
        response.setEmail(counsellor.getEmail());
        response.setPhno(counsellor.getPhno());

        // IMPORTANT: put JWT into response
        response.setToken(token);

        return  ResponseEntity.ok(response);

    }
}
