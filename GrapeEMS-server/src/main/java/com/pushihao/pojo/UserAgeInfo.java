package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAgeInfo {
    private Integer one;

    private Integer two;

    private Integer three;

    private Integer four;

    private Integer five;

    public void oneAgeAdd() {
        this.one++;
    }

    public void twoAgeAdd() {
        this.two++;
    }

    public void threeAgeAdd() {
        this.three++;
    }

    public void fourAgeAdd() {
        this.four++;
    }

    public void fiveAgeAdd() {
        this.five++;
    }
}
