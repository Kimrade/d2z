package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.AdminUser;
import org.d2z.domain.CompanyUser;
import org.d2z.domain.EngineerUser;
import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.dto.PublicAnnouncementDTO;
import org.d2z.repository.AdminUserRepository;
import org.d2z.repository.CompanyUserRepository;
import org.d2z.repository.EngineerUserRepository;
import org.d2z.repository.PublicAnnouncementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
	
	
	
	@Override
	public boolean deleteEngineerUser(int engineerUserNo) {
		
		boolean result = false;
		
		if(eur.findById(engineerUserNo).isPresent()) {
			eur.deleteById(engineerUserNo);
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean deleteCompanyUser(int companyUserNo) {
		
		boolean result = false;
		
		if(cur.findById(companyUserNo).isPresent()) {
			cur.deleteById(companyUserNo);
			result = true;
		}
		
		return result;
	}
	
	@Override
	public int approveEngineerUser(int engineerUserNo) {
		
		if(eur.findById(engineerUserNo).isPresent()){
			EngineerUser eu = eur.findById(engineerUserNo).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(1)
					.isDeleted(eu.getIsDeleted()).build());
		}
		
		return eur.findById(engineerUserNo).orElseThrow().getIsApproved();
	}
	
	@Override
	public int approveCompanyUser(int companyUserNo) {
		
		if(cur.findById(companyUserNo).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserNo).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(1).isDeleted(cu.getIsDeleted()).build());
		}
		
		return cur.findById(companyUserNo).orElseThrow().getIsApproved();
	}
	
	@Override
	public int pendingEngineerUser(int engineerUserNo) {
		
		if(eur.findById(engineerUserNo).isPresent()){
			EngineerUser eu = eur.findById(engineerUserNo).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(0)
					.isDeleted(eu.getIsDeleted()).build());
		}
		
		return eur.findById(engineerUserNo).orElseThrow().getIsApproved();
	}
	
	@Override
	public int pendingCompanyUser(int companyUserNo) {
		
		if(cur.findById(companyUserNo).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserNo).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(0).isDeleted(cu.getIsDeleted()).build());
		}
		
		return cur.findById(companyUserNo).orElseThrow().getIsApproved();
	}
	
	@Override
	public int disApprovedEngineerUser(int engineerUserNo) {
		
		if(eur.findById(engineerUserNo).isPresent()){
			EngineerUser eu = eur.findById(engineerUserNo).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(-1)
					.isDeleted(eu.getIsDeleted()).build());
		}
		
		return eur.findById(engineerUserNo).orElseThrow().getIsApproved();
	}
	
	@Override
	public int disApprovedCompanyUser(int companyUserNo) {
		
		if(cur.findById(companyUserNo).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserNo).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(-1).isDeleted(cu.getIsDeleted()).build());
		}
		
		return cur.findById(companyUserNo).orElseThrow().getIsApproved();
	}
	
	@Override
	public boolean deletePublicAnnouncement(int announcementNo) {
		
		boolean result = false;
		
		if(par.findById(announcementNo).isPresent()) {
			par.deleteById(announcementNo);
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean insertAdminUser(AdminUserDTO adminUserDTO) {
		
		boolean result = false;
		
		if(aur.findById(adminUserDTO.getAdminUserNo()).isEmpty()) {
			aur.save(modelMapper.map(adminUserDTO, AdminUser.class));
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean modifyAdminUser(AdminUserDTO adminUserDTO) {
		
		boolean result = false;
		
		if(aur.findById(adminUserDTO.getAdminUserNo()).isPresent()) {
			aur.save(AdminUser.builder().adminUserNo(adminUserDTO.getAdminUserNo()).adminUserId(adminUserDTO.getAdminUserId())
					.adminUserPw(adminUserDTO.getAdminUserPw()).adminUserName(adminUserDTO.getAdminUserName()).adminUserTel(adminUserDTO.getAdminUserTel()).build()); 
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean deleteAdminUser(int adminUserNo) {
		
		boolean result = false;
		
		if(aur.findById(adminUserNo).isPresent()) {
			aur.deleteById(adminUserNo);
			result = true;
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
