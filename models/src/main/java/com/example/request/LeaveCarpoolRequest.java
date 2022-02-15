package com.example.request;

import lombok.Data;

@Data
public class LeaveCarpoolRequest {
    private Long userId;
    private Long carpoolId;
}
