package org.zerock.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {

    @Inject
    private BoardDAO dao;

    private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

    @Test
    public void testCreate() throws Exception {

        for(int i=0; i<150; i++) {
            BoardVO board = new BoardVO();
            board.setTitle("[순서"+i+"] 대량의 데이터를 집어 넣습니다.");
            board.setContents(i+" 번째 게시글의 내용 ");
            board.setWriter("user00");
            dao.create(board);
        }
    }

    @Test
    public void testRead() throws Exception {

        logger.info(dao.read(1).toString());
    }

    @Test
    public void testUpdate() throws Exception {

        BoardVO board = new BoardVO();
        board.setBno(1);
        board.setTitle("수정된 글입니다.");
        board.setContents("수정 테스트 ");
        dao.update(board);
    }

    @Test
    public void testDelete() throws Exception {

        dao.delete(1);
    }

    @Test
    public void testListAll() throws Exception {

        logger.info(dao.listAll().toString());

    }

    @Test
    public void testListPage() throws Exception {

        int page = 2;

        List<BoardVO> list = dao.listPage(page);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
        }
    }

    @Test
    public void testListCriteria() throws Exception {
        Criteria criteria = new Criteria();
        criteria.setPage(2);
        criteria.setPerPageNum(10);

        List<BoardVO> list = dao.listCriteria(criteria);
        for(BoardVO vo : list) {
            logger.info(vo.getBno() + ":" + vo.getTitle());
        }

    }

    @Test
    public void testURI() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/board/read")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }

    @Test
    public void testURI2() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/{module}/{page}")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("board", "read")
                .encode();
        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    @Test
    public void testDynamicSearch() throws Exception {
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("날개");
        cri.setSearchType("c");

        logger.info("========================================");
        List<BoardVO> list = dao.listSearch(cri);
        for(BoardVO vo : list) {
            logger.info(vo.getBno()+" : "+vo.getTitle());
        }
        logger.info("========================================");

        logger.info("COUNT : "+dao.listSearchCount(cri));
    }

}
