package com.project.cms.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateStudentRequest {

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

    @NotBlank(message = "Student code is required")
    @Pattern(
            regexp = "^B\\.01\\.\\d{3}\\.\\d{2}\\.\\d{3}$",
            message = "Student code must be in the format B.01.XXX.XX.XXX (e.g., B.01.509.22.016)"
    )
    private String studentCode;

    @NotNull(message = "Year is required")
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 6, message = "Year must be at most 6")
    private Integer year;

    @NotBlank(message = "Major is required")
    @Size(min = 2, max = 100, message = "Major must be between 2 and 100 characters")
    private String major;

    @NotEmpty(message = "Email must not be empty")
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Email must be valid. Example: firstname.lastname@example.com"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters")
    private String password;

    private Long facultyId;
}
