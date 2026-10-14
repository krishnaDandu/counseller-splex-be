package com.si.demo.specification;

import com.si.demo.entity.Enquiry;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EnquirySpecification {

    public static Specification<Enquiry> fiter(
            String stuName,
            String stuPhno,
            Integer courseId) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (stuName != null && !stuName.trim().isEmpty()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("stuName")),
                                "%" + stuName.trim().toLowerCase() + "%"
                        )
                );
            }

            if (stuPhno != null && !stuPhno.trim().isEmpty()) {
                predicates.add(
                        cb.like(
                                root.get("stuPhno"),
                                "%" + stuPhno.trim() + "%"
                        )
                );
            }

            if (courseId != null) {
                predicates.add(
                        cb.equal(
                                root.get("course").get("courseId"),
                                courseId
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
