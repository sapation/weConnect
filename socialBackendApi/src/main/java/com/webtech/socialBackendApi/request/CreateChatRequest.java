package com.webtech.socialBackendApi.request;

import com.webtech.socialBackendApi.models.User;
import lombok.Data;

@Data
public class CreateChatRequest {
    private Integer userId;
}
