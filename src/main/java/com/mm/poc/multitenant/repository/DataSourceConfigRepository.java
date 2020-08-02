package com.mm.poc.multitenant.repository;

import com.mm.poc.multitenant.entity.DataSourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {
  DataSourceConfig findByName(String name);
}