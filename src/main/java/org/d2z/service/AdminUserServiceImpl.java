package org.d2z.service;

import org.d2z.domain.AdminUser;
import org.d2z.domain.CompanyUser;
import org.d2z.domain.EngineerUser;
import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.PublicAnnouncementDTO;
import org.d2z.repository.AdminUserRepository;
import org.d2z.repository.CompanyUserRepository;
import org.d2z.repository.EngineerUserRepository;
import org.d2z.repository.PublicAnnouncementRepository;
import org.modelmapper.ModelMapper;
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
	public boolean deleteEngineerUser(EngineerUserDTO engineerUserDTO) {
		
		boolean result = false;
		
		if(eur.findById(engineerUserDTO.getEngineerUserNo()).isPresent()) {
			eur.deleteById(engineerUserDTO.getEngineerUserNo());
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean deleteCompanyUser(CompanyUserDTO companyUserDTO) {
		
		boolean result = false;
		
		if(cur.findById(companyUserDTO.getCompanyUserNo()).isPresent()) {
			cur.deleteById(companyUserDTO.getCompanyUserNo());
			result = true;
		}
		
		return result;
	}
	
	@Override
	public int approveEngineerUser(EngineerUserDTO engineerUserDTO) {
		
		if(eur.findById(engineerUserDTO.getEngineerUserNo()).isPresent()){
			EngineerUser eu = eur.findById(engineerUserDTO.getEngineerUserNo()).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(1)
					.isDeleted(eu.getIsDeleted()).build());
		}
		
		return eur.findById(engineerUserDTO.getEngineerUserNo()).orElseThrow().getIsApproved();
	}
	
	@Override
	public int approveCompanyUser(CompanyUserDTO companyUserDTO) {
		
		if(cur.findById(companyUserDTO.getCompanyUserNo()).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserDTO.getCompanyUserNo()).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(1).isDeleted(cu.getIsDeleted()).build());
		}
		
		return cur.findById(companyUserDTO.getCompanyUserNo()).orElseThrow().getIsApproved();
	}
	
	@Override
	public int pendingEngineerUser(EngineerUserDTO engineerUserDTO) {
		
		if(eur.findById(engineerUserDTO.getEngineerUserNo()).isPresent()){
			EngineerUser eu = eur.findById(engineerUserDTO.getEngineerUserNo()).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(0)
					.isDeleted(eu.getIsDeleted()).build());
		}
		
		return eur.findById(engineerUserDTO.getEngineerUserNo()).orElseThrow().getIsApproved();
	}
	
	@Override
	public int pendingCompanyUser(CompanyUserDTO companyUserDTO) {
		
		if(cur.findById(companyUserDTO.getCompanyUserNo()).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserDTO.getCompanyUserNo()).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(0).isDeleted(cu.getIsDeleted()).build());
		}
		
		return cur.findById(companyUserDTO.getCompanyUserNo()).orElseThrow().getIsApproved();
	}
	
	@Override
	public int disApprovedEngineerUser(EngineerUserDTO engineerUserDTO) {
		
		if(eur.findById(engineerUserDTO.getEngineerUserNo()).isPresent()){
			EngineerUser eu = eur.findById(engineerUserDTO.getEngineerUserNo()).orElseThrow();
			
			eur.save(EngineerUser.builder().engineerUserNo(eu.getEngineerUserNo()).engineerUserId(eu.getEngineerUserId())
					.engineerUserPw(eu.getEngineerUserPw()).engineerUserBirth(eu.getEngineerUserBirth()).engineerUserAdd(eu.getEngineerUserAdd())
					.engineerUserTel(eu.getEngineerUserTel()).engineerUserCareer(eu.getEngineerUserCareer()).engineerUserMajorCompany(eu.getEngineerUserMajorCompany())
					.engineerUserPosition(eu.getEngineerUserPosition()).engineerUserJob(eu.getEngineerUserJob()).isApproved(-1)
					.isDeleted(eu.getIsDeleted()).build());
		}
		
		return eur.findById(engineerUserDTO.getEngineerUserNo()).orElseThrow().getIsApproved();
	}
	
	@Override
	public int disApprovedCompanyUser(CompanyUserDTO companyUserDTO) {
		
		if(cur.findById(companyUserDTO.getCompanyUserNo()).isPresent()) {
			
			CompanyUser cu = cur.findById(companyUserDTO.getCompanyUserNo()).orElseThrow();
			
			cur.save(CompanyUser.builder().companyUserNo(cu.getCompanyUserNo()).companyUserId(cu.getCompanyUserId())
					.companyUserPw(cu.getCompanyUserPw()).companyNo(cu.getCompanyNo()).companyName(cu.getCompanyName())
					.companyAdd(cu.getCompanyAdd()).companyTel(cu.getCompanyTel()).companyUserFax(cu.getCompanyUserFax())
					.companyUserEmail(cu.getCompanyUserEmail()).companySiteAdd(cu.getCompanySiteAdd()).companyUserName(cu.getCompanyUserName())
					.companyUserTel(cu.getCompanyUserTel()).isApproved(-1).isDeleted(cu.getIsDeleted()).build());
		}
		
		return cur.findById(companyUserDTO.getCompanyUserNo()).orElseThrow().getIsApproved();
	}
	
	@Override
	public boolean deletePublicAnnouncement(PublicAnnouncementDTO publicAnnouncementDTO) {
		
		boolean result = false;
		
		if(par.findById(publicAnnouncementDTO.getAnnouncementNo()).isPresent()) {
			par.deleteById(publicAnnouncementDTO.getAnnouncementNo());
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
	
	
	
}
