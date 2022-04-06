package com.example.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class FastJsonConfig {

    /**
     * 配置消息转换器
     * <p>
     * FastJSON全局配置说明
     * <p>
     * SerializerFeature.PrettyFormat:格式化输出
     * SerializerFeature.WriteMapNullValue:是否输出值为null的字段,默认为false
     * SerializerFeature.DisableCircularReferenceDetect:消除循环引用
     * SerializerFeature.WriteNullStringAsEmpty:将为null的字段值显示为""
     * WriteNullListAsEmpty：List字段如果为null,输出为[],而非null
     * WriteNullNumberAsZero：数值字段如果为null,输出为0,而非null
     * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
     * SkipTransientField：如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
     * SortField：按字段名称排序后输出。默认为false
     * WriteDateUseDateFormat：全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
     * BeanToArray：将对象转为array输出
     * QuoteFieldNames：输出key时是否使用双引号,默认为true
     * UseSingleQuotes：输出key时使用单引号而不是双引号,默认为false（经测试，这里的key是指所有的输出结果，而非key/value的key,而是key,和value都使用单引号或双引号输出）
     *
     * @return {@link HttpMessageConverter}
     */
    @Bean
    public HttpMessageConverter configureMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
        config.setSerializerFeatures(
                // 将List类型的null转成[]
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        List<MediaType> mediaTypeList = Lists.newArrayList(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }
}