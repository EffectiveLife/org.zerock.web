package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by User on 2016-06-03.
 */
@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

    private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

    @Inject
    BoardService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void listPage(@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception {
        logger.info("/list SearchCriteria"+cri.toString());

//        model.addAttribute("list", service.listCriteria(cri));
        model.addAttribute("list", service.listSearchCriteria(cri));

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(service.listSearchCount(cri));

        model.addAttribute("pageMaker", pageMaker);

    }

    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
    public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/removePage", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
        service.remove(bno);

        rttr.addFlashAttribute("cri", cri);
        rttr.addFlashAttribute("msg", "SUCCESSS");

        return "redirect:/sboard/list";
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public void modifyPageGET(int bno, @ModelAttribute("cri") SearchCriteria criteria, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagePOST(BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
        logger.info("/sboard/modifyPage : "+cri.toString());

        service.modify(vo);
        rttr.addFlashAttribute("cri", cri);
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/sboard/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET() throws Exception {
        logger.info("=========register called=============");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
        logger.info("register vo : "+vo.toString());
        service.regist(vo);

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/sboard/list";
    }

    @ResponseBody
    @RequestMapping(value = "/getAttach/{bno}")
    public List<String> getAttach(@PathVariable("bno") Integer bno) throws Exception {
        return service.getAttach(bno);
    }

}
