package com.docker.demo.service;

import com.docker.demo.UserDto;
import com.docker.demo.model.SMSResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserAccountService {

    SMSResponse doSendOtp(final String mobileNO);

    UserDto doVerifyOtpMobile(String sessionId, final String otp, String userId);

    UserDto doLoginWithEmail(final String emailId);
}
