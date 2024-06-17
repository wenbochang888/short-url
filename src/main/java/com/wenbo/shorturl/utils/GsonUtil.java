package com.wenbo.shorturl.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author changwenbo
 * @date 2024/6/17 19:40
 */
public class GsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(GsonUtil.class);

    private static Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .disableHtmlEscaping()
            .setVersion(1.0)
            .create();


    public static String toJson(Object src) {
        try {
            if (src instanceof String) {
                return (String) src;
            }
            return gson.toJson(src);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        try {
            return gson.fromJson(jsonStr, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T fromJson(JsonElement jsonElement, Class<T> clazz) {
        try {
            return gson.fromJson(jsonElement, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeToken<T> type) {
        try {
            return gson.fromJson(json, type.getType());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static JsonElement toJsonTree(Object src) {
        try {
            return gson.toJsonTree(src);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static JsonObject toJsonObject(Object src) {
        try {
            return gson.toJsonTree(src).getAsJsonObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static JsonObject parse(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return (JsonObject) JsonParser.parseString(json);
        } catch (Exception e) {
            logger.warn("parse json fail e={} ", e.getMessage(), e);
        }
        return null;
    }

    public static JsonElement parseElement(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return JsonParser.parseString(json);
        } catch (Exception e) {
            logger.warn("parse json fail e={} ", e.getMessage(), e);
            return null;
        }
    }

    public static JsonObject parseWithNullable(String json) {
        if (StringUtils.isBlank(json)) {
            return new JsonObject();
        }
        try {
            return (JsonObject) JsonParser.parseString(json);
        } catch (Exception e) {
            logger.warn("parse json fail e={}", e.getMessage(), e);
        }
        return new JsonObject();
    }


    public static boolean hasKey(JsonObject jsonObject, String key) {
        return null != jsonObject && jsonObject.has(key);
    }

    public static boolean hasKeyValue(JsonObject jsonObject, String key) {
        return null != jsonObject && jsonObject.has(key) && !jsonObject.get(key).isJsonNull();
    }

    public static String getValue(JsonObject jsonObject, String key) {
        if (hasKey(jsonObject, key)) {
            return jsonObject.get(key).getAsString();
        } else {
            return null;
        }
    }

    public static JsonObject getAsJsonObject(JsonObject jsonObject, String key) {
        if (hasKey(jsonObject, key)) {
            return jsonObject.get(key).getAsJsonObject();
        } else {
            return new JsonObject();
        }
    }

    public static String getValueOrDefault(JsonObject jsonObject, String key, String defaultValue) {
        return hasKey(jsonObject, key) ? jsonObject.get(key).getAsString() : defaultValue;
    }


    public static boolean getValueOrDefault(JsonObject jsonObject, String key, boolean defaultValue) {
        return hasKeyValue(jsonObject, key) ? jsonObject.get(key).getAsBoolean() : defaultValue;
    }
}
