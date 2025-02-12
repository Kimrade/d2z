package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.PostMessage;
import org.d2z.domain.QPostMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;


public class PostMessageSearchImpl extends QuerydslRepositorySupport implements PostMessageSearch {

	public PostMessageSearchImpl() {
		super(PostMessage.class);
	}

	@Override
	public Page<PostMessage> findbySenderId(String id, Pageable pageable) {
		
		QPostMessage postMessage = QPostMessage.postMessage;
		
		JPQLQuery<PostMessage> query = from(postMessage);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		bb.and(postMessage.sender.id.eq(id));
		
		query.where(bb);
		
		Pageable newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
		
		this.getQuerydsl().applyPagination(newPageable, query);
		
		List<PostMessage> list = query.fetch();
		
		Long count = query.fetchCount();
		
		Page<PostMessage> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

	@Override
	public Page<PostMessage> findbyReceiverId(String id, Pageable pageable) {
		
		QPostMessage postMessage = QPostMessage.postMessage;
		
		JPQLQuery<PostMessage> query = from(postMessage);
		
		BooleanBuilder bb = new BooleanBuilder();
		
		bb.and(postMessage.receiver.id.eq(id));
		
		query.where(bb);
		
		Pageable newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
		
		this.getQuerydsl().applyPagination(newPageable, query);
		
		List<PostMessage> list = query.fetch();
		
		Long count = query.fetchCount();
		
		Page<PostMessage> result = new PageImpl<>(list, pageable, count);
		
		return result;
	}

}
