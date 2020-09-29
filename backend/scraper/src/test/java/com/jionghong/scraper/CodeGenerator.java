package com.jionghong.scraper;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;


public class CodeGenerator {

    @Test
    public void genCode() {

        // 1、create a generator instance
        AutoGenerator mpg = new AutoGenerator();

        // 2、global config
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("jionghong");
        gc.setOpen(false); //open task manager
        gc.setFileOverride(false); //override generate file
        gc.setServiceName("%sService"); //Service Interface do not start with I
        mpg.setGlobalConfig(gc);

        // 3、datasource config
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/scrape?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("jionghong");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、package config
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.jionghong.scraper");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、strategy config
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// Naming strategy :database table map to entity
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// Naming strategy :database table fields map to entity
        strategy.setEntityLombokModel(true); // lombok @Accessors(chain = true) setter chaining operation
        strategy.setRestControllerStyle(true); //restful api style controller
        mpg.setStrategy(strategy);

        // 6、execute
        mpg.execute();
    }
}