package com.whf.datasource.utils;

/**
 * 数据源配置
 * @author whf
 * @date 2023/12/12
 */
public class DataSourceContextHolder {

    public static final ThreadLocal<String> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    /**
     * 设置当前线程数据源
     * @param dataSource 数据源名称
     */
    public static void setDataSource(String dataSource) {
        DATA_SOURCE_HOLDER.set(dataSource);
    }

    /**
     * 获取当前线程数据源
     * @return 数据源名称
     */
    public static String getDataSource() {
        return DATA_SOURCE_HOLDER.get();
    }

    /**
     * 删除当前线程数据源
     */
    public static void removeDataSource() {
        DATA_SOURCE_HOLDER.remove();
    }
}
