package com.study.springboot.service;

import com.study.springboot.dto.BoardDTO;
import com.study.springboot.dto.PageRequestDTO;
import com.study.springboot.dto.PageResultDTO;
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

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

       /* for(BoardDTO dto : result.getDtoList()) {
            System.out.println(dto);
        }*/
    }

    @Test
    public void testGet() {
       Long bno = 3L;
       BoardDTO dto = boardService.get(bno);
       System.out.println(dto);
    }

    @Test
    public void testDelete() {
        Long bno = 2L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify() {
        BoardDTO dto = BoardDTO.builder()
                .bno(3L)
                .title("modify title ..")
                .content("modify content..")
                .build();

        boardService.modify(dto);

        System.out.println(dto);
    }
}
