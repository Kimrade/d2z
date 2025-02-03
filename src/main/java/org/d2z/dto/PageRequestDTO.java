package org.d2z.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
	
	// 페이지 시작점 1페이지로 기본 설정
	@Builder.Default
	private int page = 1;
	
	// 1페이지당 갯수 기본 10개로 설정
	@Builder.Default
	private int size = 10;
	
	// 검색에 대한 정보 => 어떠한 것을 기반으로 검색할지에 따라서 판별 => 조건
	private String type;
	
	// 검색등 조건을 설정할때의 keyword
	private String keyword;
	
	// 공고의 검색을 필요로 하기에 공고 검색시 날짜에 대한 type와 keyword가 필요함.
	
	
	// type로 들어온 조건들을 한글자씩 끉어서 배열로 리턴
	public String[] getTypes() {
		if(type == null || type.isEmpty()) {
			return null;
		}
		return type.split("");
	}
	
	//pageable을 따로 선언하지않고 만들어서 리턴하기 위한 용도
	public Pageable getPageable(String...props) {
		return PageRequest.of(page-1, size, Sort.by(props).descending());
	}
	
	
	
}
