package com.mm.poc.multitenant.config;

import com.mm.poc.multitenant.datasource.MultiDBTenantDataSource;
import com.mm.poc.multitenant.datasource.TenantDataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(name="app.multitenantStrategy",havingValue = "DATABASE")
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
  private static final String DEFAULT_TENANT_ID = "DEFAULT";
  @Autowired
  private DataSource defaultDS;
  
  @Autowired
  private ApplicationContext context;
  
  private Map<String, DataSource> map = new HashMap<>();
  
  boolean init = false;
  
  @PostConstruct
  public void load() {
    map.put(DEFAULT_TENANT_ID, defaultDS);
  }
  
  @Override
  protected DataSource selectAnyDataSource() {
    return map.get(DEFAULT_TENANT_ID);
  }
  
  @Override
  protected DataSource selectDataSource(String tenantIdentifier) {
    if (!init) {
      init = true;
      TenantDataSource tenantDataSource = context.getBean(MultiDBTenantDataSource.class);
      map.putAll(tenantDataSource.getAll());
    }
    return map.get(tenantIdentifier) != null ? map.get(tenantIdentifier) : map.get(DEFAULT_TENANT_ID);
  }
}