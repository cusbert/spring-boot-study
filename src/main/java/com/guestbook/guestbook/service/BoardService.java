package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.BoardDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;

public interface BoardService {

    BoardDTO register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);
}
