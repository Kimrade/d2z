package org.d2z.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResponseDTO<E> {
	
	// PageRequestDTO에서 가져올 2가지
	private int page;
	private int size;
	
	// 전체 글 혹은 공고등 list 값의 갯수(전체 size 값)
	private int total;
	
	// 첫 시작 페이지와 끝 페이지의 값 보통은 1 ~ 10 => 11 ~ 20 등 바뀜
	private int start;
	private int end;
	
	// 이전과 다음으로 11에서 10으로 넘)어올때 혹은 10에서 11로 넘어갈때등의 페이지 start, end 값을 넘어갈때 사용
	private boolean prev;
	private boolean next;
	
	// 검색 혹은 조회를 할때 list 값으로 리턴이 되는 전체 검색 혹은 조회의 값 이것의 갯수가 total
	private List<E> dtolist;
	
	@Builder(builderMethodName = "")
	public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtolist, int total) {
		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();
		
		this.dtolist = dtolist;
		
		this.total = total;
		
		// 시작과 끝 페이지의 차이는 9 => 1 ~ 10 이러한 방식으로 페이지 처리
		this.end = (int)((Math.ceil(this.page/10.0))*10);
		this.start = this.end-9;
		
		int last = (int)(Math.ceil((double)this.total/this.size));
		
		if(this.end > last) {
			this.end = last;
		}
		
		this.prev = this.start > 1;
		
		this.next = this.total > this.end*size;
	}
	
}
