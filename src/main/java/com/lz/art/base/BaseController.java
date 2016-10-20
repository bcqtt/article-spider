package com.lz.art.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BaseController<T> {
	
	IBaseService<T> baseService;
	
	/**
     * 设置基础service
     * @param baseService
     */
    @Autowired
    public void setBaseService(IBaseService<T> baseService) {
        this.baseService = baseService;
    }
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(T obj,HttpServletRequest request,HttpServletResponse response){
		baseService.insert(obj);
	}

}
