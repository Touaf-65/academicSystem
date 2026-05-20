package com.academicsystem.planning_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "infrastructure-service")
public interface InfrastructureClient {

    @GetMapping("/rooms/{id}")
    Object getRoomById(
            @PathVariable Long id
    );
}