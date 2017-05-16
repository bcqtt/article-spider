package com.lz.art.service;

public interface IAccountService {
	
	public boolean login(String username,String password);

	public boolean register(String username, String password);

}
