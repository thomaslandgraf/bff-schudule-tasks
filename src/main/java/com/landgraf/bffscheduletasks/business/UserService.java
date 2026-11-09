package com.landgraf.bffscheduletasks.business;

import com.landgraf.bffscheduletasks.business.dto.in.AddressDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.PhoneNumberDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.UserDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.UserLoginRequest;
import com.landgraf.bffscheduletasks.business.dto.out.AddressDTOResponse;
import com.landgraf.bffscheduletasks.business.dto.out.PhoneNumberDTOResponse;
import com.landgraf.bffscheduletasks.business.dto.out.UserDTOResponse;
import com.landgraf.bffscheduletasks.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTOResponse saveUser(UserDTORequest userDTORequest){
        return userClient.saveUser(userDTORequest);
    }

    public String login(UserLoginRequest userLoginRequest){
        return userClient.login(userLoginRequest);
    }

    public UserDTOResponse findUserByEmail(String email, String token){
       return userClient.getUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token){
        userClient.deleteByEmail(email, token);
    }

    public UserDTOResponse updateUserData(UserDTORequest userDTORequest, String token){
        return userClient.updateUserData(userDTORequest, token);
    }

    public AddressDTOResponse updateUserAddress(Long addressId, AddressDTORequest addressDTORequest, String token){
        return userClient.updateUserAddress(addressDTORequest,addressId,token);
    }

    public PhoneNumberDTOResponse updateUserPhoneNumber(Long phoneNumberId, PhoneNumberDTORequest phoneNumberDTORequest, String token){
        return userClient.updateUserPhoneNumber(phoneNumberDTORequest, phoneNumberId, token);
    }

    public AddressDTOResponse registerAddress(AddressDTORequest addressDTORequest, String token){
        return userClient.registerAddress(addressDTORequest, token);
    }

    public PhoneNumberDTOResponse registerPhoneNumber(PhoneNumberDTORequest phoneNumberDTORequest, String token){
        return userClient.registerPhoneNumber(phoneNumberDTORequest, token);
    }
}

