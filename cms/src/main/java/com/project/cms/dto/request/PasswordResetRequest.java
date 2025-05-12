package com.project.cms.dto.request;

import lombok.Data;

@Data
public class PasswordResetRequest {
	private String email;
	private String token;
	private String newPassword;
}
