package org.d2z.config;

import org.d2z.domain.ChatMessage;
import org.d2z.domain.ChatRoom;
import org.d2z.dto.ChatMessageDTO;
import org.d2z.repository.ChatRoomRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
	
	private final ChatRoomRepository crr;
	
	public RootConfig(ChatRoomRepository crr) {
		this.crr = crr;
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
        				
        				if(source.getRoomNo() != null) {
        					ChatRoom chatRoom = crr.findById(source.getRoomNo()).orElseThrow();
        				
        					return destination.withChatRoom(chatRoom);
        				}
        				return destination;
        			});
        
        return modelMapper;
    }
	
	
}
