package com.crypto.infrastructure.adapters.outbound.repository;

import com.crypto.infrastructure.adapters.outbound.repository.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RateRepository extends JpaRepository<RateEntity, String> {

    @NonNull
    List<RateEntity> findAll();

    @Override
    @NonNull
    RateEntity save(@NonNull RateEntity rateEntity);
}
