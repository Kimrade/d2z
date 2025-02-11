package org.d2z.config;

import java.util.Optional;

import org.d2z.domain.ChatMessage;
import org.d2z.domain.CompanyUser;
import org.d2z.domain.Contract;
import org.d2z.domain.EngineerUser;
import org.d2z.domain.Proposal;
import org.d2z.dto.ChatMessageDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.ContractDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.ProposalDTO;
import org.d2z.repository.ChatRoomRepository;
import org.d2z.repository.CompanyUserRepository;
import org.d2z.repository.EngineerUserRepository;
import org.d2z.repository.PublicAnnouncementRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
	
	private final ChatRoomRepository crr;
	
	private final EngineerUserRepository eur;
	
	private final PublicAnnouncementRepository par;
	
	private final CompanyUserRepository cur;
	
	public RootConfig(ChatRoomRepository crr,EngineerUserRepository eur,PublicAnnouncementRepository par, CompanyUserRepository cur) {
		this.crr = crr;
		this.eur = eur;
		this.par = par;
		this.cur = cur;
	}
	
	
    @Bean //수동으로 빈등록 @Configuration 활용해서 등록하면 싱글톤보장
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        
        
        modelMapper.typeMap(ChatMessageDTO.class, ChatMessage.class)
    			.addMappings(x -> x.skip(ChatMessage::withChatRoom))
    			.setPostConverter(x -> {
    				ChatMessageDTO source = x.getSource();
    				ChatMessage destination = x.getDestination();
    				
    				Optional.ofNullable(source.getRoomNo())
    					.flatMap(crr :: findById)
    					.ifPresent(destination::withChatRoom);
    				
    				return destination;
        });
        
        modelMapper.typeMap(ChatMessage.class, ChatMessageDTO.class)
		        .addMappings(mapper -> 
		            mapper.map(src -> src.getChatRoom().getRoomNo(), ChatMessageDTO::setRoomNo)
        );
        
        modelMapper.typeMap(ProposalDTO.class, Proposal.class)
				.addMappings(x -> {
				x.skip(Proposal::withProposalEng);
				x.skip(Proposal::withProposalAnn);
				})
				.setPostConverter(x -> {
					ProposalDTO source = x.getSource();
					Proposal destination = x.getDestination();
					
					Optional.ofNullable(source.getEngineerUserNo())
		                .filter(id -> id != 0)
		                .flatMap(eur::findById)
		                .ifPresent(destination::withProposalEng);

					Optional.ofNullable(source.getAnnouncementNo())
		                .filter(id -> id != 0)
		                .flatMap(par::findById)
		                .ifPresent(destination::withProposalAnn);
					
					return destination;
		});
        
        modelMapper.typeMap(Proposal.class, ProposalDTO.class)
		        .addMappings(mapper -> {
		            mapper.map(src -> src.getEngineerUser().getEngineerUserNo(), ProposalDTO::setEngineerUserNo);
		            mapper.map(src -> src.getPublicAnnouncement().getAnnouncementNo(), ProposalDTO::setAnnouncementNo);
        });
        
        modelMapper.typeMap(ContractDTO.class, Contract.class)
				.addMappings(x -> {
				x.skip(Contract::withContractEng);
				x.skip(Contract::withContractCom);
				})
				.setPostConverter(x -> {
					ContractDTO source = x.getSource();
					Contract destination = x.getDestination();
					
					Optional.ofNullable(source.getEngineerUserNo())
		                .filter(id -> id != 0)
		                .flatMap(eur::findById)
		                .ifPresent(destination::withContractEng);
		
					Optional.ofNullable(source.getCompanyUserNo())
		                .filter(id -> id != 0)
		                .flatMap(cur::findById)
		                .ifPresent(destination::withContractCom);
					
					return destination;
		});
        
        modelMapper.typeMap(Contract.class, ContractDTO.class)
	        .addMappings(mapper -> {
	            mapper.map(src -> src.getEngineerUser().getEngineerUserNo(), ContractDTO::setEngineerUserNo);
	            mapper.map(src -> src.getCompanyUser().getCompanyUserNo(), ContractDTO::setCompanyUserNo);
        });
        
        modelMapper.typeMap(EngineerUser.class, EngineerUserDTO.class)
	        .addMappings(mapper -> {
	            mapper.map(src -> src.getLogin().getUserNo(), EngineerUserDTO::setUserNo);
	            mapper.map(src -> src.getLogin().getId(), EngineerUserDTO::setId);
	            mapper.map(src -> src.getLogin().getPw(), EngineerUserDTO::setPw);
	            mapper.map(src -> src.getLogin().getUserDiv(), EngineerUserDTO::setUserDiv);
        });
        
        modelMapper.typeMap(CompanyUser.class, CompanyUserDTO.class)
	        .addMappings(mapper -> {
	            mapper.map(src -> src.getLogin().getUserNo(), CompanyUserDTO::setUserNo);
	            mapper.map(src -> src.getLogin().getId(), CompanyUserDTO::setId);
	            mapper.map(src -> src.getLogin().getPw(), CompanyUserDTO::setPw);
	            mapper.map(src -> src.getLogin().getUserDiv(), CompanyUserDTO::setUserDiv);
        });
        
        
        
        return modelMapper;
    }
	
	
}
