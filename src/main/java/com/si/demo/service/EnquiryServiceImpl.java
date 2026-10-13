package com.si.demo.service;

import com.si.demo.dto.DashboardEnquiryChatResponseDto;
import com.si.demo.dto.EnquiryFilterDto;
import com.si.demo.dto.EnquiryRequestDto;
import com.si.demo.dto.EnquiryResponseDto;
import com.si.demo.entity.Counsellor;
import com.si.demo.entity.Course;
import com.si.demo.entity.Enquiry;
import com.si.demo.repo.CounsellorRepo;
import com.si.demo.repo.CourseRepo;
import com.si.demo.repo.EnquiryRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Enquiry> findAllEnquiry() {
        return enquiryRepo.findAll();
    }

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
    public List<EnquiryResponseDto> searchEnquries(EnquiryFilterDto enquiryFilterDto) {
        return List.of();
    }
}
