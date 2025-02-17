package org.d2z.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.d2z.domain.CompanyUser;
import org.d2z.domain.Login;
import org.d2z.domain.MemberRole;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.repository.CompanyUserRepository;
import org.d2z.repository.LoginRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CompanyUserServiceImpl implements CompanyUserService {
	
	private final ModelMapper modelMapper;
	private final LoginRepository lr;
	private final CompanyUserRepository cur;
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public boolean companyUserInfoInsert(LoginUserDTO loginUserDTO, CompanyUserDTO companyUserDTO) {
		
		boolean result = false;
		
		if(lr.findById(loginUserDTO.getId()).isEmpty()) {
			
			Login login = 
					Login.builder()
						.id(loginUserDTO.getId())
						.pw(passwordEncoder.encode(loginUserDTO.getPw()))
						.build();
			
			login.addRole(MemberRole.CompanyUser);
			
			lr.save(login);
			
			cur.save(CompanyUser.builder()
						.companyUserEmail(companyUserDTO.getCompanyUserEmail())
						.companyNo(companyUserDTO.getCompanyNo())
						.companyName(companyUserDTO.getCompanyName())
						.companyAdd(companyUserDTO.getCompanyAdd())
						.companyTel(companyUserDTO.getCompanyTel())
						.companyUserFax(companyUserDTO.getCompanyUserFax())
						.companySiteAdd(companyUserDTO.getCompanySiteAdd())
						.companyUserName(companyUserDTO.getCompanyUserName())
						.companyUserTel(companyUserDTO.getCompanyUserTel())
						.login(login)
						.build());
			
			result = true;
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean companyUserInfoModify(LoginUserDTO loginUserDTO, CompanyUserDTO companyUserDTO) {
		
		boolean result = false;
		
		if(lr.findById(loginUserDTO.getId()).isPresent()) {
			
			Login login = lr.findById(loginUserDTO.getId()).orElseThrow();
			
			login = login.toBuilder()
						.pw(passwordEncoder.encode(loginUserDTO.getPw()))
						.build();
			
			lr.save(login);
			
			if(cur.findById(login.getCompanyUser().getCompanyUserNo()).isPresent()) {
				CompanyUser companyUser = cur.findById(login.getCompanyUser().getCompanyUserNo()).orElseThrow();

				companyUser = companyUser.toBuilder()
						.companyUserEmail(companyUserDTO.getCompanyUserEmail())
						.companyNo(companyUserDTO.getCompanyNo())
						.companyName(companyUserDTO.getCompanyName())
						.companyAdd(companyUserDTO.getCompanyAdd())
						.companyTel(companyUserDTO.getCompanyTel())
						.companyUserFax(companyUserDTO.getCompanyUserFax())
						.companySiteAdd(companyUserDTO.getCompanySiteAdd())
						.companyUserName(companyUserDTO.getCompanyUserName())
						.companyUserTel(companyUserDTO.getCompanyUserTel())
						.isDeleted(companyUserDTO.getIsDeleted())
						.isApproved(companyUserDTO.getIsApproved())
						.login(login)
						.build();
				
				cur.save(companyUser);
				
				result = true;
			}	
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean companyUserInfoDelete(String id) {
		
		boolean result = false;
		
		if(lr.findById(id).isPresent()) {
			
			lr.deleteById(lr.findById(id).orElseThrow().getUserNo());
			
			result = true;
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean companyUserInfoCheckDeleted(String id) {
		
		boolean result = false;
		
		if(lr.findById(id).isPresent()) {
			
			Optional<CompanyUser> companyUser = cur.findByLoginId(id);
			
			cur.save(companyUser.map(x -> x.toBuilder().isDeleted(1).build()).orElseThrow());
			
			result = true;
		}
		
		return result;
	}

	@Override
	public CompanyUserDTO companyUserInfo(String id) {
		
		if(cur.findByLoginId(id).isPresent()) {
			CompanyUser companyUser = cur.findByLoginId(id).orElseThrow();
			Login login = lr.findById(id).orElseThrow();
			
			return CompanyUserDTO.builder()
					.companyUserNo(companyUser.getCompanyUserNo())
					.companyUserEmail(companyUser.getCompanyUserEmail())
					.companyNo(companyUser.getCompanyNo())
					.companyName(companyUser.getCompanyName())
					.companyAdd(companyUser.getCompanyAdd())
					.companyTel(companyUser.getCompanyTel())
					.companyUserFax(companyUser.getCompanyUserFax())
					.companySiteAdd(companyUser.getCompanySiteAdd())
					.companyUserName(companyUser.getCompanyUserName())
					.companyUserTel(companyUser.getCompanyUserTel())
					.isDeleted(companyUser.getIsDeleted())
					.isApproved(companyUser.getIsApproved())
					.id(id)
					.pw(login.getPw())
					.userNo(login.getUserNo())
					.userDiv(MemberRole.CompanyUser)
					.build();
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
