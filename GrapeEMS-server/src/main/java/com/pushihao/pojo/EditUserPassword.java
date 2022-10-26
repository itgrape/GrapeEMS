package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserPassword {
    Long userId;

    String oldPassword;

    String newPassword;
}
