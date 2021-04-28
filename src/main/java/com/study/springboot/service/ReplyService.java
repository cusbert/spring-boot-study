package com.study.springboot.service;

import com.study.springboot.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void remove(Long rno);

    void modify(ReplyDTO replyDTO);
}
