package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

	Optional<AdminUser> findByLoginId(String id);
	
}
