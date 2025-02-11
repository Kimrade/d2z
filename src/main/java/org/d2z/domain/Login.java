package org.d2z.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder(toBuilder = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userNo;
	
	// 1 - 엔지니어 , 2 - 사업주 , 0 - 관리자
	@Column(nullable = false)
	private int userDiv;
	
	@Column(nullable = false, unique=true)
	private String id;
	
	@Column(nullable = false)
	private String pw;
	
    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private EngineerUser engineerUser;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private CompanyUser companyUser ;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private AdminUser adminUser;
	
}