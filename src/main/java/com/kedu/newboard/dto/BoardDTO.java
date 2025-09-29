package com.kedu.newboard.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private long id;

    private String writer;
    private String title;
    private String contents;

    private long view;
    private Timestamp createAt;
}
