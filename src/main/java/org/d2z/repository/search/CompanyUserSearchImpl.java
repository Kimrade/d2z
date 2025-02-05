package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.CompanyUser;
import org.d2z.domain.QCompanyUser;
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
		
		// select * from CompanyUser;
		JPQLQuery<CompanyUser> query = from(companyUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}
		}
		bb.or(companyUser.isDeleted.eq(0));
		
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
	public Page<CompanyUser> companyUserDisapprovecSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		// select * from CompanyUser;
		JPQLQuery<CompanyUser> query = from(companyUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}
		}
		bb.or(companyUser.isDeleted.eq(0));
		
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
	public Page<CompanyUser> companyUserPendingSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		// select * from CompanyUser;
		JPQLQuery<CompanyUser> query = from(companyUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "":
						
					break;
				}
			}
		}
		bb.or(companyUser.isDeleted.eq(0));
		
		bb.and(companyUser.isApproved.eq(0));
		
		// where 조건 (검색 조건으로 받아온 값을 저장함)
		query.where(bb);
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<CompanyUser> list = query.fetch();
		Long count = query.fetchCount();
		
		Page<CompanyUser> result = new PageImpl<>(list,pageable,count);
		
		return result;
	}

	

}
