package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.EngineerUser;
import org.d2z.domain.QEngineerUser;
import org.d2z.domain.QLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;

public class EngineerUserSearchImpl extends QuerydslRepositorySupport implements EngineerUserSearch {
	
	public EngineerUserSearchImpl() {
		super(EngineerUser.class);
	}

	@Override
	public Page<EngineerUser> EngineerUserSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineerUser).leftJoin(engineerUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "n":
						bb.or(engineerUser.engineerUserName.contains(keyword));
					break;
					case "j":
						bb.or(engineerUser.engineerUserJob.contains(keyword));
					break;
				}
			}
		}
		
		bb.and(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(1));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public Page<EngineerUser> EngineerUserDisapprovedSearchByKeyword(String keyword, Pageable pageable) {
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineerUser).leftJoin(engineerUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if(keyword != null) {
			bb.and(engineerUser.engineerUserName.contains(keyword));
		}
		
		bb.and(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(-1));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public Page<EngineerUser> EngineerUserPendingSearchByKeyword(String keyword, Pageable pageable) {
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineerUser).leftJoin(engineerUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if(keyword != null) {
			bb.and(engineerUser.engineerUserName.contains(keyword));
		}
		
		bb.and(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(0));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public Page<EngineerUser> matchingEngineerUserSystem(String[] types, String keyword, Pageable pageable) {
		
		QEngineerUser engineer = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineer).leftJoin(engineer.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if(types != null && types.length > 0) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}	
		}
		bb.and(engineer.isDeleted.eq(0));
		
		bb.and(engineer.isApproved.eq(1));
		
		query.where(bb);
		
		query.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc());
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public int matchingTotalEngineer(String[] types, String keyword, Pageable pageable) {
		
		QEngineerUser engineer = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineer).leftJoin(engineer.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if(types != null && types.length > 0) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}	
		}
		bb.and(engineer.isDeleted.eq(0));
		
		bb.and(engineer.isApproved.eq(1));
		
		query.where(bb);
		
		query.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc());
		
		int count = (int)query.fetchCount();
		
		return count;
	}

	@Override
	public int totalApprovedEngineerCount() {
		
		QEngineerUser engineer = QEngineerUser.engineerUser;
		
		JPQLQuery<EngineerUser> query = from(engineer);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		bb.and(engineer.isDeleted.eq(0));
		
		bb.and(engineer.isApproved.eq(1));
		
		query.where(bb);
		
		int count = (int)query.fetchCount();
		
		return count;
	}

	@Override
	public Page<EngineerUser> EngineerUserSearchByKeywordAndCareer(String[] types, String keyword, Pageable pageable,double fromNo, double toNo) {
		
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineerUser).leftJoin(engineerUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "n":
						bb.or(engineerUser.engineerUserName.contains(keyword));
					break;
					case "j":
						bb.or(engineerUser.engineerUserJob.contains(keyword));
					break;
				}
			}
		}
		
		if(fromNo >= 0 && toNo >= fromNo) {
			bb.and(engineerUser.engineerUserCareer.between(fromNo, toNo));
		}
		
		bb.and(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(1));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public List<EngineerUser> engineerUserListByDeleted() {
		
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		JPQLQuery<EngineerUser> query = from(engineerUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		bb.and(engineerUser.isDeleted.eq(1));
		
		query.where(bb);
		
		return query.fetch();
	}

	

}
