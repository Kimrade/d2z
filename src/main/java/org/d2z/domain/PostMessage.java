package org.d2z.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class PostMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postNo;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private boolean deletedBySender;
	
	@Column(nullable = false)
	private boolean deletedByReceiver;
	
	@Column(nullable = false)
	@CreatedDate
	private LocalDateTime createdTime;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PostMessageUser sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PostMessageUser receiver;
	
	public void deleteBySender() {
        this.deletedBySender = true;
    }

    public void deleteByReceiver() {
        this.deletedByReceiver = true;
    }

    public boolean isDeleted() {
        return isDeletedBySender() && isDeletedByReceiver();
    }
	
}
