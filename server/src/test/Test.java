package test;


import java.util.List;



import com.mobile.dao.impl.ImplShareDao;

import com.moblie.bean.ShareCollection;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//������һ������ �����Ұ���ת���� json�ַ���
		List<ShareCollection> l;
		l = new ImplShareDao().getall();
		System.out.println(l.get(0));
	}

}
