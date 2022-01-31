package com.example.request;

import lombok.Data;

@Data
public class JoinCarpoolRequest {
    private Long userId;
    private Long carpoolId;
}
