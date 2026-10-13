package com.si.demo.controller;

import com.si.demo.dto.ApiResponseDto;
import com.si.demo.dto.DashboardEnquiryChatResponseDto;
import com.si.demo.dto.EnquiryRequestDto;
import com.si.demo.dto.EnquiryResponseDto;
import com.si.demo.entity.Enquiry;
import com.si.demo.service.EnquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;
    public EnquiryController(EnquiryService enquiryService){
        this.enquiryService = enquiryService;
    }

    @PostMapping("/save")
    public ResponseEntity<EnquiryResponseDto> saveEnquiry(@RequestBody EnquiryRequestDto request){

        Enquiry savedEnquiry = enquiryService.saveEnquiry(request);

        EnquiryResponseDto response = new EnquiryResponseDto();

        response.setEnqId(savedEnquiry.getEnqId());
        response.setStuName(savedEnquiry.getStuName());
        response.setStuPhno(savedEnquiry.getStuPhno());
        response.setClassMode(savedEnquiry.getClassMode());
        response.setEnqStatus(savedEnquiry.getEnqStatus());
        response.setCourseId(savedEnquiry.getCourse().getCourseId());
        response.setCourseName(savedEnquiry.getCourse().getCourseName());
        response.setCreatedAt(savedEnquiry.getCreatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PostMapping("/save")
//    public ResponseEntity<EnquiryResponseDto> saveEnquiry(@RequestBody EnquiryRequestDto request){
//
//        Enquiry saveEnquiry = enquiryService.saveEnquiry(request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(saveEnquiry);
//    }

//    @GetMapping("/getAllEnquiries")
//    public ResponseEntity<List<Enquiry>> getAllEnquries(){
//        List<Enquiry> resEnquiry = enquiryService.findAllEnquiry();
//        return ResponseEntity.ok(resEnquiry);
//    }

//    @GetMapping("/getAllEnquiries")
//    public ResponseEntity<List<Enquiry>> getAllEnquries(@RequestParam Integer counsellorId){
//        List<Enquiry> resEnquiry = enquiryService.findByCounsellorId(counsellorId);
//        System.out.println("getAllEnquries :" + resEnquiry);
//        return ResponseEntity.ok(resEnquiry);
//    }

    @GetMapping("/getAllEnquiries")
    public ResponseEntity<ApiResponseDto<List<EnquiryResponseDto>>> getAllEnquries(@RequestParam Integer counsellorId){
        ApiResponseDto<List<EnquiryResponseDto>> response = enquiryService.findByCounsellorId(counsellorId);
        System.out.println("getAllEnquries :" + response);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/enquiries/search")
//    public ResponseEntity

    @GetMapping("/dashboardChart")
    public ResponseEntity<DashboardEnquiryChatResponseDto> getDashboard(){
        DashboardEnquiryChatResponseDto response = enquiryService.getDashboardCounts();
        System.out.println("getDashboard : " + response );
        return ResponseEntity.ok(response);
    }


}

//    @PostMapping
//    public ResponseEntity<Enquiry> saveEnquiry(@RequestBody EnquiryRequestDto request, @RequestParam Integer counsellorId){
//
//        Enquiry saveEnquiry = enquiryService.saveEnquiry(request, counsellorId);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(saveEnquiry);
//    }
//}
