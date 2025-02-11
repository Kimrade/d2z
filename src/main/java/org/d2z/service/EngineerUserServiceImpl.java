package org.d2z.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.d2z.domain.EngineerUser;
import org.d2z.domain.Login;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.repository.EngineerUserRepository;
import org.d2z.repository.LoginRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EngineerUserServiceImpl implements EngineerUserService {
	
	private final EngineerUserRepository eur;
	private final LoginRepository lr;
	private final ModelMapper modelMapper;
	
	
	@Override
	public boolean engineerUserInfoInsert(LoginDTO loginDTO, EngineerUserDTO engineerUserDTO) {
		
		boolean result = false;
		
		if(lr.findById(loginDTO.getId()).isEmpty()) {
			
			Login login = Login.builder().userDiv(loginDTO.getUserDiv()).id(loginDTO.getId()).pw(loginDTO.getPw())
					.build();
			
			lr.save(login);
			
			eur.save(EngineerUser.builder().
					engineerUserAdd(engineerUserDTO.getEngineerUserAdd())
					.engineerUserTel(engineerUserDTO.getEngineerUserTel())
					.engineerUserCareer(engineerUserDTO.getEngineerUserCareer())
					.engineerUserMajorCompany(engineerUserDTO.getEngineerUserMajorCompany())
					.engineerUserPosition(engineerUserDTO.getEngineerUserPosition())
					.engineerUserJob(engineerUserDTO.getEngineerUserJob())
					.engineerUserEmail(engineerUserDTO.getEngineerUserEmail())
					.engineerUserNote(engineerUserDTO.getEngineerUserNote())
					.login(login)
					.build());
			
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean engineerUserInfoModify(LoginDTO loginDTO, EngineerUserDTO engineerUserDTO) {
		
		boolean result = false;

	    if (lr.findById(loginDTO.getId()).isPresent()) {
	        Login login = lr.findById(loginDTO.getId()).orElseThrow();
	        login = login.toBuilder()
	                .pw(loginDTO.getPw())
	                .build();
	        lr.save(login);

	        if (eur.findById(login.getEngineerUser().getEngineerUserNo()).isPresent()) {
	            EngineerUser engineerUser = eur.findById(login.getEngineerUser().getEngineerUserNo()).orElseThrow();
	            engineerUser = engineerUser.toBuilder()
	                    .engineerUserAdd(engineerUserDTO.getEngineerUserAdd())
	                    .engineerUserTel(engineerUserDTO.getEngineerUserTel())
	                    .engineerUserCareer(engineerUserDTO.getEngineerUserCareer())
	                    .engineerUserMajorCompany(engineerUserDTO.getEngineerUserMajorCompany())
	                    .engineerUserPosition(engineerUserDTO.getEngineerUserPosition())
	                    .engineerUserJob(engineerUserDTO.getEngineerUserJob())
	                    .engineerUserEmail(engineerUserDTO.getEngineerUserEmail())
	                    .engineerUserNote(engineerUserDTO.getEngineerUserNote())
	                    .engineerUserInfo(engineerUserDTO.getEngineerUserInfo())
	                    .isApproved(engineerUserDTO.getIsApproved())
	                    .isDeleted(engineerUserDTO.getIsDeleted())
	                    .login(login)
	                    .build();

	            eur.save(engineerUser);
	            result = true;
	        }
	    }

	    return result;
	}

	@Override
	public boolean engineerUserInfoDelete(String id) {
		
		boolean result = false;
		
		if(lr.findById(id).isPresent()) {
			
			lr.deleteById(lr.findById(id).orElseThrow().getUserNo());
			
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean engineerUserInfoCheckDeleted(String id) {
		
		boolean result = false;
		
		if(lr.findById(id).isPresent()) {
			
			Optional<EngineerUser> engineerUser = eur.findByLoginId(id);
			
			eur.save(engineerUser.map(x -> x.toBuilder().isDeleted(1).build()).orElseThrow());
			
			result = true;
		}
		
		return result;
	}

	@Override
	public EngineerUserDTO engineerUserInfo(String id) {
		
		if(eur.findByLoginId(id).isPresent()) {
			EngineerUser engineerUser = eur.findByLoginId(id).orElseThrow();
			Login login = lr.findById(id).orElseThrow();
			
			return EngineerUserDTO.builder()
					.engineerUserNo(engineerUser.getEngineerUserNo())
					.engineerUserAdd(engineerUser.getEngineerUserAdd())
					.engineerUserTel(engineerUser.getEngineerUserTel())
					.engineerUserMajorCompany(engineerUser.getEngineerUserMajorCompany())
					.engineerUserPosition(engineerUser.getEngineerUserPosition())
					.engineerUserJob(engineerUser.getEngineerUserJob())
					.engineerUserEmail(engineerUser.getEngineerUserEmail())
					.engineerUserNote(engineerUser.getEngineerUserNote())
					.engineerUserInfo(engineerUser.getEngineerUserInfo())
					.engineerUserCareer(engineerUser.getEngineerUserCareer())
					.isDeleted(engineerUser.getIsDeleted())
					.isApproved(engineerUser.getIsApproved())
					.id(id)
					.pw(login.getPw())
					.userNo(login.getUserNo())
					.userDiv(login.getUserDiv())
					.build();
		}
		
		return null;
	}

	@Override
	public PageResponseDTO<EngineerUserDTO> engineerUserSearchByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<EngineerUser> list = eur.EngineerUserSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("engineerUserNo"));
		
		List<EngineerUserDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, EngineerUserDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<EngineerUserDTO> result = PageResponseDTO.<EngineerUserDTO>f1().pageRequestDTO(pageRequestDTO).dtolist(dtolist).total((int)list.getTotalElements()).build();
		
		return result;
	}

}
