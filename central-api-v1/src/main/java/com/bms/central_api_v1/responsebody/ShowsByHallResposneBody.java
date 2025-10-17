package com.bms.central_api_v1.responsebody;

import com.bms.central_api_v1.models.Show;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShowsByHallResposneBody {
    List<Show> shows;
}
