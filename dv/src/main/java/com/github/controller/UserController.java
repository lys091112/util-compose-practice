package com.github.controller;

import com.github.view.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/")
@Slf4j
public class UserController {

    @GetMapping("user")
    public UserInfo getUserInfo() {
        return new UserInfo().setUserId(9L).setUserName("crescent");
    }

    @PostMapping(value = "webhook")
    public String webhookInfo(HttpServletRequest request) throws IOException {
        String jsonStr = IOUtils.toString(request.getInputStream(), "UTF-8");

        log.info("\n webhook info : {}", jsonStr);
        return "success";
    }
}
