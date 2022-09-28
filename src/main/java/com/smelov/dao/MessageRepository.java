package com.smelov.dao;

import com.smelov.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT message " +
            "FROM smelov.messages " +
            "JOIN smelov.users " +
            "ON smelov.messages.user_id = smelov.users.id " +
            "WHERE smelov.users.name = :username"
            , nativeQuery = true)
    List<String> findAllMessagesByUsername(@Param("username") String username);
}
