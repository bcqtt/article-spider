package com.lz.art.base;

public class BaseServiceImpl<T> implements IBaseService<T> {

	IBaseDao<T> baseDao;
	
	@Override
	public void insert(T obj) {
		
		int num = baseDao.insert(obj);
	}

}
