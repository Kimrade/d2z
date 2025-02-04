package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.PublicAnnouncement;
import org.d2z.domain.QPublicAnnouncement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
		
		JPQLQuery<PublicAnnouncement> query = from(publicAnnouncement);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		if((types != null && types.length > 0) && keyword != null) {
			for(String type : types) {
				switch(type) {
					case "":
						
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
