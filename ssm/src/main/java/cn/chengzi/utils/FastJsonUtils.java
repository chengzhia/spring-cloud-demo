package cn.chengzi.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/*public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
 public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
 public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
 public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
 public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。*/

@SuppressWarnings("all")
public class FastJsonUtils {
	/**  
	 * 将bean转化为json  
	 * @param m  
	 * @return  
	 */  
	public static String objectToJson(Object object) {
		return JSON.toJSONString(object);
	}
	/**  
	 * 将json转化为bean  
	 * @param m  
	 * @return  
	 */  
	public static <T> T jsonToObject(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}
	/**  
	 * 将list转化为json  
	 * @param m  
	 * @return  
	 */  
	public static <T> String listToJson(List<T> tList) {
		return JSON.toJSONString(tList);
	}
	/**  
	 * 将json转化为list  
	 * @param m  
	 * @return  
	 */  
	public static <T> List<T> jsonToList(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}
	/**  
	 * 将map转化为json  
	 * @param m  
	 * @return  
	 */  
	public static String mapToJson(Map m) {  
		String s = JSONObject.toJSONString(m);  
		return s;  
	}  
	 /**  
     * json字符串转化为map  
     * @param s  
     * @return  
     */  
    public static Map jsonToMap(String s) {  
        Map m = JSONObject.parseObject(s);  
        return m;  
    }  
      
}
