package devgo.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final String TABSTRING = "\t";
	private static final String ENTERSTRING = "\r\n";
	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static <T> T json2Bean(String json,Class<T> clazz){
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String bean2Json(Object obj){
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String bean2JsonWithFormat(Object obj){
		return jsonFormat(bean2Json(obj));
	}
	public static String jsonFormat(String json){
		StringBuffer value = new StringBuffer();
		char[] jsonChars = json.toCharArray();
		int depth = 0;
		for(char jsonChar:jsonChars){
			if(jsonChar == '{'||jsonChar == '['){
				String tab = getCurrentTab(depth++);
				value.append(jsonChar);
				value.append(tab+ENTERSTRING);
				value.append(getCurrentTab(depth));
				
			}else if(jsonChar == '}'||jsonChar == ']'){
				depth--;
				String tab = getCurrentTab(depth);
				value.append(tab+ENTERSTRING);
				value.append(tab);
				value.append(jsonChar);
			}else if(jsonChar == ','){
				String tab = getCurrentTab(depth);
				value.append(jsonChar+ENTERSTRING);
				value.append(tab);
			}else{
				value.append(jsonChar);
			}
		}
		return value.toString();
	}
	private static String getCurrentTab(int depth){
		String result = "";
		for(int i=0;i<depth;i++){
			result+=TABSTRING;
		}
		return result;
	}
}
