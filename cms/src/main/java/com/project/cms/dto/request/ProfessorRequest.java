package com.project.cms.dto.request;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class ProfessorRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Title cannot be blank") // e.g., "Dr.", "Assoc. Prof."
    private String title;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experience;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+994|0)(50|51|55|70|77|99)\\d{7}$", message = "Phone number must be valid. Example: +994000000000 or 0000000000")
    private String phone;

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
