package com.example.demo.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Configuration
public class FastJsonMessageConverterConfig {

    /**
     * 配置消息转换器
     * <p>
     * FastJSON全局配置说明
     * <p>
     * SerializerFeature.PrettyFormat:格式化输出
     * <p>
     * SerializerFeature.WriteMapNullValue:是否输出值为null的字段,默认为false
     * <p>
     * SerializerFeature.DisableCircularReferenceDetect:消除循环引用
     * <p>
     * SerializerFeature.WriteNullStringAsEmpty:将为null的字段值显示为""
     * <p>
     * WriteNullListAsEmpty：List字段如果为null,输出为[],而非null
     * <p>
     * WriteNullNumberAsZero：数值字段如果为null,输出为0,而非null
     * <p>
     * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
     * <p>
     * SkipTransientField：如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
     * <p>
     * SortField：按字段名称排序后输出。默认为false
     * <p>
     * WriteDateUseDateFormat：全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
     * <p>
     * BeanToArray：将对象转为array输出
     * <p>
     * QuoteFieldNames：输出key时是否使用双引号,默认为true
     * <p>
     * UseSingleQuotes：输出key时使用单引号而不是双引号,默认为false（经测试，这里的key是指所有的输出结果，而非key/value的key,而是key,和value都使用单引号或双引号输出）
     *
     * @return {@link HttpMessageConverter}
     */
    @Bean
    public HttpMessageConverter<Object> configureMessageConverters() {
        com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter converter = new com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter();
        //自定义配置...
        com.alibaba.fastjson2.support.config.FastJsonConfig config = new com.alibaba.fastjson2.support.config.FastJsonConfig();

        // 指定的日期格式，默认yyyy-MM-dd HH:mm:ss
        // config.setDateFormat("yyyy-MM-dd HH:mm:ss");

        // 配置反序列化的指定行为
        // FieldBased	基于字段反序列化，如果不配置，会默认基于public的field和getter方法序列化。配置后，会基于非static的field（包括private）做反序列化。在fieldbase配置下会更安全
        // IgnoreNoneSerializable	反序列化忽略非Serializable类型的字段
        // SupportArrayToBean	支持数据映射的方式
        // InitStringFieldAsEmpty	初始化String字段为空字符串""
        // SupportAutoType	支持自动类型，要读取带"@type"类型信息的JSON数据，需要显示打开SupportAutoType
        // SupportSmartMatch	默认下是camal case精确匹配，打开这个后，能够智能识别camal/upper/pascal/snake/Kebab五中case
        // UseNativeObject	默认是使用JSONObject和JSONArray，配置后会使用LinkedHashMap和ArrayList
        // SupportClassForName	支持类型为Class的字段，使用Class.forName。为了安全这个是默认关闭的
        // IgnoreSetNullValue	忽略输入为null的字段
        // UseDefaultConstructorAsPossible	尽可能使用缺省构造函数，在fieldBase打开这个选项没打开的时候，会可能用Unsafe.allocateInstance来实现
        // UseBigDecimalForFloats	默认配置会使用BigDecimal来parse小数，打开后会使用Float
        // UseBigDecimalForDoubles	默认配置会使用BigDecimal来parse小数，打开后会使用Double
        // ErrorOnEnumNotMatch	默认Enum的name不匹配时会忽略，打开后不匹配会抛异常
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean, JSONReader.Feature.SupportSmartMatch, JSONReader.Feature.IgnoreSetNullValue);

        // 配置序列化的指定行为
        // FieldBased	基于字段反序列化，如果不配置，会默认基于public的field和getter方法序列化。配置后，会基于非static的field（包括private）做反序列化。
        // IgnoreNoneSerializable	序列化忽略非Serializable类型的字段
        // BeanToArray	将对象序列为[101,"XX"]这样的数组格式，这样的格式会更小
        // WriteNulls	序列化输出空值字段
        // BrowserCompatible	在大范围超过JavaScript支持的整数，输出为字符串格式
        // NullAsDefaultValue	将空置输出为缺省值，Number类型的null都输出为0，String类型的null输出为""，数组和Collection类型的输出为[]
        // WriteBooleanAsNumber	将true输出为1，false输出为0
        // WriteNonStringValueAsString	将非String类型的值输出为String，不包括对象和数据类型
        // WriteClassName	序列化时输出类型信息
        // NotWriteRootClassName	打开WriteClassName的同时，不输出根对象的类型信息
        // NotWriteHashMapArrayListClassName	打开WriteClassName的同时，不输出类型为HashMap/ArrayList类型对象的类型信息，反序列结合UseNativeObject使用，能节省序列化结果的大小
        // NotWriteDefaultValue	当字段的值为缺省值时，不输出，这个能节省序列化后结果的大小
        // WriteEnumsUsingName	序列化enum使用name
        // WriteEnumUsingToString	序列化enum使用toString方法
        // IgnoreErrorGetter	忽略setter方法的错误
        // PrettyFormat	格式化输出
        // ReferenceDetection	打开引用检测，这个缺省是关闭的，和fastjson 1.x不一致
        // WriteNameAsSymbol	将字段名按照symbol输出，这个仅在JSONB下起作用
        // WriteBigDecimalAsPlain	序列化BigDecimal使用toPlainString，避免科学计数法
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.WriteEnumUsingToString, JSONWriter.Feature.WriteBigDecimalAsPlain);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return converter;
    }
}