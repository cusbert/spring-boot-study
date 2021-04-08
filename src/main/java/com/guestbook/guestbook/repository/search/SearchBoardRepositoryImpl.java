package com.guestbook.guestbook.repository.search;

import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.QBoard;
import com.guestbook.guestbook.entity.QMember;
import com.guestbook.guestbook.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board searchOne() {
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

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage.....");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        // JPQL 동작
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.id, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0l); // board0_.bno>?

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");
            // 검색 조건
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String searchType : typeArr) {
                switch (searchType) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword)); //   board0_.title like ? escape '!'
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        // Order
        Sort sort = pageable.getSort();
        //tuple.orderBy(board.modDate.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(board);

        // Page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        // 결과 fetch
        List<Tuple> result = tuple.fetch();


        // count
        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count
        );
    }
}
