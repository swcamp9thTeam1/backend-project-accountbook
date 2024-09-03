package com.iiiiii.accbookserver.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.iiiiii.accbookserver", annotationClass = Mapper.class)
public class MybatisConfiguration {
}