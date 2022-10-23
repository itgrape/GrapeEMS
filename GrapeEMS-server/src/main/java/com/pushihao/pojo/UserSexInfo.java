package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSexInfo {
    private Integer menNum;

    private Integer womenNum;

    private Integer other;
}
