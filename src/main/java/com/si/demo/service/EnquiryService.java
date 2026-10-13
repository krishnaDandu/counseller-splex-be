package com.si.demo.service;

import com.si.demo.dto.DashboardEnquiryChatResponseDto;
import com.si.demo.dto.EnquiryFilterDto;
import com.si.demo.dto.EnquiryRequestDto;
import com.si.demo.dto.EnquiryResponseDto;
import com.si.demo.entity.Counsellor;
import com.si.demo.entity.Enquiry;

import java.util.List;

public interface EnquiryService {

    Enquiry saveEnquiry(EnquiryRequestDto enquiryRequestDto);

    List<Enquiry> findAllEnquiry();

    DashboardEnquiryChatResponseDto getDashboardCounts();

    List<EnquiryResponseDto> searchEnquries(EnquiryFilterDto enquiryFilterDto);
}
