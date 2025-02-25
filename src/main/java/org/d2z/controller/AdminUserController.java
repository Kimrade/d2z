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
		
		log.info("확인용 : "+engineerUserIds);
		log.info("확인용 : "+companyUserIds);
		
		if(engineerUserIds != null) {
			if(aus.deleteAllEngineerUserById(engineerUserIds)) {
				result1 = 1;
			}
		}
		
		if(companyUserIds != null) {
			if(aus.deleteAllCompanyUserById(companyUserIds)) {
				result2 = 1;	
			}
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
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		
		model.addAttribute("calDTO", careerDTO);
		
		double fromNo = careerDTO.getFromNo();
		double toNo = careerDTO.getToNo();
		
		if(toNo != 0 && toNo >= fromNo) {
			model.addAttribute("pageResponseDTO", eus.engineerUserSearchByKeywordAndCareer(pageRequestDTO, fromNo, toNo));
		}else {
			model.addAttribute("pageResponseDTO", eus.engineerUserSearchByKeyword(pageRequestDTO));
		}
	}
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/findCompany")
	public void findCompanyGet(@AuthenticationPrincipal UserDetails userDetails, Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", cus.companyUserSearchByKeyword(pageRequestDTO));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/pendingEngineer")
	public void pendingEngineerGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));

		model.addAttribute("pageResponseDTO", aus.searchDisApprovedEngineerUserByKeyword(pageRequestDTO));
		
		
	}
	
	@PostMapping("/pendingEngineer")
	public String pendingEngineerPost(@RequestParam(value = "check", required = false) List<String> engineerUserIds, RedirectAttributes ra) {
		
		if(engineerUserIds != null) {
			for(String id : engineerUserIds) {
				
				aus.approveUser(id);
				
			}
			ra.addFlashAttribute("pendingAlert", "엔지니어 승인을 하였습니다.");
		}
		
		return "redirect:/admin/main";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/pendingCompany")
	public void pendingCompanyGet(@AuthenticationPrincipal UserDetails userDetails, Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", aus.searchDisApprovedCompanyUserByKeyword(pageRequestDTO));
	}
	
	@PostMapping("/pendingCompany")
	public String pendingCompanyPost(RedirectAttributes ra, @RequestParam(value = "check", required = false) List<String> companyUserIds) {
		
		if(companyUserIds != null) {
			for(String id : companyUserIds) {
				aus.approveUser(id);
			}
			ra.addFlashAttribute("pendingAlert", "사업주 승인을 하였습니다.");
		}
		
		return "redirect:/admin/main";
	}
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/newCompany")
	public void newCompanyGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", aus.searchPendingCompanyUserByKeyword(pageRequestDTO));
	}
	
	@PostMapping("/newCompany")
	public String newCompanyPost(@RequestParam(value = "companyApprove", required = false) List<String> companyUserApprovingIds, @RequestParam(value = "companyPending", required = false) List<String> companyUserPendingIds, RedirectAttributes ra) {
		
		boolean b1 = false;
		boolean b2 = false;
		
		log.info("확인용 0 : "+companyUserApprovingIds);
		
		if(companyUserApprovingIds != null) {
			log.info("확인용 1 : "+companyUserApprovingIds);
			for(String id : companyUserApprovingIds) {
				log.info("확인용 2 : "+id);
				aus.approveUser(id);
			}
			b1 = true;
		}
		
		if(companyUserPendingIds != null) {
			for(String id : companyUserPendingIds) {
				aus.disApprovedUser(id);
			}
			b2 = true;
		}
		
		if(b1 || b2) {
			ra.addFlashAttribute("newAlert", "처리가 완료되었습니다.");
		}
		
		return "redirect:/admin/newCompany";
		
	}
	
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/newEngineer")
	public void newEngineerGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", aus.searchPendingEngineerUserByKeyword(pageRequestDTO));
	}
	
	@PostMapping("/newEngineer")
	public String newEngineerPost(@RequestParam(value = "engineerApprove", required = false) List<String> engineerUserApprovingIds, @RequestParam(value = "engineerPending", required = false) List<String> engineerUserPendingIds, RedirectAttributes ra) {
		
		boolean b1 = false;
		boolean b2 = false;
		
		if(engineerUserApprovingIds != null) {
			for(String id : engineerUserApprovingIds) {
				aus.approveUser(id);
			}
			b1 = true;
		}
		
		if(engineerUserPendingIds != null) {
			for(String id : engineerUserPendingIds) {
				aus.disApprovedUser(id);
			}
			b2 = true;
		}
		
		if(b1 || b2) {
			ra.addFlashAttribute("newAlert", "처리가 완료되었습니다.");
		}
		
		return "redirect:/admin/newEngineer";
	}
	
	
	
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/stilOnGoing")
	public void stilOnGoingGet(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		model.addAttribute("adminUserDTO", aus.findByAdminId(userDetails.getUsername()));
		
		
	}
	
	
}
