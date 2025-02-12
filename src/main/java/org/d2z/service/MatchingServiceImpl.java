package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.EngineerUser;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.MatchingRequestDTO;
import org.d2z.dto.MatchingResponseDTO;
import org.d2z.repository.EngineerUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MatchingServiceImpl implements MatchingService {
	
	private final ModelMapper modelMapper;
	private final EngineerUserRepository eur;
	
	
	@Override
	public MatchingResponseDTO<EngineerUserDTO> recommendationEngineerMatching(MatchingRequestDTO matchingRequestDTO) {
		
		Page<EngineerUser> list = eur.matchingEngineerUserSystem(matchingRequestDTO.getTypes(), matchingRequestDTO.getKeyword(), matchingRequestDTO.getPageable());
		
		List<EngineerUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, EngineerUserDTO.class)).collect(Collectors.toList());
		
		MatchingResponseDTO<EngineerUserDTO> result = MatchingResponseDTO.<EngineerUserDTO>f2().dtolist(dtolist).matchingRequestDTO(matchingRequestDTO).total((int)list.getTotalElements()).build();
		
		return result;
	}

}
