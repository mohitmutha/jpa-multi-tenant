package com.mm.poc.multitenant.datasource;

import javax.sql.DataSource;
import java.util.Map;

public interface TenantDataSource {
  DataSource getDataSource(String name);
  
  Map<? extends String,? extends DataSource> getAll();
}
