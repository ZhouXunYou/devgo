package devgo.test.service.impl;

import org.springframework.stereotype.Service;

import devgo.framework.core.service.impl.EntityServiceImpl;
import devgo.test.dao.TestDao;
import devgo.test.model.TestModel;
import devgo.test.service.TestService;

@Service
public class TestServiceImpl extends EntityServiceImpl<TestModel, TestDao> implements TestService {

//	@Override
//	public void updateName() {
//		
//		
//		TestModel tm = new TestModel();
//		tm.setName("cccccc");
//		this.added(tm);
//		getEntityDao().updateName();
//	}
//
//	@Override
//	public PageResult<TestModel> find(int pageNumber, int pageSize) {
////		getEntityDao().queryPage(pageNumber, pageSize);
//		System.out.println(jsonFormat(bean2Json(getEntityDao().paging(pageNumber, pageSize))));
//		SortSchema<String, Boolean> schema = new SortSchema<String, Boolean>("name", false);
//		System.out.println(jsonFormat(bean2Json(getEntityDao().paging(pageNumber, pageSize,schema))));
//		DynamicSpecification ds = new DynamicSpecification(Operators.BETWEEN, "id", 2,3);
//		List<DynamicSpecification> array = new ArrayList<DynamicSpecification>();
//		array.add(ds);
//		System.out.println(jsonFormat(bean2Json(getEntityDao().paging(pageNumber, pageSize,array))));
//		System.out.println(jsonFormat(bean2Json(getEntityDao().paging(pageNumber, pageSize,array,schema))));
////		JsonUtil.bean2Json(getEntityDao().paging(pageNumber, pageSize));
//		return null;
//	}
	
	
	
	
}
