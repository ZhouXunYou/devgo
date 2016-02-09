package devgo.util;

import java.util.Collection;
import java.util.Map;

public class CommonUtil {
	/**
	 * 判断对象是否空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object){
		boolean isEmpty = false;
		if(object == null||"".equals(object)){
			isEmpty = true;
		}else if(object instanceof Collection){
			Collection<?> collection = (Collection<?>)object;
			isEmpty = collection.isEmpty();
		}else if(object instanceof Map){
			Map<?,?> map = (Map<?,?>)object;
			isEmpty = map.isEmpty();
		}else if(object.getClass().isArray()){
			Object[] objects = (Object[])object;
			isEmpty = (objects.length>0);
		}
		return isEmpty;
	}
	/**
	 * 判断对象是否非空
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object){
		return !isEmpty(object);
	}
	
}
