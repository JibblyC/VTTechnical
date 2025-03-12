package com.vt.demo.VTTechnical.dao;

import com.vt.demo.VTTechnical.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users u WHERE u.date_joined >= :startDate AND " +
            "u.user_id not in (select d.user_id from documents d where d.date_added > :startDate and d.date_added < :endDate)", nativeQuery = true)
    List<User> findUsersWithNoUploads(@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);

}
