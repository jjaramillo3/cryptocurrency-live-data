package com.jesse.cryptocurrency.ms.datastream.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Rate {
    private CurrencyInformation usd;
}
