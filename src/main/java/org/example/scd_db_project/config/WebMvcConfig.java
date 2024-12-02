package org.example.scd_db_project.config;

import org.example.scd_db_project.converter.StringToRestaurantMenuIdConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private final StringToRestaurantMenuIdConverter stringToRestaurantMenuIdConverter;

    public WebMvcConfig(StringToRestaurantMenuIdConverter stringToRestaurantMenuIdConverter) {
        this.stringToRestaurantMenuIdConverter = stringToRestaurantMenuIdConverter;
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToRestaurantMenuIdConverter);
    }
}
