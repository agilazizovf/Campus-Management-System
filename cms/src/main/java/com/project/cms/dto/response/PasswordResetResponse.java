package com.project.cms.dto.response;

import lombok.Data;

@Data
public class PasswordResetResponse {
	private String email;
	private String token;
	private String newPassword;
}
