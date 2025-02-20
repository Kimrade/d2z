package org.d2z.controller;

import java.util.List;

import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CareerCalDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.service.AdminUserService;
import org.d2z.service.CompanyUserService;
import org.d2z.service.EngineerUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Log4j2
public class AdminUserController {
	
	private final AdminUserService aus;
	private final CompanyUserService cus;
	private final EngineerUserService eus;
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/main")
	public String adminMainGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("adminMain get 진입 - 관리자 메인 화면 진입");
		
		AdminUserDTO adminUserDTO = aus.findByAdminId(userDetails.getUsername());
		
		model.addAttribute("adminUserDTO", adminUserDTO);
		
		return "/admin/admin";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/memberdelete")
	public void memberdeleteGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		model.addAttribute("companyList", aus.listByCompanyUserDeleted());
		model.addAttribute("engineerList", aus.listByEngineerUserDeleted());
		
	}
	
	@PostMapping("/memberdelete")
	public String memberDeletePost(@RequestParam(value = "engineerCheck", required = false) List<Integer> engineerUserIds, @RequestParam(value = "companyCheck", required = false) List<Integer> companyUserIds, RedirectAttributes ra) {
		
		int result1 = 0;
		int result2 = 0;
		
		if(engineerUserIds != null) {
			aus.deleteAllEngineerUserById(engineerUserIds);
			result1 = 1;
		}
		
		if(companyUserIds != null) {
			aus.deleteAllCompanyUserById(companyUserIds);
			result2 = 1;
		}
		
		if(result1 == 1 && result2 == 1) {
			ra.addFlashAttribute("deleteAlert", "사업주 및 엔지니어 사용자가 탈퇴 되었습니다.");
		}else if(result1 == 1 && result2 == 0) {
			ra.addFlashAttribute("deleteAlert", "엔지니어 사용자가 탈퇴 되었습니다.");
		}else if(result1 == 0 && result2 == 1) {
			ra.addFlashAttribute("deleteAlert", "사업주 사용자가 탈퇴 되었습니다.");
		}else {
			ra.addFlashAttribute("deleteAlert", "탈퇴처리된 사용자가 없습니다.");
		}
		
		return "redirect:/admin/main";
	}
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/findEngineer")
	public void findEngineerGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO, CareerCalDTO careerDTO) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		model.addAttribute("calDTO", careerDTO);
		
		int fromNo = careerDTO.getFromNo();
		int toNo = careerDTO.getToNo();
		
		if(toNo != 0 && toNo >= fromNo) {
			model.addAttribute("pageResponseDTO", eus.engineerUserSearchByKeywordAndCareer(pageRequestDTO, fromNo, toNo));
		}else {
			model.addAttribute("pageResponseDTO", eus.engineerUserSearchByKeyword(pageRequestDTO));
		}
		
	}
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/findCompany")
	public void findCompanyGet(@AuthenticationPrincipal UserDetails userDetails, Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", cus.companyUserSearchByKeyword(pageRequestDTO));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/pendingEngineer")
	public void pendingEngineerGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));

		model.addAttribute("pageResponseDTO", aus.searchPendingEngineerUserByKeyword(pageRequestDTO));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/pendingCompany")
	public void pendingCompanyGet(@AuthenticationPrincipal UserDetails userDetails, Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", aus.searchPendingCompanyUserByKeyword(pageRequestDTO));
	}
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/newCompany")
	public void newCompanyGet() {
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/newEngineer")
	public void newEngineerGet() {
		
	}
	
	
}
