package com.project.cms.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FacultyRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;

    @NotBlank(message = "Code is required")
    //@Pattern(regexp = "^[A-Z]{2,5}$", message = "Code must be 2–5 uppercase letters")
    private String code;

    @NotBlank(message = "Dean's name is required")
    @Size(min = 1, max = 100, message = "Dean's name must be between 1 and 100 characters")
    private String dean;

    @NotNull(message = "Established year is required")
    @Min(value = 1800, message = "Established year must be later than 1800")
    @Max(value = 2025, message = "Established year cannot be in the future")
    private Integer establishedYear;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+994|0)(50|51|55|70|77|99)\\d{7}$", message = "Phone number must be valid. Example: +994000000000 or 0000000000")
    private String phone;

    @NotEmpty(message = "Email must not be empty")
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Email must be valid. Example: firstname.lastname@example.com"
    )
    private String contactEmail;
}
