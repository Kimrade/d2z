package org.d2z.domain;


public enum MemberRole {

	AdminUser, EngineerUser, CompanyUser;
		
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
