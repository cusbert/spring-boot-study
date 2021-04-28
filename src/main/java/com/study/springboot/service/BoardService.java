package com.study.springboot.service;

import com.study.springboot.dto.BoardDTO;
import com.study.springboot.dto.PageRequestDTO;
import com.study.springboot.dto.PageResultDTO;
import org.springframework.data.domain.Page;

public interface BoardService {

    BoardDTO register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    Page<Object[]>  getList2(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO dto);
}
