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
					case "":
						
					break;
				}
			}
		}
		
		bb.or(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(1));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public Page<EngineerUser> EngineerUserDisapprovedSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineerUser).leftJoin(engineerUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}
		}
		
		bb.or(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(-1));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public Page<EngineerUser> EngineerUserPendingSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		QEngineerUser engineerUser = QEngineerUser.engineerUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<EngineerUser> query = from(engineerUser).leftJoin(engineerUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}
		}
		
		bb.or(engineerUser.isDeleted.eq(0));
		
		bb.and(engineerUser.isApproved.eq(0));
		
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<EngineerUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<EngineerUser> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	

}
