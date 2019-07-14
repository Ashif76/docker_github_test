package com.docker.demo.service.impl;

import com.docker.demo.UserDto;
import com.docker.demo.entity.User;
import com.docker.demo.model.SMSResponse;
import com.docker.demo.respository.UserRepo;
import com.docker.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mobile.verification.2factor.api.key}")
    private String twoFactorApiKey;

    @Value("${mobile.verification.2factor.send.otp.url}")
    private String url;

    @Autowired
    private UserRepo userRepo;


    @Override
    public SMSResponse doSendOtp(final String mobileNO) {

        String sendOtpUrl = url + twoFactorApiKey + "/SMS/" + mobileNO + "/AUTOGEN";
        final ResponseEntity<SMSResponse> responseEntity = restTemplate.getForEntity(sendOtpUrl, SMSResponse.class);
        return responseEntity.getBody();

    }

    @Override
    public UserDto doVerifyOtpMobile(final String sessionId, final String otp, final String userId) {
        String sendOtpUrl = url + twoFactorApiKey + "/SMS/VERIFY/" + sessionId + "/" + otp;
        SMSResponse responseEntity = null;
        try {
            responseEntity = restTemplate.getForObject(sendOtpUrl, SMSResponse.class);

        } catch (HttpClientErrorException.BadRequest e) {
            responseEntity = new SMSResponse();
            responseEntity.setStatus("Error");
            responseEntity.setSessionId("Mis Match");
            e.printStackTrace();
        }
        final SMSResponse smsResponse = responseEntity;
        return userVerify(smsResponse, userId);

    }


    private UserDto userVerify(SMSResponse smsResponse, final String userId) {
        final String status = smsResponse.getStatus();
        if ("success".equalsIgnoreCase(status)) {
            return userUpdate(userId);
        }
        UserDto userDto = new UserDto();
        userDto.setStatus("Error");
        return userDto;
    }


    private UserDto userUpdate(String userId) {
        UserDto userDto = new UserDto();
        final Optional<User> user = userRepo.findById(userId);
        userDto.setStatus("success");
        User userEntity;
        if (user.isPresent()) {
            userEntity = user.get();
        } else {
            userEntity = new User();
            userEntity.setUserId(userId);
            userEntity.setRedemptionAmount(5);
            userEntity.setRewardPoints(200);
            userRepo.save(userEntity);
        }
        userDto.setContent(userEntity);
        return userDto;
    }


    @Override
    public UserDto doLoginWithEmail(final String emailId) {
       return userUpdate(emailId);
    }
}
