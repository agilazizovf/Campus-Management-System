package com.project.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAdminRequest {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    @NotBlank(message = "Surname is required")
    @Size(min = 1, max = 100, message = "Surname must be between 1 and 100 characters")
    private String surname;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+994|0)(50|51|55|70|77|99)\\d{7}$", message = "Phone number must be valid. Example: +994000000000 or 0000000000")
    private String phone;

    private Long facultyId;
}
