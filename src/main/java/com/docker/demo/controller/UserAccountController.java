package com.docker.demo.controller;


import com.docker.demo.UserDto;
import com.docker.demo.model.SMSResponse;
import com.docker.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("sendOtp/{mobileNo}")
    public ResponseEntity<SMSResponse> registerWithMobile(@PathVariable("mobileNo") String mobileNO) {
        final SMSResponse smsResponse = userAccountService.doSendOtp(mobileNO);
        return new ResponseEntity<>(smsResponse, HttpStatus.OK);
    }


    @GetMapping("verifyOtp/{sessionId}/{otpReceived}/{userId}")
    public UserDto verifyMobileOtp(@PathVariable("sessionId") String sessionId, @PathVariable("otpReceived") String otpReceived, @PathVariable("userId") String userId) {
        final UserDto user = userAccountService.doVerifyOtpMobile(sessionId, otpReceived, userId);
        return user;

    }

    @GetMapping("login/{emailId}")
    public UserDto loginWithEmail(@PathVariable String emailId) {
       return userAccountService.doLoginWithEmail(emailId);
    }


}
