package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.PublicAnnouncement;
import org.d2z.domain.QCompanyUser;
import org.d2z.domain.QPublicAnnouncement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class PublicAnnouncementSearchImpl extends QuerydslRepositorySupport implements PublicAnnouncementSearch {

	public PublicAnnouncementSearchImpl() {
		super(PublicAnnouncement.class);
	}

	@Override
	public Page<PublicAnnouncement> publicAnnouncementSearchByKeyword(String[] types, String keyword, Pageable pageable) {
		
		QPublicAnnouncement publicAnnouncement = QPublicAnnouncement.publicAnnouncement;
		
		QCompanyUser companyUser = QCompanyUser.companyUser;
		
		JPQLQuery<PublicAnnouncement> query = from(publicAnnouncement).leftJoin(publicAnnouncement.companyUser, companyUser);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "n":
						bb.or(publicAnnouncement.announcementName.contains(keyword));
					break;
					case "j":
						bb.or(publicAnnouncement.serviceJob.contains(keyword));
					break;
					case "a":
						bb.or(publicAnnouncement.serviceAdd.contains(keyword));
					break;
					case "d":
						bb.or(publicAnnouncement.serviceDiv.contains(keyword));
					break;
					case "p":
						bb.or(publicAnnouncement.servicePeriod.contains(keyword));
					break;
				}
			}
			query.where(bb);
		}
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<PublicAnnouncement> list = query.fetch();
		
		Long count = query.fetchCount();
		
		Page<PublicAnnouncement> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

}
