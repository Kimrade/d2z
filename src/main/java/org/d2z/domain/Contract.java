package org.d2z.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
	
	// 사업 명세 고유 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contractNo;
	
	// 사업명
	private String contractName;
	
	// 사업 명세 처리 과정 - (5가지 정도로 처리 나눌 예정)
	@ColumnDefault("0")
	private int processingTask;
	
	// 사업 명세가 처음 생성된 날짜
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;
	
	// 사업 명세 처리가 진행되는 날을 알기위해서 기록
	@LastModifiedDate
	private LocalDateTime modifyDate;
	
	// 엔지니어 사용자의 정보 - 조인용
	@ManyToOne
	private EngineerUser engineerUser;
	
	// 사업주 사용자의 정보 - 조인용
	@ManyToOne
	private CompanyUser companyUser;
	
	public Contract withContractEng(EngineerUser engineerUser) {
		this.engineerUser = engineerUser;
		return this;
	}
	
	public Contract withContractCom(CompanyUser companyUser) {
		this.companyUser = companyUser;
		return this;
	}
	
	
}
