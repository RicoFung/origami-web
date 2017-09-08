package app.admin.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import app.admin.entity.Model;
import chok.devwork.springboot.BaseDao;


@Repository
public class ModelDao extends BaseDao<Model,Long>
{
	@Resource(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<Model> getEntityClass()
	{
		return Model.class;
	}
}
