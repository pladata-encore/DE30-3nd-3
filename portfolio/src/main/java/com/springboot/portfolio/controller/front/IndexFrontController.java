package com.springboot.portfolio.controller.front;

import com.springboot.portfolio.data.dto.UserDto;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexFrontController {
    private final UserService userService;

    @Autowired
    private HttpSession httpSession;

    public IndexFrontController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/index")
    public String getUserInfo(Model model) throws Exception {
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        User user = userService.findById(userId);
        // 이미지 파일 경로 설정
        String imgPath = user.getImage() != null ? "/bootstrap/assets/uploads/" + user.getImage() : "";
        user.setImage(imgPath);

        model.addAttribute("user",user);
        return "index";
    }
}
