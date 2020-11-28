package com.ihi.cope.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @NotEmpty
    private String clientId;

    private String softwareTitle;
    private String localizationName;
    private Address contactAddress;
    private String contactPhone;
    private Locality locality;
}
