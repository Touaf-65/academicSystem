package com.academicsystem.planning_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "schooling-service")
public interface SchoolingClient {

    @GetMapping("/groups/{id}")
    Object getGroupById(
            @PathVariable Long id
    );
}