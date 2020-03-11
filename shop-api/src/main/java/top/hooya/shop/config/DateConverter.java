package top.hooya.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式全局配置
 */
@Configuration
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		try {
			if (source.length()>10){
				return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source);

			}
			return new SimpleDateFormat("yyyy-MM-dd").parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

}
