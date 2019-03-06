package com.example.springbootsintro.configuration;

import com.example.springbootsintro.utils.HtmlReader;
import com.example.springbootsintro.utils.HtmlReaderImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public HtmlReader htmlReader() {
        return new HtmlReaderImpl();
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
