package com.jesse.cryptocurrency.ms.datastream.model;

import lombok.Data;

@Data
public class CurrencyInformation {
    private String name;
    private String unit;
    private String value;
    private String type;
}
