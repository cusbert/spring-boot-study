package com.study.springboot.controller;

import com.study.springboot.dto.ReplyDTO;
import com.study.springboot.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/board/{bno}/replies")
    public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bno") Long bno) {
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("/board/{bno}/replies")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);
        return new ResponseEntity<>(replyService.register(replyDTO), HttpStatus.OK);
    }

    @DeleteMapping("/board/{bno}/replies/{rno}")
    public ResponseEntity<String> remove(@PathVariable("bno") Long bno,
                                         @PathVariable("rno") Long rno) {
        replyService.remove(rno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/board/{bno}/replies/{rno}")
    public ResponseEntity<String> modify(@PathVariable("bno") Long bno,
                                         @PathVariable("rno") Long rno,
                                         @RequestBody ReplyDTO replyDTO) {
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
