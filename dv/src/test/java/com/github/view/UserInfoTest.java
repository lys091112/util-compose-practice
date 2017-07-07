package com.github.view;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class UserInfoTest {

    @Test
    public void testUserInfo() {
        UserInfo userInfo = new UserInfo().setUserId(9L).setUserName("crescent");
        assertThat(userInfo.getUserId(), is(9L));
        assertThat(userInfo.getUserName(), equalTo("crescent"));
    }

}
