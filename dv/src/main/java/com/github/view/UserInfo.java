package com.github.view;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {
    private long   userId;
    private String userName;
}
