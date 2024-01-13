package com.whf.datasource.config;

import com.whf.datasource.entity.DataSourceEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDataSourceRunner implements CommandLineRunner {

    @Resource
    private DynamicDataSource dynamicDataSource;

    @Override
    public void run(String... args) throws Exception {
        //从数据库中查询所有数据源
        List<DataSourceEntity> dataSourceEntityList = new ArrayList<>();
        //创建数据源
        dynamicDataSource.createDataSource(dataSourceEntityList);
    }
}