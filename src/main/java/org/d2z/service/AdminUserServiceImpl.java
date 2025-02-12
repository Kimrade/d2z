package org.d2z.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.d2z.domain.AdminUser;
import org.d2z.domain.CompanyUser;
import org.d2z.domain.EngineerUser;
import org.d2z.domain.Login;
import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.dto.PublicAnnouncementDTO;
import org.d2z.repository.AdminUserRepository;
import org.d2z.repository.CompanyUserRepository;
import org.d2z.repository.EngineerUserRepository;
import org.d2z.repository.LoginRepository;
import org.d2z.repository.PublicAnnouncementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService{
	
	private final ModelMapper modelMapper;
	private final EngineerUserRepository eur;
	private final CompanyUserRepository cur;
	private final PublicAnnouncementRepository par;
	private final AdminUserRepository aur;
	private final LoginRepository lr;
	
	
	
	@Override
	@Transactional
	public boolean deleteUser(String id) {
		
		boolean result = false;
		
		if(lr.findById(id).isPresent()) {
			lr.deleteById(lr.findById(id).orElseThrow().getUserNo());
			result = true;
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public int approveUser(String id) {
		
		int result = 0;
		
		if(lr.findById(id).isPresent()){
			if(lr.findById(id).orElseThrow().getUserDiv() == 1) {
				
				Optional<EngineerUser> eu = eur.findByLoginId(id);
				
				eur.save(eu.map(x -> x.toBuilder().isApproved(1).build()).orElseThrow());
				
				result = 1;
				
			}else if(lr.findById(id).orElseThrow().getUserDiv() == 2) {
				
				Optional<CompanyUser> cu = cur.findByLoginId(id);
				
				cur.save(cu.map(x -> x.toBuilder().isApproved(1).build()).orElseThrow());
				
				result = 1;
			}
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public int pendingUser(String id) {
		
		int result = 0;
		
		if(lr.findById(id).isPresent()){
			if(lr.findById(id).orElseThrow().getUserDiv() == 1) {
				
				Optional<EngineerUser> eu = eur.findByLoginId(id);
				
				eur.save(eu.map(x -> x.toBuilder().isApproved(0).build()).orElseThrow());
				
				result = 0;
				
			}else if(lr.findById(id).orElseThrow().getUserDiv() == 2) {
				
				Optional<CompanyUser> cu = cur.findByLoginId(id);
				
				cur.save(cu.map(x -> x.toBuilder().isApproved(0).build()).orElseThrow());
				
				result = 0;
			}
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public int disApprovedUser(String id) {
		
		int result = -1;
		
		if(lr.findById(id).isPresent()){
			if(lr.findById(id).orElseThrow().getUserDiv() == 1) {
				
				Optional<EngineerUser> eu = eur.findByLoginId(id);
				
				eur.save(eu.map(x -> x.toBuilder().isApproved(-1).build()).orElseThrow());
				
				result = -1;
				
			}else if(lr.findById(id).orElseThrow().getUserDiv() == 2) {
				
				Optional<CompanyUser> cu = cur.findByLoginId(id);
				
				cur.save(cu.map(x -> x.toBuilder().isApproved(-1).build()).orElseThrow());
				
				result = -1;
			}
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public boolean deletePublicAnnouncement(int announcementNo) {
		
		boolean result = false;
		
		if(par.findById(announcementNo).isPresent()) {
			par.deleteById(announcementNo);
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean insertAdminUser(LoginDTO loginDTO, AdminUserDTO adminUserDTO) {
		
		boolean result = false;
		
		if(lr.findById(loginDTO.getId()).isEmpty()) {
			
			Login login = Login.builder().userDiv(loginDTO.getUserDiv()).id(loginDTO.getId()).pw(loginDTO.getPw())
					.build();
			
			lr.save(login);
			
			aur.save(AdminUser.builder().adminUserName(adminUserDTO.getAdminUserName())
					.adminUserTel(adminUserDTO.getAdminUserTel()).login(login).build());
			
			result = true;
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean modifyAdminUser(LoginDTO loginDTO, AdminUserDTO adminUserDTO) {
		
		boolean result = false;
		
		if(lr.findById(loginDTO.getId()).isPresent()) {
			
			Login login = lr.findById(loginDTO.getId()).orElseThrow();
			
			login = login.toBuilder().pw(loginDTO.getPw()).build();
			
			lr.save(login);
			
			if(aur.findById(login.getAdminUser().getAdminUserNo()).isPresent()) {
				
				AdminUser adminUser = aur.findById(login.getAdminUser().getAdminUserNo()).orElseThrow();
				
				adminUser = adminUser.toBuilder()
							.adminUserName(adminUserDTO.getAdminUserName())
							.adminUserTel(adminUserDTO.getAdminUserTel())
							.login(login)
							.build();
				
				aur.save(adminUser);
				
				result = true;
			}
		}
		
		return result;
	}

	@Override
	public PageResponseDTO<CompanyUserDTO> searchDisApprovedCompanyUserByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<CompanyUser> list = cur.companyUserDisapprovecSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("companyUserNo"));
		
		List<CompanyUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, CompanyUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<CompanyUserDTO> result = PageResponseDTO.<CompanyUserDTO>f1().dtolist(dtolist).pageRequestDTO(pageRequestDTO).total((int)list.getTotalElements()).build();
		
		return result;
	}

	@Override
	public PageResponseDTO<CompanyUserDTO> searchPendingCompanyUserByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<CompanyUser> list = cur.companyUserPendingSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("companyUserNo"));
		
		List<CompanyUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, CompanyUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<CompanyUserDTO> result = PageResponseDTO.<CompanyUserDTO>f1().dtolist(dtolist).pageRequestDTO(pageRequestDTO).total((int)list.getTotalElements()).build();
		
		return result;
	}

	@Override
	public PageResponseDTO<EngineerUserDTO> searchDisApprovedEngineerUserByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<EngineerUser> list = eur.EngineerUserDisapprovedSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("engineerUserNo"));
		
		List<EngineerUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, EngineerUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<EngineerUserDTO> result = PageResponseDTO.<EngineerUserDTO>f1().dtolist(dtolist).pageRequestDTO(pageRequestDTO).total((int)list.getTotalElements()).build();
		
		return result;
	}

	@Override
	public PageResponseDTO<EngineerUserDTO> searchPendingEngineerUserByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<EngineerUser> list = eur.EngineerUserPendingSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("engineerUserNo"));
		
		List<EngineerUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, EngineerUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<EngineerUserDTO> result = PageResponseDTO.<EngineerUserDTO>f1().dtolist(dtolist).pageRequestDTO(pageRequestDTO).total((int)list.getTotalElements()).build();
		
		return result;
	}
	
	
	
}
