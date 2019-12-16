package top.hooya.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求全局配置
 */
@Configuration
public class CorsConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
//						.allowedHeaders("Authorization","Token") // 允许的请求头信息
//						.allowedMethods("POST") // 仅允许POST
						.allowedOrigins("http://localhost:8080");//允许域名访问，如果*，代表所有域名
			}
		};
	}
}
