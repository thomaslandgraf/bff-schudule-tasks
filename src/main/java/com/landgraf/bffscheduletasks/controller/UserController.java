package com.landgraf.bffscheduletasks.controller;

import com.landgraf.bffscheduletasks.business.UserService;
import com.landgraf.bffscheduletasks.business.dto.in.AddressDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.PhoneNumberDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.UserDTORequest;
import com.landgraf.bffscheduletasks.business.dto.in.UserLoginRequest;
import com.landgraf.bffscheduletasks.business.dto.out.AddressDTOResponse;
import com.landgraf.bffscheduletasks.business.dto.out.PhoneNumberDTOResponse;
import com.landgraf.bffscheduletasks.business.dto.out.UserDTOResponse;
import com.landgraf.bffscheduletasks.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User register and user login.")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Save a user", description = "Create a new user.")
    @ApiResponse(responseCode = "200", description = "User saved successfully.")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "400", description = "User already create.")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<UserDTOResponse> saveUser(@RequestBody UserDTORequest userDTORequest){
        return ResponseEntity.ok(userService.saveUser(userDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "User login.")
    @ApiResponse(responseCode = "200", description = "User logged successfully.")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public String login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    @GetMapping
    @Operation(summary = "Get user data by Email", description = "Get user data by email.")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<UserDTOResponse> getUserByEmail(@RequestParam("email") String email,
                                                          @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.findUserByEmail(email, token));
    }

    @DeleteMapping
    @Operation(summary = "Delete user data by Email", description = "delete user data by email.")
    @ApiResponse(responseCode = "200", description = "User deleted")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<Void> deleteByEmail(@RequestParam("email") String email,
                                              @RequestHeader(name = "Authorization", required = false) String token){
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Update user data", description = "Update user data.")
    @ApiResponse(responseCode = "200", description = "User updated")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public  ResponseEntity<UserDTOResponse> updateUserData(@RequestBody UserDTORequest userDTORequest,
                                                           @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateUserData(userDTORequest, token));
    }

    @PutMapping("/address")
    @Operation(summary = "Update user address", description = "update user address by Id.")
    @ApiResponse(responseCode = "200", description = "Address updated")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<AddressDTOResponse> updateUserAddress(@RequestBody AddressDTORequest addressDTORequest,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateUserAddress(id, addressDTORequest, token));
    }

    @PutMapping("/phone_number")
    @Operation(summary = "Update user phone number", description = "update user phone number by Id.")
    @ApiResponse(responseCode = "200", description = "Phone number updated")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "Phone number not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<PhoneNumberDTOResponse> updateUserPhoneNumber(@RequestBody PhoneNumberDTORequest phoneNumberDTORequest,
                                                                        @RequestParam("id") Long id,
                                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateUserPhoneNumber(id, phoneNumberDTORequest, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Save user address", description = "Save user address")
    @ApiResponse(responseCode = "200", description = "Address created successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<AddressDTOResponse> registerAddress(@RequestBody AddressDTORequest addressDTORequest,
                                                              @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.registerAddress(addressDTORequest, token));
    }

    @PostMapping("/phone_number")
    @Operation(summary = "Save user phone number", description = "Save user phone number")
    @ApiResponse(responseCode = "200", description = "Phone number created successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "Phone number not found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<PhoneNumberDTOResponse> registerPhoneNumber(@RequestBody PhoneNumberDTORequest phoneNumberDTORequest,
                                                                      @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.registerPhoneNumber(phoneNumberDTORequest, token));
    }
}
