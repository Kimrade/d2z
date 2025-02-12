package org.d2z.service;

import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.MatchingRequestDTO;
import org.d2z.dto.MatchingResponseDTO;

public interface MatchingService {
	
	// 추천 매칭 방식 - 여러가지 조건을 설정하여 추천 방식을 선택할 경우 => 각 조건별로 직업군 혹은 사람들을 추려서 랜덤으로 뿌려야함.
	// 이전에 나왔던 사람이 아닌 다른 사람을 추천해주는 방식으로 설정해야함
	// 총 3명씩 선정해서 page 처리를 진행하여 뿌려주면 되는게 기초
	// 검색을 키워드를 통해서 하며, page 처리를 통해 3명씩 엄선하여 추천을 진행, 나왔던 사람은 안나오게 처음 시작시에만 랜덤으로 값을 조정하여 페이지 처리하면 그 이후부터는 페이지가 달라지기에 나오지 않을것
	public MatchingResponseDTO<EngineerUserDTO> recommendationEngineerMatching(MatchingRequestDTO matchingRequestDTO);
	
	
	
	
	
}
