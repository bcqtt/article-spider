package com.lz.art.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.art.dao.AccountMapper;
import com.lz.art.pojo.Account;
import com.lz.art.pojo.AccountExample;
import com.lz.art.pojo.AccountExample.Criteria;
import com.lz.art.service.IAccountService;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
	
	@Resource
	private AccountMapper accountMapper;
	
	@Override
	public boolean login(String username, String password) {
		AccountExample emp = new AccountExample();
		Criteria cri = emp.createCriteria();
		cri.andAccountEqualTo(username);
		cri.andPasswordEqualTo(password);
		List<Account> accList = accountMapper.selectByExample(emp);
		
		return accList.size()>0?true:false;
	}

	@Override
	public boolean register(String username, String password) {
		Account acc = new Account();
		acc.setAccount(username);
		acc.setPassword(password);
		int n = accountMapper.insert(acc);
		return n>0?true:false;
	}

}
