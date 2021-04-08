package com.guestbook.guestbook.repository.search;

import com.guestbook.guestbook.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    Board searchOne();

    Page<Object[]> searchPage(String type, String Keyword, Pageable pageable);
}
