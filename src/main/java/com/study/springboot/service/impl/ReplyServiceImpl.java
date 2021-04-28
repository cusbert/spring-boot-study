package com.study.springboot.service.impl;

import com.study.springboot.converter.ReplyConverter;
import com.study.springboot.dto.ReplyDTO;
import com.study.springboot.entity.Board;
import com.study.springboot.entity.Reply;
import com.study.springboot.repository.ReplyRepository;
import com.study.springboot.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyConverter replyConverter;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = replyConverter.dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
        return result.stream().map(reply -> replyConverter.entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = replyConverter.dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }
}
