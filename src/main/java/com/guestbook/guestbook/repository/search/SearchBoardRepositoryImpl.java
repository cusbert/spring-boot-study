package com.guestbook.guestbook.repository.search;

import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.QBoard;
import com.guestbook.guestbook.entity.QMember;
import com.guestbook.guestbook.entity.QReply;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search !!!");
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        // JPQL 동작
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));


        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.id, reply.count());
        //.where(board.bno.eq(5L));
        tuple.groupBy(board);

        log.info("========start");
        log.info(tuple);
        log.info("========end");

        // 실제 돌아가는 쿼리 볼 수 있음
        // List<Board> result = jpqlQuery.fetch(); // bno 5로 해도 결과가 5개 나옴
        List<Tuple> result = tuple.fetch();
        log.info(result);

        return null;
    }
}
