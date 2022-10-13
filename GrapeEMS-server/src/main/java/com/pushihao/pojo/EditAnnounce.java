package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditAnnounce {
    private Long id;

    private String title;

    private String content;
}
