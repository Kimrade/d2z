package org.d2z.domain;


public enum MemberRole {

	EngineerUser, CompanyUser, AdminUser;
		
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
