package cn.stylefeng.guns.generator.executor.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 默认的代码生成的配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:27
 */
public class GunsGeneratorConfig extends AbstractGeneratorConfig {

    protected void globalConfig() {
        //globalConfig.setOutputDir("../../src/main/java");//写自己项目的绝对路径,注意具体到java目录
        globalConfig.setOutputDir("./generate/src/main/java");//写自己项目的绝对路径,注意具体到java目录
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor("felix");
    }

    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/guns?characterEncoding=utf8");
    }

    protected void packageConfig() {
        packageConfig.setParent(null);
        packageConfig.setEntity("cn.stylefeng.guns.modular.cms.entity");
        packageConfig.setMapper("cn.stylefeng.guns.modular.cms.mapper");
        packageConfig.setXml("cn.stylefeng.guns.modular.cms.mapper.mapping");
    }
    
    protected void strategyConfig() {
        strategyConfig.setTablePrefix(new String[]{""});// 此处可以修改为您的表前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude("cms_album","cms_banner");
    }

    protected void contextConfig() {
        contextConfig.setProPackage("cn.stylefeng.guns");
        contextConfig.setCoreBasePackage("cn.stylefeng.guns.core");
        contextConfig.setBizChName("专辑管理");
        contextConfig.setBizEnName("CmsAlbum");
        contextConfig.setModuleName("cms");
        //contextConfig.setProjectPath("../../");//写自己项目的绝对路径
        contextConfig.setProjectPath("./generate");//写自己项目的绝对路径
        contextConfig.setEntityName("CmsAlbum");
        sqlConfig.setParentMenuName("内容管理");//这里写已有菜单的名称,当做父节点

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(true);
        contextConfig.setDaoSwitch(true);
        contextConfig.setServiceSwitch(true);

        /**
         * guns 生成器开关
         */
        contextConfig.setControllerSwitch(true);
        contextConfig.setIndexPageSwitch(true);
        contextConfig.setAddPageSwitch(true);
        contextConfig.setEditPageSwitch(true);
        contextConfig.setJsSwitch(true);
        contextConfig.setInfoJsSwitch(true);
        contextConfig.setSqlSwitch(true);
    }

    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
        contextConfig();
    }
}
