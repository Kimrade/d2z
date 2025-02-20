package org.d2z.controller;

import org.d2z.dto.CareerCalDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PublicAnnouncementDTO;
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
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/company")
public class CompanyUserController {
	
	private final CompanyUserService cus;
	private final PublicAnnouncementService pas;
	private final EngineerUserService eus;
	private final ContractService cs;
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/main")
	public String companyMainGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("companyMain get 진입 - 회사 유저 메인 진입");
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("contractList", cs.searchOneByCompanyUser(cus.companyUserInfo(userDetails.getUsername()).getCompanyUserNo()));
		
		return "/company/company";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/announce")
	public void announceGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("announce get 진입");
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
	}
	
	@PostMapping("/announce")
	public String announcePost(@AuthenticationPrincipal UserDetails userDetails, PublicAnnouncementDTO publicAnnouncementDTO, RedirectAttributes ra, Model model) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		if(pas.publicAnnouncementInsert(publicAnnouncementDTO, cus.companyUserInfo(userDetails.getUsername()))) {
			ra.addFlashAttribute("announceInsert", "공고를 성공적으로 등록하였습니다.");
		}else {
			ra.addFlashAttribute("announceInsert", "공고 등록에 실패하였습니다. 다시 시도해 주세요");
		}
		
		return "redirect:/company/main";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/companyedit")
	public void companyEditGet(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		log.info("companyEdit get 진입");
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
	}
	
	@PostMapping("/companyedit")
	public String companyEditPost(@AuthenticationPrincipal UserDetails userDetails,CompanyUserDTO companyUserDTO, RedirectAttributes ra,@RequestParam("pwConfirm")String pwConfirm) {
		
		if(pwConfirm.equals(companyUserDTO.getPw())) {
			
			LoginUserDTO loginUserDTO = LoginUserDTO.builder()
					.id(userDetails.getUsername())
					.pw(companyUserDTO.getPw())
					.build();

			if(cus.companyUserInfoModify(loginUserDTO, companyUserDTO)) {
				ra.addFlashAttribute("userModifyAlert", "정상적으로 수정되었습니다.");
				return "redirect:/company/main";
			}else {
				ra.addFlashAttribute("userModifyAlert", "수정 실패하였습니다. 다시 확인하여 주시기 바랍니다.");
				return "redirect:/company/companyedit";
			}
			
		}else {
			ra.addFlashAttribute("userModifyAlert", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "redirect:/company/companyedit";
		}
		
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/companyinfo")
	public void companyinfoGet(@AuthenticationPrincipal UserDetails userDetails,Model model, PageRequestDTO pageRequestDTO) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", cus.companyUserSearchByKeyword(pageRequestDTO));
		
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/companyProgress")
	public void companyProgressGet(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
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
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/searchAnn")
	public void searchAnnGet(@AuthenticationPrincipal UserDetails userDetails,Model model,  PageRequestDTO pageRequestDTO) {
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("pageResponseDTO", pas.publicAnnouncementSearchByKeyword(pageRequestDTO));
	}
	
	@PostMapping("/delete")
	public String companyUserCheckDeleted(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra) {
		cus.companyUserInfoCheckDeleted(userDetails.getUsername());
		ra.addFlashAttribute("deleteAlert", "탈퇴 신천이 완료되었습니다. 관리자와 연락하시기 바랍니다.");
		return "redirect:/d2z/main";
	}
	
	

}
