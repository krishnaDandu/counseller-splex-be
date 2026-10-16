package com.si.demo.service;

import com.si.demo.dto.*;
import com.si.demo.entity.Counsellor;
import com.si.demo.entity.Course;
import com.si.demo.entity.Enquiry;
import com.si.demo.repo.CounsellorRepo;
import com.si.demo.repo.CourseRepo;
import com.si.demo.repo.EnquiryRepo;
import com.si.demo.specification.EnquirySpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private static EnquiryRepo enquiryRepo;
    private static CourseRepo courseRepo;
    private static CounsellorRepo counsellorRepo;

    public EnquiryServiceImpl(EnquiryRepo enquiryRepo, CourseRepo courseRepo, CounsellorRepo counsellorRepo){
        this.enquiryRepo = enquiryRepo;
        this.courseRepo = courseRepo;
        this.counsellorRepo = counsellorRepo;
    }

    @Override
    @Transactional
    public Enquiry saveEnquiry(EnquiryRequestDto requestDto) {

        // Fetch Course from DB
        Course course = courseRepo.findById(requestDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        // Fetch Counsellor from DB
        Counsellor counsellor = counsellorRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Counsellor not found"));

        // Create Enquiry entity
        Enquiry enquiry = new Enquiry();

        enquiry.setStuName(requestDto.getStuName());
        enquiry.setStuPhno(requestDto.getStuPhno());
        enquiry.setClassMode(requestDto.getClassMode());
        enquiry.setEnqStatus(requestDto.getEnqStatus());
        enquiry.setCourse(course);
        enquiry.setCounsellor(counsellor);

        return enquiryRepo.save(enquiry);
    }

    @Override
    @Transactional
    public Enquiry upDateEnquiry(Integer enquiryId , EnquiryRequestDto requestDto) {

        // Fetch the existing enquiry
        Enquiry enquiry = enquiryRepo.findById(enquiryId)
                .orElseThrow(()-> new RuntimeException("Enquiry not found"));

        Course course = courseRepo.findById(requestDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));


        // Verify that this enquiry belongs to the logged-in counsellor
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        if(!enquiry.getCounsellor().getEmail().equals(email)){
            throw new org.springframework.security
                    .access.AccessDeniedException("You are not allowed to update this enquiry");
        }


        //Update ONLY the permitted fields
        //Enquiry updateEnquiry = new Enquiry();
        enquiry.setStuPhno(requestDto.getStuPhno());
        enquiry.setClassMode(requestDto.getClassMode());
        enquiry.setCourse(course);
        //updateEnquiry.setStuName(requestDto.getStuName());
        //updateEnquiry.setEnqStatus(requestDto.getEnqStatus());
        //updateEnquiry.setCounsellor(counsellor);

        // Save the existing enquiry
        return enquiryRepo.save(enquiry);
    }

    @Override
    public List<Enquiry> findAllEnquiry() {
        return enquiryRepo.findAll();
    }

    @Override
    public ApiResponseDto<List<EnquiryResponseDto>> findByCounsellorId(Integer counsellorId) {
        List<Enquiry> enquiries =
                enquiryRepo.findByCounsellor_CounsellorId(counsellorId);

        List<EnquiryResponseDto> data = enquiries.stream()
                .map(enquiry -> {
                    EnquiryResponseDto dto = new EnquiryResponseDto();

                    dto.setEnqId(enquiry.getEnqId());
                    dto.setStuName(enquiry.getStuName());
                    dto.setStuPhno(enquiry.getStuPhno());
                    dto.setClassMode(enquiry.getClassMode());
                    dto.setEnqStatus(enquiry.getEnqStatus());

                    if (enquiry.getCourse() != null) {
                        dto.setCourseId(enquiry.getCourse().getCourseId());
                        dto.setCourseName(enquiry.getCourse().getCourseName());
                    }

                    dto.setCreatedAt(enquiry.getCreatedAt());
                    dto.setUpdatedAt(enquiry.getUpdatedAt());

                    return dto;
                })
                .toList();

        String message = data.isEmpty()
                ? "No enquiries found"
                : "Data found";

        return new ApiResponseDto<>(message, data);
    }


    //    @Override
//    public Map<String, Object> findByCounsellorId(Integer counsellorId) {
//
//        List<Enquiry> enquiries  = enquiryRepo.findByCounsellor_CounsellorId(counsellorId);
//
//        Map<String, Object> response = new HashMap<>();
//
//        if(enquiries.isEmpty()){
//            response.put("message", "No enquiries found");
//            response.put("data", response);
//        }else {
//            response.put("message", "Data found");
//            response.put("data", response);
//        }
////        if(enquiryList.isEmpty()){
////            throw new RuntimeException("No enquiries found for counsellorId: " + counsellorId);
////        }
//        return response;
//    }

    @Override
    public DashboardEnquiryChatResponseDto getDashboardCounts() {
        long count = enquiryRepo.count();
        long newEnquiries = enquiryRepo.countByEnqStatus("NEW");
        long enrolled = enquiryRepo.countByEnqStatus("ENROLLED");
        long lost = enquiryRepo.countByEnqStatus("LOST");

        return new DashboardEnquiryChatResponseDto(
                count,
                newEnquiries,
                enrolled,
                lost
        );

    }

    @Override
    public List<EnquiryResponseDto> searchEnquries(EnquiryFilterDto filterDto) {

        Specification<Enquiry> specification = EnquirySpecification.fiter(
                filterDto.getStuName(),
                filterDto.getStuPhno(),
                filterDto.getCourseId()
        );

        List<Enquiry> enquiries = enquiryRepo.findAll(specification);

        return enquiries.stream()
                .map(enquiry -> {
                    EnquiryResponseDto dto = new EnquiryResponseDto();

                    dto.setEnqId(enquiry.getEnqId());
                    dto.setStuName(enquiry.getStuName());
                    dto.setStuPhno(enquiry.getStuPhno());
                    dto.setClassMode(enquiry.getClassMode());
                    dto.setEnqStatus(enquiry.getEnqStatus());
                    dto.setCreatedAt(enquiry.getCreatedAt());

                    if (enquiry.getCourse() != null) {
                        dto.setCourseId(enquiry.getCourse().getCourseId());
                        dto.setCourseName(enquiry.getCourse().getCourseName());
                    }

                    return dto;
                }).toList();



    }
}
