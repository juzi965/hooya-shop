package top.hooya.shop.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.hooya.shop.config.DateConverter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SqlInjectInterceptor()).addPathPatterns("/**");

		WebMvcConfigurer.super.addInterceptors(registry);
	}


	/**
	 * 配置全局表单日期转换器
	 */
	@Bean
	@Autowired
	public ConversionService getConversionService(DateConverter dateConverter) {
		ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(dateConverter);
		factoryBean.setConverters(converters);

		return factoryBean.getObject();
	}

}
