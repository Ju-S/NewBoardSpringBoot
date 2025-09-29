package com.kedu.newboard.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFileDTO {
    private long id;
    private long boardId;

    // 파일 원본 이름
    private String name;
    // 파일 다운 링크
    private String url;

    private Timestamp createAt;
}
