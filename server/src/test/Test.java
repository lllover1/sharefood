package test;


import java.util.List;



import com.mobile.dao.impl.ImplShareDao;

import com.moblie.bean.ShareCollection;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//获得最后一条数据 ，并且把它转换成 json字符串
		List<ShareCollection> l;
		l = new ImplShareDao().getall();
		System.out.println(l.get(0));
	}

}
