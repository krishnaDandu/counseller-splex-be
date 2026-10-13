package com.si.demo.repo;

import com.si.demo.entity.Counsellor;
import com.si.demo.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>, JpaSpecificationExecutor<Enquiry> {

    long countByEnqStatus(String enqStatus);

    List<Enquiry> findByCounsellor_CounsellorId(Integer counsellorId);
}
