package com.study.springboot.repository.search;

import com.study.springboot.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    Board searchOne();

    Page<Object[]> searchPage(String type, String Keyword, Pageable pageable);
}
