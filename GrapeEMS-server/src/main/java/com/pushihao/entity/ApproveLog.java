package com.pushihao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproveLog<T> {
    private Long approveLogId;

    private Integer approveLogKind;

    private Long approveLogContentId;

    private T content;
}
