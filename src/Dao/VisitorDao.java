package Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Visitor;
import utils.HibernateUtils;

public class VisitorDao implements IBaseDao<Visitor> {

	private SessionFactory sessionFactory;
	private Session session;

	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	
	@Override
	public String addObject(Visitor object) {
		Session s = HibernateUtils.getSession();
		try{			
			Transaction tx = s.beginTransaction();			
			s.save(object);
			tx.commit();
			return "保存成功";
		}finally{
			if (null != s){
				s.close();
			}
		}
		
	}

	@Override
	public void delObject(Visitor object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delObjectByIdCardNum(String idCardNum) {
		// TODO Auto-generated method stub
		Session s = HibernateUtils.getSession();
		try{
			String strHql = "delete from Visitor where idCardNum='"+idCardNum+"'";
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery(strHql);
			q.executeUpdate();
			tx.commit();
		}finally{
			if (null != s){
				s.close();
			}
		}
	}

	@Override
	public String updateObject(Visitor object) {
		//更新用户信息
		Session s = sessionFactory.openSession();
//		Session s = HibernateUtils.getSession();
		try{
			Transaction tx = s.beginTransaction();
			s.saveOrUpdate(object);
			tx.commit();
			return "更新成功";
		}finally{
			if(null != s){
				s.close();
			}
		}
		
	}

	@Override
	public List<Visitor> queryObjectListBySql(String strSql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> queryObjectListByHql(String idCardNum) {
//		Session s = sessionFactory.openSession();
                Session s = HibernateUtils.getSession();
		try{    
                        String strHql = "from Visitor where idCardNum ="+  idCardNum;
			Query query = s.createQuery(strHql);
			return query.list();
		}finally{
			if(null != s){
				s.close();
			}
		}
		
	
	}

	@Override
	public List<Visitor> queryListForPage(String strHql, int iStart, int iNum) {
		Session s = sessionFactory.openSession();
		try{
                        
			Query query = s.createQuery(strHql);
			query.setFirstResult(iStart*iNum);
			query.setMaxResults(iNum);
			return query.list();
		}finally{
			if(null != s){
				s.close();
			}
		}		
	}


	@Override
	public int getAllCountByHql(String strHql) {
		Session s = sessionFactory.openSession();
		try{
			Query query = s.createQuery(strHql);
			return ((Number)query.uniqueResult()).intValue();
		}finally{
			if(null != s){
				s.close();
			}
		}		
	}


	@Override
	public Visitor queryUniqueObject(String id) {
		Session s = HibernateUtils.getSession();
		try{
			String strHql = "from Visitor where id ="+  id;
			Query query = s.createQuery(strHql);
			return (Visitor)query.uniqueResult();
		}finally{
			if(null != s){
				s.close();
			}
		}	
	}

}
