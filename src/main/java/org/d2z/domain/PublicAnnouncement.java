package org.d2z.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PublicAnnouncement {
	
	// 공고 고유 번호 - 어토인크리먼트
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int announcementNo;
	
	// 사업 공고 명
	@Column(nullable = false)
	private String announcementName;
	
	// 사업 공고 내용
	@Column(length=2000,nullable = false)
	private String announcementDescription;
	
	// 근무지
	@Column(nullable = false)
	private String serviceAdd;
	
	// 근무 분야 - 주조,가공등등
	@Column(nullable = false)
	private String serviceJob;
	
	// 근무 구분 - 원결, 방문등
	@Column(nullable = false)
	private String serviceDiv;
	
	// 근무 기간
	@Column(nullable = false)
	private String servicePeriod;
	
	// 월 근무 비용
	@ColumnDefault("0")
	private int serviceCost;
	
	// 총 근무 비용
	@ColumnDefault("0")
	private int serviceTotalCost;
	
	// 모집 인원
	@ColumnDefault("0")
	private int ServicePersonnel;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;
	
	@Column(nullable = true)
	private LocalDateTime deadlineDate;
	
}