package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.CompanyUser;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.repository.CompanyUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CompanyUserServiceImpl implements CompanyUserService {
	
	private final ModelMapper modelMapper;
	private final CompanyUserRepository cur;
	
	
	@Override
	public boolean companyUserInfoInsert(CompanyUserDTO companyUserDTO) {
		
		boolean result = false;
		
		if(cur.findById(companyUserDTO.getCompanyUserNo()).isEmpty()) {
			cur.save(modelMapper.map(companyUserDTO, CompanyUser.class));
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean companyUserInfoModify(CompanyUserDTO companyUserDTO) {
		
		boolean result = false;
		
		if(cur.findById(companyUserDTO.getCompanyUserNo()).isPresent()) {
			cur.save(modelMapper.map(companyUserDTO, CompanyUser.class));
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean companyUserInfoDelete(int companyUserNo) {
		
		boolean result = false;
		
		if(cur.findById(companyUserNo).isPresent()) {
			cur.deleteById(companyUserNo);
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean companyUserInfoCheckDeleted(int companyUserNo) {
		
		boolean result = false;
		
		if(cur.findById(companyUserNo).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserNo).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(cu.getIsApproved()).isDeleted(1).build());
			
			result = true;
		}
		
		return result;
	}

	@Override
	public CompanyUserDTO companyUserInfo(int companyUserNo) {
		
		if(cur.findById(companyUserNo).isPresent()) {
			return modelMapper.map(cur.findById(companyUserNo).orElseThrow(), CompanyUserDTO.class);
		}
		
		return null;
	}

	@Override
	public PageResponseDTO<CompanyUserDTO> companyUserSearchByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<CompanyUser> list = cur.companyUserSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("companyUserNo"));
		
		List<CompanyUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, CompanyUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<CompanyUserDTO> result = PageResponseDTO.<CompanyUserDTO>f1().pageRequestDTO(pageRequestDTO).dtolist(dtolist).total((int)list.getTotalElements()).build();
		
		return result;
	}

}
