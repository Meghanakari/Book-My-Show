package com.bms.dbapi.responsebody;

import com.bms.dbapi.models.Show;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShowsByHallResposneBody {
    List<Show> shows;
}
