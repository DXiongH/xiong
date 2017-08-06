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
         * org.hibernate.cfg.Configuration类的作用： 
         * 读取hibernate配置文件(hibernate.cfg.xml或hiberante.properties)的. 
         * new Configuration()默认是读取hibernate.properties 
         * 所以使用new Configuration().configure();来读取hibernate.cfg.xml配置文件 
         */  
        cfg = new Configuration().configure("conf/hibernate.cfg.xml");  
        //cfg = new Configuration().configure();  
          
        /* 
         * 创建SessionFactory 
         * 一个数据库对应一个SessionFactory  
         * SessionFactory是线线程安全的。 
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
            //创建session  
            //此处的session并不是web中的session  
            //session只有在用时，才建立concation,session还管理缓存。  
            //session用完后，必须关闭。  
            //session是非线程安全，一般是一个请求一个session.  
            session = sf.openSession();  
              
            //手动开启事务(可以在hibernate.cfg.xml配置文件中配置自动开启事务)  
            tx=session.beginTransaction();  
            /* 
             * 保存数据，此处的数据是保存对象，这就是hibernate操作对象的好处， 
             * 我们不用写那么多的JDBC代码，只要利用session操作对象，至于hibernat如何存在对象，这不需要我们去关心它， 
             * 这些都有hibernate来完成。我们只要将对象创建完后，交给hibernate就可以了。 
             */  
            session.save(s);  
            tx.commit();  
        } catch (HibernateException e) {  
            e.printStackTrace();  
            //回滚事务  
            tx.rollback();  
        } finally {  
            //关闭session  
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
            //创建session  
            //此处的session并不是web中的session  
            //session只有在用时，才建立concation,session还管理缓存。  
            //session用完后，必须关闭。  
            //session是非线程安全，一般是一个请求一个session.  
            session = sf.openSession();  
            //手动开启事务(可以在hibernate.cfg.xml配置文件中配置自动开启事务)  
            tx=session.beginTransaction();  
            /* 
             * 保存数据，此处的数据是保存对象，这就是hibernate操作对象的好处， 
             * 我们不用写那么多的JDBC代码，只要利用session操作对象，至于hibernat如何存在对象，这不需要我们去关心它， 
             * 这些都有hibernate来完成。我们只要将对象创建完后，交给hibernate就可以了。 
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
            //回滚事务  
            tx.rollback();  
        } finally {  
            //关闭session  
            session.close();  
            sf.close();  
        }
        return user;
	}

}
