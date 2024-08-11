package com.example.ai.config;

import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ai.function.CurrectDateTimeFunction;
import com.example.ai.function.ProductFunction;

@Configuration
public class AiConfig {	
	@Bean
    public FunctionCallback productSalesInfo() {
        return FunctionCallbackWrapper.builder(new ProductFunction())
                .withName("ProductSalesInfo")
                .withDescription("Get the products sales volume at year")
                .withResponseConverter((response) -> response.products().toString())
                .build();
    }
	
	@Bean
    public FunctionCallback currectDateTime() {
        return FunctionCallbackWrapper.builder(new CurrectDateTimeFunction())
                .withName("CurrectDateTime")
                .withDescription("Get the Date Time")
                .withResponseConverter((response) -> response.currDateTime().toString())
                .build();
    }
}
