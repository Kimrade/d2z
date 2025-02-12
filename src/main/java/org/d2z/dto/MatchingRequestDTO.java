package org.d2z.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingRequestDTO {
	
	@Builder.Default
	private int page = 1;
	
	@Builder.Default
	private int size = 3;
	
	private String type;
	
	private String keyword;

	
	public String[] getTypes() {
		if(type == null || type.isEmpty()) {
			return null;
		}
		return type.split("");
	}
	
	
	public Pageable getPageable() {
		return PageRequest.of(page-1, size);
	}
	
	
	
}
