package com.pinde.sci.model.jsres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCertificateCountDto {
    private String certificateName;

    private int number;
}
