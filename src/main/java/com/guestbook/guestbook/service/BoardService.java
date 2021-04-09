package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.BoardDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import org.springframework.data.domain.Page;

public interface BoardService {

    BoardDTO register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    Page<Object[]>  getList2(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO dto);
}
