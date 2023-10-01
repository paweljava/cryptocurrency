package com.crypto.infrastructure.persistence.database.crypto.repository;

import com.crypto.infrastructure.persistence.database.crypto.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<RateEntity, String> {
}
