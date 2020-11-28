package com.ihi.cope.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    @NotEmpty
    private String ssn;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Valid
    @NotNull
    private Address address;
}
