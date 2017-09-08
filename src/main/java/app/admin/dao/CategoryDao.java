package app.admin.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import app.admin.entity.Category;
import chok.devwork.springboot.BaseDao;

@Repository
public class CategoryDao extends BaseDao<Category,Long>
{
	@Resource(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<Category> getEntityClass()
	{
		return Category.class;
	}
}
