package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET(BoardVO board, Model model) throws Exception {

        logger.info("register get ...........");
    }

    // @RequestMapping(value = "/register", method = RequestMethod.POST)
    // public String registPOST(BoardVO board, Model model) throws Exception {
    //
    // logger.info("regist post ...........");
    // logger.info(board.toString());
    //
    // service.regist(board);
    //
    // model.addAttribute("result", "success");
    //
    // //return "/board/success";
    // return "redirect:/board/listAll";
    // }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

        logger.info("regist post ...........");
        logger.info(board.toString());

        service.regist(board);

        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public void listAll(Model model) throws Exception {

        logger.info("show all list......................");
        List<BoardVO> list = service.listAll();
        for(BoardVO vo : list) {
            logger.info("BoardVO : "+vo.toString());
        }
        model.addAttribute("list", list);
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(@RequestParam("bno") int bno, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
    public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno")int bno, RedirectAttributes rttr) throws Exception {
        service.remove(bno);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/removePage", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
        service.remove(bno);

        logger.info("/removePage getPage : "+cri.getPage());
        /*rttr.addFlashAttribute("page", cri.getPage());
        rttr.addFlashAttribute("perPageNum", cri.getPerPageNum());*/
        rttr.addFlashAttribute("cri", cri);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/listPage";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyGET(int bno, Model model) throws Exception {
        model.addAttribute("boardVO", service.read(bno));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
        logger.info("============modify POST===============");
        service.modify(vo);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagePOST(BoardVO vo, Criteria cri, RedirectAttributes rttr) throws Exception {
        service.modify(vo);

        logger.info("/modifyPage Criteria : "+cri.toString());
        rttr.addFlashAttribute("cri", cri);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/listPage";
    }

    @RequestMapping(value = "/listCri", method = RequestMethod.GET)
    public void listAll(Criteria criteria, Model model) throws Exception {
        logger.info("============list Cri===============");
        model.addAttribute("list", service.listCriteria(criteria));
    }

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public void listPage(@ModelAttribute("cri") Criteria criteria, Model model) throws Exception {
        logger.info("Criteria : "+ criteria.toString());
        model.addAttribute("list", service.listCriteria(criteria));
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(criteria);
        pageMaker.setTotalCount(service.listCountCriteria(criteria));

        model.addAttribute("pageMaker", pageMaker);

    }

}
