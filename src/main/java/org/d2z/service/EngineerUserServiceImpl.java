package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.EngineerUser;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.repository.EngineerUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EngineerUserServiceImpl implements EngineerUserService {
	
	private final EngineerUserRepository eur;
	private final ModelMapper modelMapper;
	
	
	@Override
	public boolean engineerUserInfoInsert(EngineerUserDTO engineerUserDTO) {
		
		boolean result = false;
		
		if(eur.findById(engineerUserDTO.getEngineerUserNo()).isEmpty()) {
			eur.save(modelMapper.map(engineerUserDTO, EngineerUser.class));
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean engineerUserInfoModify(EngineerUserDTO engineerUserDTO) {
		
		boolean result = false;
		
		if(eur.findById(engineerUserDTO.getEngineerUserNo()).isPresent()) {
			eur.save(modelMapper.map(engineerUserDTO, EngineerUser.class));
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean engineerUserInfoDelete(int engineerUserNo) {
		
		boolean result = false;
		
		if(eur.findById(engineerUserNo).isPresent()) {
			eur.deleteById(engineerUserNo);
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean engineerUserInfoCheckDeleted(int companyUserNo) {
		
		boolean result = false;
		
		if(eur.findById(companyUserNo).isPresent()) {
			EngineerUser eu = eur.findById(companyUserNo).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(eu.getIsApproved())
					.isDeleted(1).build());
			
			result = true;
		}
		
		return result;
	}

	@Override
	public EngineerUserDTO engineerUserInfo(int engineerUserNo) {
		
		if(eur.findById(engineerUserNo).isPresent()) {
			return modelMapper.map(eur.findById(engineerUserNo).orElseThrow(), EngineerUserDTO.class);
		}
		
		return null;
	}

	@Override
	public PageResponseDTO<EngineerUserDTO> engineerUserSearchByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<EngineerUser> list = eur.EngineerUserSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("engineerUserNo"));
		
		List<EngineerUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, EngineerUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<EngineerUserDTO> result = PageResponseDTO.<EngineerUserDTO>f1().pageRequestDTO(pageRequestDTO).dtolist(dtolist).total((int)list.getTotalElements()).build();
		
		return result;
	}

}
