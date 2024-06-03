package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.*;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/bootstrap/assets/uploads";

    private final UserService userService;
    private final Map<String, Integer> loginAttempts = new ConcurrentHashMap<>();
    private final Map<String, Long> lockedAccounts = new ConcurrentHashMap<>();

    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final long LOCK_DURATION = 1 * 60 * 1000; // 잠금 지속 시간 (1분)

    private HttpSession regenerateSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // 기존 세션 무효화
        return request.getSession(true); // 새로운 세션 생성하여 반환
    }

    // 로그인 페이지 열람
    @GetMapping("login")
    public String showLoginForm(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "login";
    }

    // 로그인 처리
    @PostMapping("login")
    public String processLogin(HttpServletRequest request, @ModelAttribute("userLoginDto") UserLoginDto userLoginDto, Model model) {
        String userId = userLoginDto.getUser_id();
        HttpSession session = request.getSession();

        // 계정이 잠겼는지 확인
        if (isAccountLocked(userId)) {
            model.addAttribute("loginError", "계정이 잠겼습니다. 1분 뒤에 다시 시도해주세요.");
            return "login";
        }

        User existingUser;
        try {
            existingUser = userService.findById(userId);
        } catch (Exception e) {
            User newUser = userService.createUser(userLoginDto);
            session = regenerateSession(request); // 새로운 세션 생성
            session.setAttribute("logined", newUser);
            return "redirect:/addinfo";
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(userLoginDto.getPassword(), existingUser.getPassword())) {
            resetLoginAttempts(userId); // 로그인 시도 횟수 초기화
            session = regenerateSession(request); // 새로운 세션 생성
            session.setAttribute("logined", existingUser);
            User compareUser = (User) session.getAttribute("logined");
            if (compareUser.getGithub() == null && compareUser.getBlog() == null) {
                return "redirect:/addinfo";
            } else {
                return "redirect:/index";
            }
        } else {
            incrementLoginAttempts(userId); // 로그인 시도 횟수 증가
            if (isAccountLocked(userId)) {
                lockedAccounts.put(userId, System.currentTimeMillis()); // 계정 잠금 시간 기록
            }
            if (loginAttempts.getOrDefault(userId, 0) >= MAX_LOGIN_ATTEMPTS) {
                lockedAccounts.put(userId, System.currentTimeMillis()); // 계정 잠금 시간 기록
                model.addAttribute("loginError", "계정이 잠겼습니다. 잠시 후 다시 시도해주세요.");
            } else {
                model.addAttribute("loginError", "비밀번호가 틀렸습니다. 다시 시도해주세요.");
            }
            return "login";
        }
    }

    // 계정이 잠겼는지 확인
    private boolean isAccountLocked(String userId) {
        return lockedAccounts.containsKey(userId) && System.currentTimeMillis() - lockedAccounts.get(userId) < LOCK_DURATION;
    }

    // 로그인 시도 횟수 증가
    private void incrementLoginAttempts(String userId) {
        loginAttempts.put(userId, loginAttempts.getOrDefault(userId, 0) + 1);
    }

    // 로그인 시도 횟수 초기화
    private void resetLoginAttempts(String userId) {
        loginAttempts.remove(userId);
    }

    // 잠금 해제 메커니즘: 잠금 해제 시간이 지나면 잠긴 상태를 해제함
    @Scheduled(fixedRate = 60000) // 매 분마다 실행 (시간 단위로 조절 가능)
    public void unlockAccounts() {
        long now = System.currentTimeMillis();
        lockedAccounts.entrySet().removeIf(entry -> now - entry.getValue() >= LOCK_DURATION);
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("logined");
        return "redirect:/login";
    }



    @GetMapping("/addinfo")
    public String calladdinfo(Model model, HttpSession session) throws Exception {
        User newUser = (User) session.getAttribute("logined");
        String userId = newUser.getUser_id();
        User userinfo = userService.findById(userId);
        model.addAttribute("userinfo",userinfo);
        return "addinfo";
    }


    @PostMapping(value="/addinfo", consumes = "multipart/form-data")
    public String processAddInfo(@ModelAttribute("userInfo") UserRequestDto userDto, @RequestParam("img") MultipartFile file, HttpSession session, RedirectAttributes attributes) throws Exception {
        User loginedUser = (User) session.getAttribute("logined");
        // 업데이트된 정보로 사용자 정보 업데이트
        loginedUser = userService.updateUserInfo(loginedUser.getUser_id(), userDto.getFull_name(), userDto.getGithub(), userDto.getBlog());

        String fileName = null;
        // Check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            session.setAttribute("logined", loginedUser);
        } else {
            fileName = file.getOriginalFilename();

            // Normalize the file path
            Path path = Paths.get(UPLOAD_DIR, fileName);

            // Save the file on server
            try {
                Files.createDirectories(path.getParent());
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            loginedUser = userService.updateUserImage(loginedUser.getUser_id(), fileName);
            session.setAttribute("logined", loginedUser);
        }

        return "redirect:/index";
    }

    @GetMapping("/needlogin")
    public String needlogin() {
        return "needlogin";
    }
    @GetMapping("/unloginedindex")
    public String index() {
        return "unloginedindex";
    }

    @GetMapping("/")
    public String firstpage() {
        return "unloginedindex";
    }

    @GetMapping("/help")
    public String help() {
        return "fragments/help";
    }

}
