package edu.codespring.cloud.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTelescope {

    @Null
    private String id;

    @NotNull
    private String issuedByUser;

    @NotNull
    private String startDateTime;

    @NotNull
    private String endDateTime;

    @NotNull
    private String lensType;
}
