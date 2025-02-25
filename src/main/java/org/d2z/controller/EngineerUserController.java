package org.d2z.controller;

import org.d2z.dto.CareerCalDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.service.CompanyUserService;
import org.d2z.service.ContractService;
import org.d2z.service.EngineerUserService;
import org.d2z.service.PublicAnnouncementService;
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
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/engineer")
public class EngineerUserController {
	
	private final EngineerUserService eus;
	private final CompanyUserService cus;
	private final ContractService cs;
	private final PublicAnnouncementService pas;

	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/main")
	public String engineerGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("contractList", cs.searchOneByEngineerUser(eus.engineerUserInfo(userDetails.getUsername()).getEngineerUserNo()));
		
		return "/engineer/engineer";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/engineeredit")
	public void engineerEditGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
	}
	
	@PostMapping("/delete")
	public String engineerUserCheckDeleted(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra) {
		eus.engineerUserInfoCheckDeleted(userDetails.getUsername());
		ra.addFlashAttribute("deleteAlert", "탈퇴 신천이 완료되었습니다. 관리자와 연락하시기 바랍니다.");
		return "redirect:/d2z/main";
	}
	
	
	@PostMapping("/engineeredit")
	public String engineerEditPost(EngineerUserDTO engineerUserDTO, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra,@RequestParam("pwConfirm")String pwConfirm) {
		
		if(engineerUserDTO.getPw().equals(pwConfirm)) {
			
			LoginUserDTO loginUserDTO = LoginUserDTO.builder().id(userDetails.getUsername())
					.pw(engineerUserDTO.getPw())
					.build();

			if(eus.engineerUserInfoModify(loginUserDTO, engineerUserDTO)) {
				ra.addFlashAttribute("modifyAlert","회원정보가 성공적으로 수정되었습니다.");
				return "redirect:/engineer/main";
			}else {
				ra.addFlashAttribute("modifyAlert", "수정에 실패하였습니다. 다시 진행하여 주시길 바랍니다.");
				return "redirect:/engineer/engineeredit";
			}
			
		}else {
			ra.addFlashAttribute("modifyAlert", "비밀번호와 확인이 일치하지 않습니다. 다시 작성하여 주세요.");
			return "redirect:/engineer/engineeredit";
		}
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/engineerinfo")
	public void engineerinfoGet(@AuthenticationPrincipal UserDetails userDetails, Model model, RedirectAttributes ra, PageRequestDTO pageRequestDTO, CareerCalDTO careerDTO) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		model.addAttribute("calDTO", careerDTO);
		
		int fromNo = careerDTO.getFromNo();
		int toNo = careerDTO.getToNo();
		
		if(toNo != 0 && toNo >= fromNo) {
			model.addAttribute("pageResponseDTO", eus.engineerUserSearchByKeywordAndCareer(pageRequestDTO,fromNo , toNo));
		}else {
			model.addAttribute("pageResponseDTO", eus.engineerUserSearchByKeyword(pageRequestDTO));
		}
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/findCompany")
	public void findCompanyGet(@AuthenticationPrincipal UserDetails userDetails, Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", cus.companyUserSearchByKeyword(pageRequestDTO));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/searchAnn")
	public void searchAnnGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", pas.publicAnnouncementSearchByKeyword(pageRequestDTO));
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/engineerProgress")
	public void engineerProgressGet(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/engineerUserInfo")
	public void engineerUserInfoGet(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/anotherEngineerUserInfo")
	public void anotherEngineerUserInfoGet(@AuthenticationPrincipal UserDetails userDetails,Model model, int engineerUserNo) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("anotherEngineerUserDTO", eus.getEngineerUserInfoByNo(engineerUserNo));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/anotherCompanyUserInfo")
	public void anotherCompanyUserInfoGet(@AuthenticationPrincipal UserDetails userDetails,Model model, int companyUserNo) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("anotherCompanyUserDTO", cus.getCompanyUserInfoByNo(companyUserNo));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/annInfo")
	public void annInfoGet(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra, Model model, int announcementNo) {
		
		model.addAttribute("engineerUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
		
		model.addAttribute("publicAnnouncementDTO", pas.publicAnnouncementReadOne(announcementNo));
	}
	
	
}