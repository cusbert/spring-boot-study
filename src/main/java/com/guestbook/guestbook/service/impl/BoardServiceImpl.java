package com.guestbook.guestbook.service.impl;

import com.guestbook.guestbook.converter.BoardConverter;
import com.guestbook.guestbook.dto.BoardDTO;
import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.repository.BoardRepository;
import com.guestbook.guestbook.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;
    private final BoardConverter converter;

    @Override
    public BoardDTO register(BoardDTO dto) {
        log.info(dto.toString());

        Board board = converter.dtoToEntity(dto);

        repository.save(board);

        return dto;
    }
}
