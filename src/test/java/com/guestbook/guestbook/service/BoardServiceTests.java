package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("title " + Math.random())
                .content("content " + Math.random())
                .writerId("userID1")
                .build();

        boardService.register(dto);
    }
}
