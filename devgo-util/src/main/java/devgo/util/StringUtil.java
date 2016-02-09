package devgo.util;

import java.util.Date;

public class StringUtil {
	public static boolean isEmpty(String value){
		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		for(StackTraceElement stackTraceElement:stackTraceElements){
			System.out.println(stackTraceElement);
		}
		return CommonUtil.isEmpty(value);
	}
	public static boolean isNotEmpty(String value){
		return CommonUtil.isNotEmpty(value);
	}
	public static String concat(Object...values){
		String result = "";
		for(Object object:values){
			if(CommonUtil.isEmpty(object)){
				continue;
			}
			if(object instanceof String){
				result+=String.format("%s", object);
			}else if(object instanceof Integer || object instanceof Long){
				result+=String.format("%d", object);
			}else if(object instanceof Float || object instanceof Double){
				result+=String.format("%1$.2f", object);
			}else if(object instanceof Date){
				result+=DateUtil.getDateTime((Date)object);
			}else{
				result+=object.toString();
			}
		}
		return result;
	}
}
