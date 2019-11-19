package cn.stylefeng.guns.generator.modular.service;

import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * 获取数据库所有的表
 *
 * @author fengshuonan
 * @date 2017-12-04-下午1:37
 */
@Service
public class TableService {
	private static final Logger LOG = LoggerFactory.getLogger(TableService.class);

    @Value("${spring.datasource.db-name:guns}")
    private String dbName;

    /**
     * 获取当前数据库所有的表信息
     */
    public List<Map<String, Object>> getAllTables() {
        String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'";
        LOG.info(sql);

        return SqlRunner.db().selectList(sql);
    }
    
    public List<Map<String, Object>> getTableFields(String tableName) {
        String sql = "select COLUMN_NAME as columnName,COLUMN_COMMENT as columnComment from information_schema.`COLUMNS` where table_name = '" + tableName + "'";
        LOG.info(sql);

        return SqlRunner.db().selectList(sql);
    }
}
