package com.crypto.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoSymbolRepository extends JpaRepository<CryptoSymbol, String> {
}
