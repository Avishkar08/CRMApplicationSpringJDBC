package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private int custId;

    private String custName;

    private String custAddress;

    private double custAccountBalance;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Kolkata")
    private Date custDOB;

    private long custContactNumber;

    private long custUID;

    private String custPancard;

    private String custEmailId;

    private String custPassword;
}
