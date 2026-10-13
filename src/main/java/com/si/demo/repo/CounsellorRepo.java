package com.si.demo.repo;

import com.si.demo.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

    boolean existsByEmail(String email);

    Optional<Counsellor> findByEmail(String email);

    public Optional<Counsellor> findByEmailAndPwd(String email, String pwd);
}
