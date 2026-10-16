package com.si.demo.controller;

import com.si.demo.dto.*;
import com.si.demo.entity.Enquiry;
import com.si.demo.repo.EnquiryRepo;
import com.si.demo.service.EnquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    //On Edit of the Enquiry update
    @PutMapping("/updateenquiry/{enqId}")
    public ResponseEntity<ApiResponseDto<EnquiryRequestDto>> updateEnquiry(
            @PathVariable Integer enqId, @RequestBody EnquiryRequestDto requestDto){

        Enquiry updatedEnquiry = enquiryService.upDateEnquiry(enqId, requestDto);

        EnquiryRequestDto response = new EnquiryRequestDto();
        response.setEnqId(updatedEnquiry.getEnqId());
        response.setStuName(updatedEnquiry.getStuName());
        response.setStuPhno(updatedEnquiry.getStuPhno());
        response.setClassMode(updatedEnquiry.getClassMode());
        response.setEnqStatus(updatedEnquiry.getEnqStatus());
        response.setCreatedAt(updatedEnquiry.getCreatedAt());
        response.setUpdatedAt(updatedEnquiry.getUpdatedAt());

        if (updatedEnquiry.getCourse() != null) {
            response.setCourseId(updatedEnquiry.getCourse().getCourseId());
            //response.setCourseName(updatedEnquiry.getCourse().getCourseName());
        }

        ApiResponseDto<EnquiryRequestDto> apiResponse = new ApiResponseDto<>();

        apiResponse.setMessage("Enquiry updated successfully");
        apiResponse.setData(response);


        return ResponseEntity.ok(apiResponse);
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

    // Get all Enquiries based on the Counsellor Login ID - counsellorId
    @GetMapping("/getAllEnquiries")
    public ResponseEntity<ApiResponseDto<List<EnquiryResponseDto>>> getAllEnquries(@RequestParam Integer counsellorId){
        ApiResponseDto<List<EnquiryResponseDto>> response = enquiryService.findByCounsellorId(counsellorId);
        System.out.println("getAllEnquries :" + response);
        return ResponseEntity.ok(response);
    }

    //Get all Enquiries Search based on the StudentName, StudentPhone and Course
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getEnquriesBySearch(@ModelAttribute EnquiryFilterDto filterDto){

        List<EnquiryResponseDto> data = enquiryService.searchEnquries(filterDto );
        Map<String, Object> response = new HashMap<>();
        response.put(
                "message",
                data.isEmpty() ? "No enquiries found" : "Data found"
        );
        response.put("data", data);
        return ResponseEntity.ok(response);
    }

    // Get all the Dashboard Enquires count
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
