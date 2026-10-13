package com.si.demo.service;

import com.si.demo.dto.LoginRequestDto;
import com.si.demo.dto.RegistrationRequestDto;
import com.si.demo.entity.Counsellor;

public interface CounsellorService {

    public boolean saveCounsellor(Counsellor counsellor);
    public String register(RegistrationRequestDto request);

    //public Counsellor login(String email, String pwd);

    //Counsellor login(LoginRequestDto loginRequestDto);

    //Counsellor login(String email, String pwd);

    Counsellor login(LoginRequestDto loginRequestDto);
}
