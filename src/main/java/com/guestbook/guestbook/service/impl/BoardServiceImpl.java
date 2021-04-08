package com.guestbook.guestbook.service.impl;

import com.guestbook.guestbook.common.GuestbookNotFoundException;
import com.guestbook.guestbook.converter.BoardConverter;
import com.guestbook.guestbook.dto.BoardDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.Member;
import com.guestbook.guestbook.repository.BoardRepository;
import com.guestbook.guestbook.repository.ReplyRepository;
import com.guestbook.guestbook.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;
    private final ReplyRepository replyRepository;
    private final BoardConverter converter;

    @Override
    public BoardDTO register(BoardDTO dto) {
        log.info(dto.toString());
        Board board = converter.dtoToEntity(dto);
        repository.save(board);
        return converter.entityToDTO(board);
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> {
            return converter.entityToDTO((Board) en[0], (Member) en[1], (Long) en[2]);
        });

        /* Page<Object[]> result = repository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("modDate").descending())
        );*/

        Page<Object[]> result = repository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("modDate").descending())
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        return converter.entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        repository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO dto) {

        // findById 대신 로딩지연 방식인 getOne -> @Transactional
        // 필요한 순간까지 로딩지연
        Board board = repository.getOne(dto.getBno());

        if (board != null) {
            board.changeTitle(dto.getTitle());
            board.changeContent(dto.getContent());

            repository.save(board);
        } else {
            throw new GuestbookNotFoundException(dto.getBno());
        }
    }
}
