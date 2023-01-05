package learning.order.config.redis;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import learning.order.config.ObjectMapperUtil;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 程行荣
 * Time: 2014-04-14 10:56
 * Copyright (C) 2014 Xiamen Yaxon Networks CO.,LTD.
 */

public class JsonCommonSerializer {
	
	protected static final String EMPTY_JSON = "{}";

    protected static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8") ;
    
    protected ObjectMapper objectMapper = ObjectMapperUtil.instance.getAdvanceObjectMapper();

    /**
     * java-object as json-string
     * @param object
     * @return
     */
    public String seriazileAsString(Object object){
        if (object== null) {
            return EMPTY_JSON;
        }
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    /**
     * json-string to java-object
     * @param str
     * @return
     */
    public <T> T deserializeAsObject(String str, Class<T> clazz){
        if(str == null || clazz == null){
            return null;
        }
        try{
            return this.objectMapper.readValue(str, clazz);
        }catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
    
    public byte[] seriazileAsBytes(Object object){
        if (object== null) {
            return new byte[] {0};
        }
        try {
            return this.objectMapper.writeValueAsBytes(object);
        } catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    public <T> T deserializeBytesAsObject(byte[] bytes, Class<T> clazz){
        if(bytes.length == 0){
            return null;
        }
        try{
            return this.objectMapper.readValue(new String(bytes , DEFAULT_CHARSET), clazz);
        }catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
    
    
    public <T> List<T> deserializeStringAsList(String json, Class<T> clazz){
    	List<T> list = new ArrayList<T>() ;
        if(StringUtils.isBlank(json)){
            return list;
        }
        try{
        	CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        	return objectMapper.readValue(json, listType);
        }catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
    
    public <T> List<T> deserializeBytesAsList(byte[] bytes, Class<T> clazz){
    	List<T> list = new ArrayList<T>() ;
        if(bytes.length == 0){
            return list;
        }
        try{
        	String json = new String(bytes , DEFAULT_CHARSET) ;
        	CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        	return objectMapper.readValue(json, listType);
        }catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
    
}
