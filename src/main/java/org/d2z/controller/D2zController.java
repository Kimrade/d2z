package org.d2z.controller;

import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.repository.LoginRepository;
import org.d2z.service.CompanyUserService;
import org.d2z.service.ContractService;
import org.d2z.service.EngineerUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/d2z")
public class D2zController {
	
	private final EngineerUserService eus;
	private final CompanyUserService cus;
	private final ContractService cs;
	private final LoginRepository lr;
	private final PasswordEncoder pe;
	
	@PreAuthorize("permitAll")
	@GetMapping({"/","/main"})
	public String mainGet(Model model, Authentication authentication) {
		log.info("main get 진입 - main 화면 조회");
		
		model.addAttribute("engineerCount", eus.totalEngineerCount());
		
		model.addAttribute("companyCount", cus.totalCompanyUserCount());
		
		model.addAttribute("completeCount", cs.totalCountCompletedContract());
		
		model.addAttribute("onGoingCount", cs.totalCountOnGoingContract());
		
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_AdminUser"))) {
            	
                return "redirect:/admin/main";
                
            } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EngineerUser"))) {
            	
                return "redirect:/engineer/main";
                
            } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_CompanyUser"))) {
            	
                return "redirect:/company/main";
                
            }
        }
		
		return "d2z/main";
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/login")
	public void loginGet() {
		log.info("login get 진입 - 로그인 화면 조회");
	}
	
	@GetMapping("/loginError")
	public String loginErrorGet(RedirectAttributes ra) {
		log.info("/loginError get 진입");
		
		ra.addFlashAttribute("loginError", "ID 혹은 PW가 틀립니다.");
		
		return "redirect:/d2z/login";
	}
	
	@PostMapping("/loginError")
	public String loginErrorPost(RedirectAttributes ra) {
		log.info("/loginError post 진입");
		
		ra.addFlashAttribute("loginError", "ID 혹은 PW가 틀립니다.");
		
		return "redirect:/d2z/login";
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/member")
	public void membershipGet() {
		log.info("membership get 진입 - 회원가입 화면 조회");
	}
	
	@PostMapping("/member")
	public String membershipPost(@RequestParam("usertype") String type, LoginUserDTO loginUserDTO, CompanyUserDTO companyUserDTO, EngineerUserDTO engineerUserDTO, RedirectAttributes ra) {
		log.info("membership post 진입 - 회원가입 요청 => 아이디, 비밀번호 정보 + 사업체 사용자 혹은 엔지니어 사용자 정보");
		
		log.info("확인용 : "+type);
		
		if(type != null && type.length() > 0) {
			if(type.equals("사업체")) {
				cus.companyUserInfoInsert(loginUserDTO, companyUserDTO);
				ra.addFlashAttribute("memberConfirmAlert", "사업체 사용자 회원가입이 완료되었습니다.");
				log.info("사업체 완료");
			}else {
				eus.engineerUserInfoInsert(loginUserDTO, engineerUserDTO);
				ra.addFlashAttribute("memberConfirmAlert", "엔지니어 사용자 회원가입이 완료되었습니다.");
				log.info("엔지니어 완료");
			}
		}
		
		return "redirect:/d2z/";
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/informationHistory")
	public void informationHistoryGet() {
		
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/informationManufacturer")
	public void informationManufacturerGet() {
		
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/informationMarket")
	public void informationMarketGet() {
		
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/findid")
	public void findIdGet() {
		
	}
	
	@PostMapping("/findid")
	public String findIdPost(RedirectAttributes ra, @RequestParam("findid") String id, @RequestParam("type")String type,@RequestParam("userEmail") String userEmail,@RequestParam("userTel") String userTel) {
		log.info("findid post 진입");
		
		if(((type != null && type.length() > 0) && type.equals("이메일")) && (userEmail != null && userEmail.length() > 0)) {
			
			log.info("이메일 처리 진입");
			
			if(cus.findCompanyUserByEmail(userEmail) != null  && !cus.findCompanyUserByEmail(userEmail).equals(null)) {
				ra.addFlashAttribute("idAlert", "ID : "+cus.findCompanyUserByEmail(userEmail));
			}else if(eus.findEngineerUserByEmail(userEmail) != null && !eus.findEngineerUserByEmail(userEmail).equals(null)) {
				ra.addFlashAttribute("idAlert", "ID : "+eus.findEngineerUserByEmail(userEmail));
			}else {
				ra.addFlashAttribute("idAlert", "이메일과 일치하는 ID가 없습니다.");
			}
			
		}else if(((type != null && type.length() > 0) && type.equals("전화")) && (userTel != null && userTel.length() > 0)) {
			
			log.info("연락처 처리 진입");
			
			if(cus.findCompanyUserByTelNo(userTel) != null && !cus.findCompanyUserByTelNo(userTel).equals(null)){
				ra.addFlashAttribute("idAlert", "ID : "+cus.findCompanyUserByTelNo(userTel));
			}else if(eus.findEngineerUserByTelNo(userTel) != null && !eus.findEngineerUserByTelNo(userTel).equals(null)) {
				ra.addFlashAttribute("idAlert", "ID : " + eus.findEngineerUserByTelNo(userTel));
			}else {
				ra.addFlashAttribute("idAlert", "연락처와 일치하는 ID가 없습니다.");
			}
			
		}
		
		return "redirect:/d2z/login";
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/findpw")
	public void findPwGet() {
		
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/findPwConfirm")
	public String findPwConfirmGet(@RequestParam("id")String id, @RequestParam("email")String email, Model model, RedirectAttributes ra) {
		
		if((id != null && !id.equals(null)) && (email != null && !email.equals(null))) {
			if(eus.engineerUserInfo(id) != null) {
				if(eus.engineerUserInfo(id).getId().equals(eus.findEngineerUserByEmail(email))) {
					model.addAttribute("id", eus.engineerUserInfo(id).getId());
				}else {
					ra.addFlashAttribute("pwAlert", "id와 email에 일치하는 사용자가 없습니다.");
					return "redirect:/d2z/findpw";
				}
			}else if(cus.companyUserInfo(id) != null) {
				if(cus.companyUserInfo(id).getId().equals(cus.findCompanyUserByEmail(email))) {
					model.addAttribute("id", cus.companyUserInfo(id).getId());
				}else {
					ra.addFlashAttribute("pwAlert", "id와 email에 일치하는 사용자가 없습니다.");
					return "redirect:/d2z/findpw";
				}
			}
		}else {
			ra.addFlashAttribute("pwAlert", "모든 정보를 입력하여주시기 바랍니다.");
			return "redirect:/d2z/findpw";
		}
		
		return "d2z/findPwConfirm";
	}
	
	@PostMapping("/findPwConfirm")
	public String findPwConfirmPost(RedirectAttributes ra, @RequestParam("pw")String pw, @RequestParam("passWordConfirm")String passWordConfirm, @RequestParam("id")String id) {
		
		if(pw.equals(passWordConfirm)) {
			
			lr.updatePasswordById(id, pe.encode(passWordConfirm));
			
			ra.addFlashAttribute("pwAlert", "성공적으로 비밀번호가 변경되었습니다.");
			return "redirect:/d2z/login";
		}else {
			ra.addFlashAttribute("pwAlert", "비밀번호가 일치하지 않습니다.");
			return "redirect:/d2z/findPwConfirm";
		}
	}
	
}
