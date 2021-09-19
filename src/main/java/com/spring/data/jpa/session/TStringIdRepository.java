package com.spring.data.jpa.session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TStringIdRepository extends JpaRepository<TTEntity, String> {

}
