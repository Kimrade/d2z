package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.CompanyUser;
import org.d2z.domain.PublicAnnouncement;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.dto.PublicAnnouncementDTO;
import org.d2z.repository.CompanyUserRepository;
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
public class PublicAnnouncementServiceImpl implements PublicAnnouncementService {
	
	private final PublicAnnouncementRepository par;
	private final CompanyUserRepository cur;
	private final ModelMapper modelMapper;
	
	@Override
	public boolean publicAnnouncementInsert(PublicAnnouncementDTO publicAnnouncementDTO, CompanyUserDTO companyUserDTO) {
		
		boolean result = false;
		
		if(par.findById(publicAnnouncementDTO.getAnnouncementNo()).isEmpty()) {
			
			PublicAnnouncement publicAnnouncement = PublicAnnouncement.builder()
					.announcementName(publicAnnouncementDTO.getAnnouncementName())
					.announcementDescription(publicAnnouncementDTO.getAnnouncementDescription())
					.deadlineDate(publicAnnouncementDTO.getDeadlineDate())
					.serviceAdd(publicAnnouncementDTO.getServiceAdd())
					.serviceCost(publicAnnouncementDTO.getServiceCost())
					.serviceDiv(publicAnnouncementDTO.getServiceDiv())
					.serviceJob(publicAnnouncementDTO.getServiceJob())
					.serviceJob(publicAnnouncementDTO.getServiceJob())
					.servicePeriod(publicAnnouncementDTO.getServicePeriod())
					.ServicePersonnel(publicAnnouncementDTO.getServicePersonnel())
					.serviceTotalCost(publicAnnouncementDTO.getServiceTotalCost())
					.companyUser(modelMapper.map(companyUserDTO, CompanyUser.class))
					.build();
			
			par.save(publicAnnouncement);
			
			result = true;
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean publicAnnouncementModify(PublicAnnouncementDTO publicAnnouncementDTO) {
		
		boolean result = false;
		
		if(par.findById(publicAnnouncementDTO.getAnnouncementNo()).isPresent()) {
			
			PublicAnnouncement publicAnnouncement = par.findById(publicAnnouncementDTO.getAnnouncementNo()).orElseThrow();
			
			publicAnnouncement = publicAnnouncement.toBuilder()
									.announcementName(publicAnnouncementDTO.getAnnouncementName())
									.announcementDescription(publicAnnouncementDTO.getAnnouncementDescription())
									.deadlineDate(publicAnnouncementDTO.getDeadlineDate())
									.serviceAdd(publicAnnouncementDTO.getServiceAdd())
									.serviceCost(publicAnnouncementDTO.getServiceCost())
									.serviceDiv(publicAnnouncementDTO.getServiceDiv())
									.serviceJob(publicAnnouncementDTO.getServiceJob())
									.servicePeriod(publicAnnouncementDTO.getServicePeriod())
									.ServicePersonnel(publicAnnouncementDTO.getServicePersonnel())
									.serviceTotalCost(publicAnnouncementDTO.getServiceTotalCost()).build();
			
			par.save(publicAnnouncement);
			result = true;
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean publicAnnouncementDelete(int announcementNo) {
		
		boolean result = false;
		
		if(par.findById(announcementNo).isPresent()) {
			par.deleteById(announcementNo);
			result = true;
		}
		
		return result;
	}

	@Override
	public PublicAnnouncementDTO publicAnnouncementReadOne(int announcementNo) {
		
		if(par.findById(announcementNo).isPresent()) {
			
			PublicAnnouncement publicAnnouncement = par.findById(announcementNo).orElseThrow();
			
			PublicAnnouncementDTO publicAnnouncementDTO = PublicAnnouncementDTO.builder()
															.announcementName(publicAnnouncement.getAnnouncementName())
															.announcementDescription(publicAnnouncement.getAnnouncementDescription())
															.announcementNo(announcementNo)
															.companyName(publicAnnouncement.getCompanyUser().getCompanyName())
															.companyUserNo(publicAnnouncement.getCompanyUser().getCompanyUserNo())
															.createdDate(publicAnnouncement.getCreatedDate())
															.deadlineDate(publicAnnouncement.getDeadlineDate())
															.serviceAdd(publicAnnouncement.getServiceAdd())
															.serviceCost(publicAnnouncement.getServiceCost())
															.serviceDiv(publicAnnouncement.getServiceDiv())
															.serviceJob(publicAnnouncement.getServiceJob())
															.servicePeriod(publicAnnouncement.getServicePeriod())
															.ServicePersonnel(publicAnnouncement.getServicePersonnel())
															.serviceTotalCost(publicAnnouncement.getServiceTotalCost()).build();
			
			return publicAnnouncementDTO;
		}
		
		return null;
	}

	@Override
	public PageResponseDTO<PublicAnnouncementDTO> publicAnnouncementSearchByKeyword(PageRequestDTO pageRequestDTO) {
		
		Page<PublicAnnouncement> list = par.publicAnnouncementSearchByKeyword(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable("announcementNo"));
		
		List<PublicAnnouncementDTO> dtolist = list.getContent().stream().map(x -> modelMapper.map(x, PublicAnnouncementDTO.class)).collect(Collectors.toList());
		
		PageResponseDTO<PublicAnnouncementDTO> result = PageResponseDTO.<PublicAnnouncementDTO>f1().dtolist(dtolist).pageRequestDTO(pageRequestDTO).total((int)list.getTotalElements()).build();
		
		
		return result;
	}

}
