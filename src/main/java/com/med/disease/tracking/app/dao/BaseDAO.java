package com.med.disease.tracking.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BaseDAO {

	@Autowired
	private ApplicationContext context;
	private SqlSession sqlSession;

	public synchronized SqlSession getSqlSession() {
		return (sqlSession == null) ? context.getBean(SqlSession.class) : sqlSession;
	}
}
