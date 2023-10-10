package com.crypto.infrastructure.adapters.outbound.repository;

import com.crypto.infrastructure.adapters.outbound.repository.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CryptoRepository extends JpaRepository<CryptoEntity, String> {

    @NonNull
    List<CryptoEntity> findAll();

    @NonNull
    CryptoEntity save(@NonNull CryptoEntity crypto);
}
