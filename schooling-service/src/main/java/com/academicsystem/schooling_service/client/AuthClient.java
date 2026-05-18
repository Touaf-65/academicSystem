package com.academicsystem.schooling_service.client;

import com.academicsystem.schooling_service.dto.auth.CreateAccountRequest;
import com.academicsystem.schooling_service.dto.auth.CreateAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @PostMapping("/auth/internal/create-account")
    CreateAccountResponse createAccount(
            @RequestBody CreateAccountRequest request
    );
}