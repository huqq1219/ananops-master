package com.ananops.provider.main;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;

import java.util.List;

/**
 * 配置分页插件
 *
 * Created by bingyueduan on 2019/12/27.
 */
public class PaginationPlugin extends PluginAdapter {

    /**
     * 验证布尔值。
     *
     * @param warnings
     * @return
     */
    public boolean validate(List<String> warnings) {
        return true;
    }

    private static void generate() {
        String config = PaginationPlugin.class.getClassLoader().getResource("generator/generatorConfig-B.xml").getFile();
        String[] arg = {"-configfile", config, "-overwrite"};
        ShellRunner.main(arg);
    }

    public static void main(String[] args) {
        generate();
    }
}
