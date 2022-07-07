package com.net.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置
 */
@Configuration
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
       
        //获取application.properties中的上传路径
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        try {
        props.load(inputStream);
        } catch (IOException e) {
        e.printStackTrace();
        }
        String fileUrl = props.getProperty("app.upload.dir");
        if (fileUrl.startsWith("~")) {
        fileUrl = fileUrl.replaceFirst("~", System.getProperty("java.io.tmpdir"));
        }
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + fileUrl + "/");
        }
        }
