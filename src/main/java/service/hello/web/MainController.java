package service.hello.web;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.hello.Repository.MemberRepository;
import service.hello.domain.Member;

import java.util.List;

@Controller
@RequestMapping("/basic/members")
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "basic/members";
    }

    @GetMapping("/add")
    public String add(){
        return "basic/addForm";
    }

    /**
     * ModelAttribute 자동 바인딩 시킬려면 html의 name태그와 객체가 일치되어야함
     */
    @PostMapping("/add")
    public String postAdd(@ModelAttribute Member member, Model model){
        memberRepository.save(member);
        model.addAttribute("member",member);
        return "redirect:/basic/members";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model){
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/member";
    }


    @GetMapping("/{memberId}/edit")
    public String memberEdit(@PathVariable long memberId, Model model){
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String memberEdit(@PathVariable long memberId, @ModelAttribute Member member){
        memberRepository.updateMember(memberId, member);
        return "redirect:/basic/members";
    }

    @PostConstruct
    public void init(){
        memberRepository.save(new Member("KIM", 21, "SEOUL"));
        memberRepository.save(new Member("LEE", 22, "BUSAN"));
    }

}
