package com.si.demo.specification;

import com.si.demo.entity.Enquiry;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EnquirySpecification {

    public static Specification<Enquiry> filter(
            String stuName, String stuPhno, List<Integer> courseIds ){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Student name filter
            if(stuName != null && !stuName.trim().isEmpty()){
                predicates.add(
                        (Predicate) criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("stuName")
                                ),
                                "%" + stuName.trim().toLowerCase() + "%"
                        )
                );
            }

            // Student phone filter
            if (stuPhno != null && !stuPhno.trim().isEmpty()) {

                predicates.add(
                        (Predicate) criteriaBuilder.like(
                                root.get("stuPhno"),
                                "%" + stuPhno.trim() + "%"
                        )
                );
            }

            // Multiple course filter
            if (courseIds != null && !courseIds.isEmpty()) {

                predicates.add(
                        (Predicate) root.get("course")
                                .get("courseId")
                                .in(courseIds)
                );
            }

            return criteriaBuilder.and( predicates.toArray(new jakarta.persistence.criteria.Predicate[0]) );
           //return criteriaBuilder.conjunction();
        };
    }
}
