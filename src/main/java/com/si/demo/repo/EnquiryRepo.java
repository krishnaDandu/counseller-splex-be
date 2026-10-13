package com.si.demo.repo;

import com.si.demo.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>, JpaSpecificationExecutor<Enquiry> {

    long countByEnqStatus(String enqStatus);
}
