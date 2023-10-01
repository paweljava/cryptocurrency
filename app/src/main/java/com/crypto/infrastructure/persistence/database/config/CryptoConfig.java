package com.crypto.infrastructure.persistence.database.config;

import com.crypto.domain.crypto.adapter.CryptoServiceAdapter;
import com.crypto.domain.crypto.port.inbound.CryptoServicePort;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.service.RateService;
import com.crypto.infrastructure.persistence.database.crypto.RateDatabaseAdapter;
import com.crypto.infrastructure.persistence.database.crypto.repository.CryptoRepository;
import com.crypto.web.client.BianceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoConfig {

    @Bean
    public CryptoServicePort cryptoServicePort(CryptoRepositoryPort cryptoRepositoryPort) {
        return new CryptoServiceAdapter(new RateService(cryptoRepositoryPort, bianceClient() ));
    }

    @Bean
    public CryptoRepositoryPort cryptoRepositoryPort(CryptoRepository cryptoRepository) {
        return new RateDatabaseAdapter(cryptoRepository);
    }

    /*@Bean
    public CryptoRepositoryPort cryptoRepositoryPort() {
        return new CryptoInMemoryAdapter();
    }*/

    @Bean
    public RateService cryptoService(CryptoRepository cryptoRepository) {
        return new RateService(cryptoRepositoryPort(cryptoRepository), bianceClient());
    }

    @Bean
    BianceClient bianceClient() {
        return new BianceClient();
    }





   /* @Bean
    public CryptoRepository cryptoRepository() {
        return new CryptoRepository() {
            @Override
            public void flush() {

            }

            @Override
            public <S extends RateEntity> S saveAndFlush(S entity) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                throw new ImplementMePleaseException();            }

            @Override
            public void deleteAllInBatch(Iterable<RateEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public RateEntity getOne(String s) {
                return null;
            }

            @Override
            public RateEntity getById(String s) {
                return null;
            }

            @Override
            public RateEntity getReferenceById(String s) {
                return null;
            }

            @Override
            public <S extends RateEntity> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends RateEntity> List<S> findAll(Example<S> example, Sort sort) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> List<S> saveAll(Iterable<S> entities) {
                throw new ImplementMePleaseException();
            }

            @Override
            public List<RateEntity> findAll() {
                //throw new ImplementMePleaseException();
                return (List<RateEntity>) cryptoRepositoryPort((CryptoRepository) cryptoRepository().findAll());
            }

            @Override
            public List<RateEntity> findAllById(Iterable<String> strings) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> S save(S entity) {
                throw new ImplementMePleaseException();
            }

            @Override
            public Optional<RateEntity> findById(String s) {
                throw new ImplementMePleaseException();
            }

            @Override
            public boolean existsById(String s) {
                throw new ImplementMePleaseException();
            }

            @Override
            public long count() {
                throw new ImplementMePleaseException();
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(RateEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends RateEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<RateEntity> findAll(Sort sort) {
                throw new ImplementMePleaseException();
            }

            @Override
            public Page<RateEntity> findAll(Pageable pageable) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> Optional<S> findOne(Example<S> example) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> long count(Example<S> example) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity> boolean exists(Example<S> example) {
                throw new ImplementMePleaseException();
            }

            @Override
            public <S extends RateEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                throw new ImplementMePleaseException();
            }
        };*/
    }

