package com.pinde.sci.model.jsres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSpeCountDto {
    private String speName;

    private int generalNumber;

    private int keyNumber;
}
