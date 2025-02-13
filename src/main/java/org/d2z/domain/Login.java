package org.d2z.domain;


import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@ToString(exclude = "userDiv")
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userNo;
	
	// 엔지니어 , 사업주 , 관리자
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private Set<MemberRole> userDiv = new HashSet<>();
	
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
    
    public void addRole(MemberRole memberRole) {
    	this.userDiv.add(memberRole);
    }
    
    public void clearRole() {
    	this.userDiv.clear();
    }
	
}