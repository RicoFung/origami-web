package app.admin.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import app.admin.entity.Image;
import chok.devwork.springboot.BaseDao;


@Repository
public class ImageDao extends BaseDao<Image,Long>
{
	@Resource(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<Image> getEntityClass()
	{
		return Image.class;
	}
	
	public void delByModelIds(Long[] ids)
	{
		this.getSqlSession().update(getStatementName("delByModelIds"), ids);
	}
}
