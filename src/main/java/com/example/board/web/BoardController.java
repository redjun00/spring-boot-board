package com.example.board.web;

import com.example.board.domain.Board;
import com.example.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/board")
@SessionAttributes("board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/list")
    public String list(Model model){
        model.addAttribute("boardList", boardService.list());
        return "/board/list";
    }

    @RequestMapping(value = "/read/{seq}")//TODO read를 빼보자.
    public String read(Model model, @PathVariable int seq){
        model.addAttribute("board", boardService.read(seq));
        return "/board/read";
    }

    //글 작성 화면으로 넘겨지는 부분.
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(Model model){
        log.debug("write model=" + model.toString());
        model.addAttribute("board", new Board());
        return "/board/write";
    }

    //글을 작성하고 등록하는 부분.
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@Valid Board board,
                        BindingResult bindingResult,
                        SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            return "/board/write";
        }
        boardService.write(board);
        sessionStatus.setComplete();
        return "redirect:/board/list";
    }

    //TODO 정상동작 안함.. 확인 후 수정 필요.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute Board board){
        log.debug("1 board=" + board.toString());
        return "/board/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute Board board,
                       BindingResult bindingResult,
                       int pwd,
                       SessionStatus sessionStatus,
                       Model model){

        log.debug("2 board=" + board.toString());

        if(bindingResult.hasErrors()){
            return "/board/edit";
        }

        System.out.println("board password=" + board.getPassword() + ", pwd=" + pwd);

        if(board.getPassword() == pwd){
            boardService.edit(board);
            sessionStatus.setComplete();
            return "redirect:/board/list";
        }

        model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
        return "/board/edit";
    }

    @RequestMapping(value = "/delete/{seq}", method = RequestMethod.GET)
    public String delete(@PathVariable int seq, Model model){
        model.addAttribute("seq", seq);
        return "/board/delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int seq, int pwd, Model model){
        Board board = new Board();
        board.setSeq(seq);
        board.setPassword(pwd);

        boolean deleted = boardService.delete(board);

        if(deleted == false){
            model.addAttribute("seq", seq);
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "/board/delete";
        }
        return "redirect:/board/list";
    }
}
