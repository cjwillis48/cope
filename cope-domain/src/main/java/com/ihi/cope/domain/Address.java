package com.ihi.cope.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private Long id;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String zipCode;

    @NotNull
    private State state;
}