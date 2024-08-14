//package com.example.validatedocsapi.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class configCORS implements WebMvcConfigurer {
//    //Cross origin resource
//    //cah 1
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**"); //all domain access
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedOrigins("localhost://3000")
//                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*");
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//
//            }
//        };
//    }
//}
