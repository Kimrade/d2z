package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.Contract;
import org.d2z.dto.ContractDTO;
import org.d2z.repository.ContractRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
	
	private final ModelMapper modelMapper;
	private final ContractRepository cr;
	
	
	@Override
	public void makeContract(ContractDTO contractDTO) {
		cr.save(modelMapper.map(contractDTO, Contract.class));
	}

	@Override
	public boolean deleteContract(int contractNo) {
		
		boolean result = false;
		
		if(cr.findById(contractNo).isPresent()) {
			cr.deleteById(contractNo);
			result = true;
		}
		
		return result;
	}

	@Override
	public ContractDTO readOneContract(int contractNo) {
		return modelMapper.map(cr.findById(contractNo).orElseThrow(), ContractDTO.class);
	}

	@Override
	public List<ContractDTO> searchOneByEngineerUser(int engineerUserNo) {
		return cr.searchByEngineerUserNo(engineerUserNo).stream().map(x -> modelMapper.map(x, ContractDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<ContractDTO> searchOneByCompanyUser(int companyUserNo) {
		return cr.searchBycompanyUserNo(companyUserNo).stream().map(x -> modelMapper.map(x, ContractDTO.class)).collect(Collectors.toList());
	}

}
