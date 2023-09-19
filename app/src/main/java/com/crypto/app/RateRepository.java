package com.crypto.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RateRepository extends JpaRepository<Rate, String> {

}
