package org.d2z.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.d2z.dto.CareerCalDTO;
import org.d2z.dto.ChatMessageDTO;
import org.d2z.dto.ChatRoomDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.dto.MatchingRequestDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.ProposalDTO;
import org.d2z.dto.PublicAnnouncementDTO;
import org.d2z.service.ChatService;
import org.d2z.service.CompanyUserService;
import org.d2z.service.ContractService;
import org.d2z.service.EngineerUserService;
import org.d2z.service.MatchingService;
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
	private final ChatService chatS;
	
	private final MatchingService ms;
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/main")
	public String companyMainGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("companyMain get 진입 - 회사 유저 메인 진입");
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("contractList", cs.searchOneByCompanyUser(cus.companyUserInfo(userDetails.getUsername()).getCompanyUserNo()));
		
		List<ChatRoomDTO> chatRooms = chatS.findChatRoomById(userDetails.getUsername());
        
		model.addAttribute("chatRooms", chatRooms);
		
		List<String> engineerUserName = new ArrayList<>();
		
		for(ChatRoomDTO roomDTO : chatRooms) {
			engineerUserName.add(eus.engineerUserInfo(roomDTO.getEngineerUserId()).getEngineerUserName());
		}
		
		List<LocalDateTime> lastMessages = new ArrayList<>();
        
        for (ChatRoomDTO room : chatRooms) {
        	ChatMessageDTO messageDTO = chatS.lastMessageByRoom(room.getRoomNo());
        	if(messageDTO != null) {
        		lastMessages.add(chatS.lastMessageByRoom(room.getRoomNo()).getCreatedTime()); // 채팅방 별 마지막 메시지 저장
        	}else {
        		lastMessages.add(null);
        	}
        }

    	model.addAttribute("lastMessages", lastMessages);
        
        model.addAttribute("roomUserName", engineerUserName);
        
		return "company/company";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/announce")
	public void announceGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("announce get 진입");
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@PostMapping("/announce")
	public String announcePost(@AuthenticationPrincipal UserDetails userDetails, PublicAnnouncementDTO publicAnnouncementDTO, RedirectAttributes ra, Model model, String rec, MatchingRequestDTO matchingRequestDTO) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		if(cus.companyUserInfo(userDetails.getUsername()).getIsApproved() != 1){
			ra.addFlashAttribute("announceInsert", "회원 승인을 받지 못한 상태입니다. 관리자와 상의하여 주시기 바랍니다.");
			
		}else {
			if(pas.publicAnnouncementInsert(publicAnnouncementDTO, cus.companyUserInfo(userDetails.getUsername()))) {
				if(rec.equals("추천")) {
					
					String type = "";
					
					if(matchingRequestDTO.getYearOfCareer() != 0) {
						type = type + "y";
					}
					
					if(publicAnnouncementDTO.getServiceJob() != null) {
						type = type + "j";
					}
					
					if(publicAnnouncementDTO.getServiceDiv() != null) {
						type = type + "d";
					}
					
					matchingRequestDTO.setType(type);
					matchingRequestDTO.setKeyword1(publicAnnouncementDTO.getServiceJob());
					matchingRequestDTO.setKeyword2(publicAnnouncementDTO.getServiceDiv());
					
					model.addAttribute("matchingResponseDTO", ms.recommendationEngineerMatching(matchingRequestDTO));
					model.addAttribute("totalEngineer", ms.recommendationEngineerCount(matchingRequestDTO));
					
					ra.addFlashAttribute("announceInsert", "공고를 성공적으로 등록하였습니다.");
					return "company/recommend";
				}else {	
					ra.addFlashAttribute("announceInsert", "공고를 성공적으로 등록하였습니다.");
				}
			}else {
				ra.addFlashAttribute("announceInsert", "공고 등록에 실패하였습니다. 다시 시도해 주세요");
			}
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
		
		double fromNo = careerDTO.getFromNo();
		double toNo = careerDTO.getToNo();
		
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
		ra.addFlashAttribute("deleteAlert", "탈퇴 신청이 완료되었습니다. 관리자와 연락하시기 바랍니다.");
		return "redirect:/d2z/main";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/companyUserInfo")
	public void companyUserInfo(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra, Model model) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/anotherCompanyUserInfo")
	public void anotherCompanyUserInfoGet(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra, Model model, int companyUserNo) {
	
		model.addAttribute("companyUserDTO", cus.getCompanyUserInfoByNo(companyUserNo));
		
		model.addAttribute("anotherCompanyUserDTO", cus.getCompanyUserInfoByNo(companyUserNo));
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/anotherEngineerUserInfo")
	public void anotherEngineerUserInfoGet(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra, Model model, int engineerUserNo) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("anotherEngineerUserDTO", eus.getEngineerUserInfoByNo(engineerUserNo));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/annInfo")
	public void annInfoGet(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes ra, Model model, int announcementNo) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("publicAnnouncementDTO", pas.publicAnnouncementReadOne(announcementNo));
		
		model.addAttribute("", "");
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/recommend")
	public void recommendGet(@AuthenticationPrincipal UserDetails userDetails, PublicAnnouncementDTO publicAnnouncementDTO, RedirectAttributes ra, Model model, String rec, MatchingRequestDTO matchingRequestDTO) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
	}
	
	@PostMapping("/chatMatching")
	public String matchingChatGet(@AuthenticationPrincipal UserDetails userDetails, Model model,@RequestParam(value = "engineerUserNo", required = false, defaultValue = "0") int engineerUserNo, PublicAnnouncementDTO publicAnnouncementDTO, ProposalDTO proposalDTO) {
		
	    // 로그인한 사용자 정보 가져오기
	    CompanyUserDTO companyUser = cus.companyUserInfo(userDetails.getUsername());
	    
	    if (chatS.findByEngineerUserIdAndCompanyUserId(eus.getEngineerUserInfoByNo(engineerUserNo).getId(), companyUser.getId()) != null) {
	        // 기존 채팅방이 있으면 해당 채팅방으로 리다이렉트
	        return "redirect:/chat/room?roomNo=" + chatS.findByEngineerUserIdAndCompanyUserId(eus.getEngineerUserInfoByNo(engineerUserNo).getId(), companyUser.getId()).getRoomNo();
	    }
	    
	    // 새로운 채팅방 생성
	    Long newRoomNo = chatS.makeChatRoom(eus.getEngineerUserInfoByNo(engineerUserNo).getId(), companyUser.getId());
	    
	    // 생성된 채팅방으로 이동
	    return "redirect:/chat/room?roomNo=" + newRoomNo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/room")
	public String chatRoomGet(@RequestParam Long roomNo, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        
        List<ChatMessageDTO> messages = chatS.listChatRecords(roomNo);

        model.addAttribute("roomNo", roomNo);
        model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
        model.addAttribute("messages", messages);

        return "room"; // chatRoom.html로 이동
    }
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/message")
	public void messageGet() {
		
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/annModify")
	public void annModifyGet(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int announcementNo, Model model) {
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		model.addAttribute("publicAnnouncementDTO", pas.publicAnnouncementReadOne(announcementNo));
	}
	
	@PostMapping("/annModify")
	public String annModifyPost(PublicAnnouncementDTO publicAnnouncementDTO, RedirectAttributes ra) {
		
		if(pas.publicAnnouncementModify(publicAnnouncementDTO)) {
			ra.addFlashAttribute("annModifyAlert", "수정이 완료되었습니다.");
		}else {
			ra.addFlashAttribute("annModifyAlert", "수정을 실패하였습니다. 다시 확인하여 주시기 바랍니다.");
		}
		
		log.info("확인용 : "+publicAnnouncementDTO.getAnnouncementNo());
		
		String link = String.valueOf(publicAnnouncementDTO.getAnnouncementNo());
		
		// annInfo?announcement="+publicAnnouncementDTO.getAnnouncementNo()
		return "redirect:/company/searchAnn";
	}
	
	@PostMapping("/annRemove")
	public String annRemovePost(PublicAnnouncementDTO publicAnnouncementDTO, RedirectAttributes ra) {
		
		if(pas.publicAnnouncementDelete(publicAnnouncementDTO.getAnnouncementNo())) {
			ra.addFlashAttribute("annRemoveAlert", "삭제가 완료되었습니다.");
		}else {
			ra.addFlashAttribute("annRemoveAlert", "삭제를 실패하였습니다. 다시 확인하여 주시기 바랍니다.");
		}
		
		return "redirect:/company/searchAnn";
	}
	
	

}
