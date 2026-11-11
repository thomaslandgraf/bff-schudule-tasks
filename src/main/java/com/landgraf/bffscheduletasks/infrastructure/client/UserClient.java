package com.landgraf.bffscheduletasks.infrastructure.client;


import com.landgraf.bffscheduletasks.business.dto.in.AddressDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.PhoneNumberDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.UserDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.UserLoginRequest;
import com.landgraf.bffscheduletasks.business.dto.out.AddressDTOResponse;
import com.landgraf.bffscheduletasks.business.dto.out.PhoneNumberDTOResponse;
import com.landgraf.bffscheduletasks.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user")
    UserDTOResponse getUserByEmail(@RequestParam("email") String email,
                                   @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTORequest);

    @PostMapping("/login")
    String login(@RequestBody UserLoginRequest userLoginRequest);


    @DeleteMapping("/{email}")
    void deleteByEmail(@RequestParam("email") String email,
                       @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTOResponse updateUserData(@RequestBody UserDTORequest userDTORequest,
                                   @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateUserAddress(@RequestBody AddressDTORequest addressDTORequest,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/phone_number")
    PhoneNumberDTOResponse updateUserPhoneNumber(@RequestBody PhoneNumberDTORequest phoneNumberDTORequest,
                                                 @RequestParam("id") Long id,
                                                 @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTOResponse registerAddress(@RequestBody AddressDTORequest addressDTORequest,
                                       @RequestHeader("Authorization") String token);

    @PostMapping("/phone_number")
    PhoneNumberDTOResponse registerPhoneNumber(@RequestBody PhoneNumberDTORequest phoneNumberDTORequest,
                                               @RequestHeader("Authorization") String token);
}
