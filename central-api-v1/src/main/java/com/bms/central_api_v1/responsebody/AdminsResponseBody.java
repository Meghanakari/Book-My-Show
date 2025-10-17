package com.bms.central_api_v1.responsebody;

import com.bms.central_api_v1.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminsResponseBody {
    List<AppUser> admins;
}
