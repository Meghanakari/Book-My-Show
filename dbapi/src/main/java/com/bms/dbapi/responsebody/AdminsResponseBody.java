package com.bms.dbapi.responsebody;

import com.bms.dbapi.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminsResponseBody {
    List<AppUser> admins;
}
