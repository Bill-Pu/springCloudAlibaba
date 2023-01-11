package learning.order.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class ObjectMapperUtil {

	public static final ObjectMapperUtil instance = new ObjectMapperUtil();

	protected ObjectMapper objectMapper;

	private ObjectMapperUtil() {
		objectMapper = new ObjectMapper();
	}

	public ObjectMapper getAdvanceObjectMapper() {
		objectMapper = getDeafaultObjectMapper();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper;
	}

	public ObjectMapper getDeafaultObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(CommonDateConstants.simpleDateFormat).setTimeZone(CommonDateConstants.timeZone);

		SimpleModule module = new SimpleModule();
		module.addSerializer(Long.class, ToStringSerializer.instance);
		module.addSerializer(long.class, ToStringSerializer.instance);
		module.addSerializer(Long.TYPE, ToStringSerializer.instance);
		module.addSerializer(BigInteger.class, ToStringSerializer.instance);
		objectMapper.registerModule(module);

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(CommonDateConstants.dateTimeFormatter));
		javaTimeModule.addSerializer(LocalDate.class,
				new LocalDateSerializer(CommonDateConstants.dateTimeFormatterYYYYMMDD));
		javaTimeModule.addSerializer(LocalTime.class,
				new LocalTimeSerializer(CommonDateConstants.dateTimeFormatterHHMMSS));
		// 日期反序列化
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(CommonDateConstants.dateTimeFormatter));
		javaTimeModule.addDeserializer(LocalDate.class,
				new LocalDateDeserializer(CommonDateConstants.dateTimeFormatterYYYYMMDD));
		javaTimeModule.addDeserializer(LocalTime.class,
				new LocalTimeDeserializer(CommonDateConstants.dateTimeFormatterHHMMSS));
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}

}
