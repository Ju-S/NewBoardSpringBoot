package com.kedu.newboard.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private long id;

    private String writer;
    private long boardId;

    private String contents;

    private Timestamp createAt;
}
