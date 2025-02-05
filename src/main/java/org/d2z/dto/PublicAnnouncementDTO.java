package org.d2z.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicAnnouncementDTO {
	
	// 공고 번호 - 고유번호 (어토인크리먼트)
	private int announcementNo;
	
	// 사업 공고 명
	private String announcementName;
	
	// 사업 공고 내용
	private String announcementDescription;
	
	// 근무지
	private String serviceAdd;
	
	// 근무 분야 - 주조,가공등등
	private String serviceJob;
	
	// 근무 구분 - 원결, 방문등
	private String serviceDiv;
	
	// 근무 기간
	private String servicePeriod;
	
	// 월 근무 비용
	private int serviceCost;
	
	// 총 근무 비용
	private int serviceTotalCost;
	
	// 모집 인원
	private int ServicePersonnel;
	
	// 공고 생성 날짜
	private LocalDateTime createdDate;
	
	// 공고 마감 날짜
	private LocalDateTime deadlineDate;
	
	// 조인 - 사업주의 정보
	private int companyUserNo;
}
