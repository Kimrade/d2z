package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.CompanyUser;
import org.d2z.domain.QCompanyUser;
import org.d2z.domain.QLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class CompanyUserSearchImpl extends QuerydslRepositorySupport implements CompanyUserSearch {

	public CompanyUserSearchImpl() {
		super(CompanyUser.class);
	}
	
	// 검색 조건에 따른 사업주 조회(페이지 처리)
	@Override
	public Page<CompanyUser> companyUserSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		// q 도메인 객체 생성
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<CompanyUser> query = from(companyUser).leftJoin(companyUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "n":
						bb.or(companyUser.companyName.contains(keyword));
					break;
					case "a":
						bb.or(companyUser.companyAdd.contains(keyword));
					break;
					case "i":
						bb.or(companyUser.companyNote.contains(keyword));
					break;
				}
			}
		}
		bb.and(companyUser.isDeleted.eq(0));
		
		bb.and(companyUser.isApproved.eq(1));
		
		// where 조건 (검색 조건으로 받아온 값을 저장함)
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<CompanyUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<CompanyUser> result = new PageImpl<>(list,pageable,count);
		
		return result;
	}

	@Override
	public Page<CompanyUser> companyUserDisapprovecSearchByKeyword(String keyword, Pageable pageable) {
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<CompanyUser> query = from(companyUser).leftJoin(companyUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if(keyword != null) {
			bb.and(companyUser.companyName.contains(keyword));
		}
		
		bb.and(companyUser.isDeleted.eq(0));
		
		bb.and(companyUser.isApproved.eq(-1));
		
		// where 조건 (검색 조건으로 받아온 값을 저장함)
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<CompanyUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<CompanyUser> result = new PageImpl<>(list,pageable,count);
		
		return result;
	}

	@Override
	public Page<CompanyUser> companyUserPendingSearchByKeyword(String keyword, Pageable pageable) {
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		QLogin login = QLogin.login;
		
		JPQLQuery<CompanyUser> query = from(companyUser).leftJoin(companyUser.login, login);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if(keyword != null) {
			bb.and(companyUser.companyName.contains(keyword));
		}
		
		bb.and(companyUser.isDeleted.eq(0));
		
		bb.and(companyUser.isApproved.eq(0));
		
		// where 조건 (검색 조건으로 받아온 값을 저장함)
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<CompanyUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<CompanyUser> result = new PageImpl<>(list,pageable,count);
		
		return result;
	}

	@Override
	public int totalCompanyCount() {
		
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		JPQLQuery<CompanyUser> query = from(companyUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		bb.and(companyUser.isApproved.eq(1));
		
		bb.and(companyUser.isDeleted.eq(0));
		
		query.where(bb);
		
		int count = (int)query.fetchCount();
		
		return count;
	}

	@Override
	public List<CompanyUser> companyUserDeletedList() {
		
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		JPQLQuery<CompanyUser> query = from(companyUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		bb.and(companyUser.isDeleted.eq(1));
		
		query.where(bb);
		
		return query.fetch();
	}

	

}
