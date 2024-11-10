package com.example.nymmpv2.repository;

import com.example.nymmpv2.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByGroupName(String groupName);
    Optional<Group> findByGroupHashCode(String groupHashCode);
}