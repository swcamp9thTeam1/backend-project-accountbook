package com.iiiiii.accountbook.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.iiiiii.accountbook", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
