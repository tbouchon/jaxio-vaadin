/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-vaadin:src/main/java/config/JacksonConfiguration.java.p.vm
 */
package com.mycompany.myapp.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class JacksonConfiguration {

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        JavaTimeModule module = new JavaTimeModule();

        module.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
        module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());

        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());

        return new Jackson2ObjectMapperBuilder().featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).findModulesViaServiceLoader(true)
                .modulesToInstall(module);
    }
}