package com.wxy8866.demo.marketing163.db;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by wxy8866 on 2017/3/13.
 */
public class MyBatisGeneratorMain
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Properties prop = new Properties();
        prop.setProperty("generated.source.dir", System.getProperty("user.dir"));

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        File configFile = new File(MyBatisGeneratorMain.class.getResource("/com/wxy8866/demo/marketing163/mybatis/mybatis-generator.xml").toURI());
        ConfigurationParser cp = new ConfigurationParser(prop, warnings);
        Configuration config = cp.parseConfiguration(configFile);
        config.validate();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        ProgressCallback progress = new VerboseProgressCallback();
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(progress);
        System.out.println("Completed");

    }
}
