package com.kalda.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.domain.TblReplyJavaRanch;
import com.kalda.domain.TblThreadJavaRanch;
import com.kalda.domain.TblThreadStackoverflow;
import com.kalda.domain.TblUser;

public class CorpusMgrDAOImpl extends HibernateDaoSupport implements
		CorpusMgrDAO {

	@Override
	public boolean saveThreadJavaRanch(TblThreadJavaRanch tjr) {

		this.getHibernateTemplate().saveOrUpdate(tjr);
		System.out.println(tjr.getId());
		return true;
	}

	@Override
	public TblThreadJavaRanch getThreadJavaRanch(int id) {

		String hql = "FROM TblThreadJavaRanch WHERE id = ?";
		List<TblThreadJavaRanch> list = this.getHibernateTemplate().find(hql,id);
		TblThreadJavaRanch tjr = list.get(0);
		return tjr;
	}

	@Override
	public boolean saveReplyJavaRanch(TblReplyJavaRanch rjr) {
		this.getHibernateTemplate().saveOrUpdate(rjr);
		// System.out.println("Reply id-"+rjr.getId() +
		// " Thread id-"+rjr.getThreadId());
		return true;
	}

	@Override
	public List<String> loadAllUsers() {
		
		List<String> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)throws HibernateException, SQLException {
				return session.createQuery("SELECT DISTINCT author FROM TblReplyJavaRanch ORDER BY author").list();
			}
		});
		return list;
	}

	@Override
	public List<TblUser> loadAllUsersWithPostAndReply() {
		
		String hql = "FROM TblUser";
		List<TblUser> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean saveUserJavaRanch(List<TblUser> userList) {
		for (int i=0;i<userList.size();i++)  {
			this.getHibernateTemplate().saveOrUpdate(userList.get(i));
			System.out.println("##### save "+i+" user");
		}
		return true;
	}

	@Override
	public boolean updateUserJavaRanchForPostAmountAndReplyAmount(List<TblUser> userList) {
		// in table TblUser, 一共14729个user
		for (int i=10000;i<14729;i++) {
			
			String posthql = "FROM TblReplyJavaRanch WHERE author=? AND replySequenceNum=0";
			List<TblReplyJavaRanch> postlist = this.getHibernateTemplate().find(posthql,userList.get(i).getUserName());
			userList.get(i).setPostAmount(postlist.size());
			
			String replyhql = "FROM TblReplyJavaRanch WHERE author=? AND replySequenceNum<>0";
			List<TblReplyJavaRanch> replylist = this.getHibernateTemplate().find(replyhql,userList.get(i).getUserName());
			//userList.get(i).setReplyAmount(replylist.size());
			int ramount = replylist.size();
			for (int j=0; j<postlist.size(); j++) {
				
				Integer threadId = postlist.get(j).getThreadId();
				for (int m=0;m<replylist.size();m++) {
					if (threadId.intValue() == replylist.get(m).getThreadId().intValue()) {
						ramount = ramount - 1;
					}
					
				}
				
			}
			
			userList.get(i).setReplyAmount(ramount);
			this.getHibernateTemplate().saveOrUpdate(userList.get(i));
			
			System.out.println("##### No."+i+" updated user "+userList.get(i).getUserName()+" PostAmount="+userList.get(i).getPostAmount()+" ReplyAmount="+userList.get(i).getReplyAmount());
		}
		
		
		return true;
	}

	@Override
	public List<TblReplyJavaRanch> getReplyJavaRanch(TblUser u) {
		
		String hql = "FROM TblReplyJavaRanch WHERE author = ?";
		
		List<TblReplyJavaRanch> list = this.getHibernateTemplate().find(hql,new Object[] {u.getUserName()});
		
		return list;
	}
	@Override
	public List<TblReplyJavaRanch> getReplyJavaRanch() {
		
		String hql = "FROM TblReplyJavaRanch";
		
		List<TblReplyJavaRanch> list = this.getHibernateTemplate().find(hql);
		
		return list;
	}

	@Override
	public boolean saveThreadStackoverflow(TblThreadStackoverflow tsf) {
		this.getHibernateTemplate().saveOrUpdate(tsf);
		System.out.println(tsf.getId());
		return true;
	}

	@Override
	public TblThreadStackoverflow getThreadStackoverflow(int id) {
		String hql = "FROM TblThreadStackoverflow WHERE id = ?";
		List<TblThreadStackoverflow> list = this.getHibernateTemplate().find(hql,id);
		TblThreadStackoverflow tsf = list.get(0);
		return tsf;
	}

	@Override
	public List<TblKeyWordsForTopic> loadKeyWordsForTopic(String whichCorpus, String whichLDARun) {
		List<TblKeyWordsForTopic> returnList = new ArrayList<TblKeyWordsForTopic>();
		String hql = "FROM TblKeyWordsForTopic WHERE whichCorpus = ? AND whichLDARun = ?";
		returnList = this.getHibernateTemplate().find(hql,new Object[] {whichCorpus, whichLDARun});
		return returnList;
	}

	@Override
	public List<TblReplyJavaRanch> getReplyJavaRanch(int id) {
		
		String hql = "FROM TblReplyJavaRanch WHERE id = ?";
		
		List<TblReplyJavaRanch> list = this.getHibernateTemplate().find(hql,new Object[] {id});
		
		return list;
	}

	@Override
	public TblReplyJavaRanch loadReply(int replyId) {
		String hql = "FROM TblReplyJavaRanch WHERE id = ?";
		List<TblReplyJavaRanch> list = this.getHibernateTemplate().find(hql,new Object[] {replyId});
		return list.get(0);
	}

	@Override
	public TblThreadJavaRanch loadThread(int threadId) {
		String hql = "FROM TblThreadJavaRanch WHERE id = ?";
		List<TblThreadJavaRanch> list = this.getHibernateTemplate().find(hql,new Object[] {threadId});
		return list.get(0);
	}

	@Override
	public List<TblReplyJavaRanch> getReplyContentWithSourceCode() {
		
		List<TblReplyJavaRanch> returnList = new ArrayList<TblReplyJavaRanch>();
		String hql = "FROM TblReplyJavaRanch";
		List<TblReplyJavaRanch> list = this.getHibernateTemplate().find(hql);
		for (int i=0;i<1000;i++) {
			TblReplyJavaRanch rjr = new TblReplyJavaRanch();
			rjr = list.get(i);
			if (rjr.getReplyContent().contains("<textarea") && rjr.getReplyContent().contains("import")) {
				returnList.add(rjr);
				int threadId = rjr.getThreadId();
				TblThreadJavaRanch tjr = loadThread(threadId);
				System.out.println(tjr.getThreadURL());
			}
		}
		return returnList;
	}
}
