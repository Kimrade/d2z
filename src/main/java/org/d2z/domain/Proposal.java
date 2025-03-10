package org.d2z.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "publicAnnouncement")
public class Proposal {
	
	// 제안서 고유 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int proposalNo;
	
	// 제안서 보낸 시기
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime sendProposalDate;
	
	// 엔지니어 사용자 정보 - 조인용
	@ManyToOne(cascade = CascadeType.ALL)
	private EngineerUser engineerUser;
	
	// 공고 게시글 정보 - 조인용
	@ManyToOne(cascade = CascadeType.ALL)
	private PublicAnnouncement publicAnnouncement;
	
	
	 public Proposal withProposalEng(EngineerUser engineerUser) {
        this.engineerUser = engineerUser;
        return this;
    }
	
	public Proposal withProposalAnn(PublicAnnouncement publicAnnouncement) {
        this.publicAnnouncement = publicAnnouncement;
		return this;
    }

	
	
}
