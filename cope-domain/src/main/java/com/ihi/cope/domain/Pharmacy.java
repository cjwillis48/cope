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
public class Pharmacy {
    private Long id;

    @NotEmpty
    private String name;

    @Valid
    @NotNull
    private Address address;

}
