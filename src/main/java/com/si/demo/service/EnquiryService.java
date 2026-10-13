package com.si.demo.service;

import com.si.demo.dto.*;
import com.si.demo.entity.Enquiry;

import java.util.List;
import java.util.Map;

public interface EnquiryService {

    Enquiry saveEnquiry(EnquiryRequestDto enquiryRequestDto);

    List<Enquiry> findAllEnquiry();

    //List<Enquiry> findByCounsellorId(Integer counsellorId);
    ApiResponseDto<List<EnquiryResponseDto>> findByCounsellorId(Integer counsellorId);

    DashboardEnquiryChatResponseDto getDashboardCounts();

    List<EnquiryResponseDto> searchEnquries(EnquiryFilterDto enquiryFilterDto);
}
