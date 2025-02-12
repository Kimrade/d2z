package org.d2z.controller;

import java.util.List;

import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PostMessageDTO;
import org.d2z.dto.PostMessageUserDTO;
import org.d2z.repository.PostMessageUserRepository;
import org.d2z.service.PostMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/post")
public class MessageController {
	
	private final PostMessageService pms;
	private final PostMessageUserRepository pmur;
	private final ModelMapper modelMapper;
	
	@PostMapping("/write")
	public PostMessageDTO postMethodName(@RequestBody PostMessageDTO postMessageDTO, @RequestParam("id") String id) {
		
		PostMessageUserDTO pmuDTO = modelMapper.map(pmur.findById(id),PostMessageUserDTO.class);
		
		postMessageDTO.setSenderId(pmuDTO.getId());
		
		return pms.writeMessage(postMessageDTO);
	}
	
    @GetMapping("/messages/received")
    public List<PostMessageDTO> getReceivedMessage(@RequestParam("id") String id, PageRequestDTO pageRequestDTO) {
    	
        PostMessageUserDTO user = modelMapper.map(pmur.findById(id),PostMessageUserDTO.class);

        return pms.receivedMessage(user.getId(), pageRequestDTO);
    }

    @DeleteMapping("/messages/received/{no}")
    public boolean deleteReceivedMessage(@PathVariable("no") Integer no, @RequestParam("id") String id) {
    	
        PostMessageUserDTO user = modelMapper.map(pmur.findById(id), PostMessageUserDTO.class);

        return pms.deleteByReceivedPart(no, user);
    }

    @GetMapping("/messages/sent")
    public List<PostMessageDTO> getSentMessage(@RequestParam("id") String id, PageRequestDTO pageRequestDTO) {
    	
        PostMessageUserDTO user = modelMapper.map(pmur.findById(id),PostMessageUserDTO.class);

        return pms.sentMessage(user.getId(), pageRequestDTO);
    }

    @DeleteMapping("/messages/sent/{no}")
    public boolean deleteSentMessage(@PathVariable("no") Integer no, @RequestParam("id") String id) {
    	
    	PostMessageUserDTO user = modelMapper.map(pmur.findById(id), PostMessageUserDTO.class);

        return pms.deleteBySentPart(no, user);
    }
	
	
	

}
