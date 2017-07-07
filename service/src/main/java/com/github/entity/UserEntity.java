package com.github.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * user basic info
 */
@Data
@Accessors(chain = true)
public class UserEntity {
    private Long   userId;
    private String userName;
    private int    age;
}
