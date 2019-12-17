package top.hooya.shop.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.hooya.shop.interceptor.SqlInjectInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * 拦截器配置
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// sql注入拦截器
		registry.addInterceptor(new SqlInjectInterceptor()).addPathPatterns("/**");

		WebMvcConfigurer.super.addInterceptors(registry);
	}

	/**
	 * 跨域请求全局配置
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
//				.allowedHeaders("Authorization","Token") // 允许的请求头信息
//				.allowedMethods("POST") // 仅允许POST
				.allowedOrigins("http://localhost:8080");//允许域名访问，如果*，代表所有域名

	//	WebMvcConfigurer.super.addCorsMappings(registry);
	}

	/**
	 * 返回json字符串处理
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setSerializerFeatures(
				// 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
				SerializerFeature.DisableCircularReferenceDetect,
				// List字段如果为null,输出为[],而非null
				SerializerFeature.WriteNullListAsEmpty,
				// 字符类型字段如果为null,输出为"",而非null
				SerializerFeature.WriteNullStringAsEmpty,
				// Boolean字段如果为null,输出为false,而非null
				SerializerFeature.WriteNullBooleanAsFalse,
				// 是否输出值为null的字段,默认为false
				SerializerFeature.WriteMapNullValue,
				// 默认为yyyy-MM-dd HH:mm:ss
				SerializerFeature.WriteDateUseDateFormat
		);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(0,fastJsonHttpMessageConverter);
	}

	/**
	 * 配置全局表单日期转换器
	 * @param dateConverter
	 * @return
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
