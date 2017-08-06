package Test;

import java.net.URL;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import Demo.jdbc.UserBean;

public class HibernateTest {
	private static Configuration cfg = null;  
	private static SessionFactory sf = null;  
	static 
	{
		/* 
         * org.hibernate.cfg.Configuration������ã� 
         * ��ȡhibernate�����ļ�(hibernate.cfg.xml��hiberante.properties)��. 
         * new Configuration()Ĭ���Ƕ�ȡhibernate.properties 
         * ����ʹ��new Configuration().configure();����ȡhibernate.cfg.xml�����ļ� 
         */  
        cfg = new Configuration().configure("conf/hibernate.cfg.xml");  
        //cfg = new Configuration().configure();  
          
        /* 
         * ����SessionFactory 
         * һ�����ݿ��Ӧһ��SessionFactory  
         * SessionFactory�����̰߳�ȫ�ġ� 
         */  
        sf = cfg.buildSessionFactory();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          
        UserBean s = new UserBean();  
//        s.setId("3");  
//        s.setName("zz");
//        addUser(s); 
        System.out.println(getUser("3"));
	}
	private static void addUser(UserBean s) {
		Session session = null; 
        Transaction tx = null;
          
        try {  
            //����session  
            //�˴���session������web�е�session  
            //sessionֻ������ʱ���Ž���concation,session�������档  
            //session����󣬱���رա�  
            //session�Ƿ��̰߳�ȫ��һ����һ������һ��session.  
            session = sf.openSession();  
              
            //�ֶ���������(������hibernate.cfg.xml�����ļ��������Զ���������)  
            tx=session.beginTransaction();  
            /* 
             * �������ݣ��˴��������Ǳ�����������hibernate��������ĺô��� 
             * ���ǲ���д��ô���JDBC���룬ֻҪ����session������������hibernat��δ��ڶ����ⲻ��Ҫ����ȥ�������� 
             * ��Щ����hibernate����ɡ�����ֻҪ�����󴴽���󣬽���hibernate�Ϳ����ˡ� 
             */  
            session.save(s);  
            tx.commit();  
        } catch (HibernateException e) {  
            e.printStackTrace();  
            //�ع�����  
            tx.rollback();  
        } finally {  
            //�ر�session  
            session.close();  
            sf.close();  
        }
	}
	
	private static UserBean getUser(String id)
	{
		UserBean user=null;
		Session session = null; 
        Transaction tx = null;
          
        try {  
        	String sql="select * from user where id=:id";
            //����session  
            //�˴���session������web�е�session  
            //sessionֻ������ʱ���Ž���concation,session�������档  
            //session����󣬱���رա�  
            //session�Ƿ��̰߳�ȫ��һ����һ������һ��session.  
            session = sf.openSession();  
            //�ֶ���������(������hibernate.cfg.xml�����ļ��������Զ���������)  
            tx=session.beginTransaction();  
            /* 
             * �������ݣ��˴��������Ǳ�����������hibernate��������ĺô��� 
             * ���ǲ���д��ô���JDBC���룬ֻҪ����session������������hibernat��δ��ڶ����ⲻ��Ҫ����ȥ�������� 
             * ��Щ����hibernate����ɡ�����ֻҪ�����󴴽���󣬽���hibernate�Ϳ����ˡ� 
             */  
            Criteria criteria = session.createCriteria(UserBean.class).add( Restrictions.eq("id", id) );
            List user1=criteria.list();
//            SQLQuery  sqlQuery=session.createSQLQuery (sql).addEntity(UserBean.class);
//            sqlQuery.setString("id", id);  
//            user=(UserBean) sqlQuery.uniqueResult(); 
            user=(UserBean) user1.get(0);
            tx.commit();  
        } catch (HibernateException e) {  
            e.printStackTrace();  
            //�ع�����  
            tx.rollback();  
        } finally {  
            //�ر�session  
            session.close();  
            sf.close();  
        }
        return user;
	}

}
