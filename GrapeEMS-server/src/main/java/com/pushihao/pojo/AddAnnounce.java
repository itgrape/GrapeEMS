package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAnnounce {
    private String title;

    private String content;

    private String deptName;
}
