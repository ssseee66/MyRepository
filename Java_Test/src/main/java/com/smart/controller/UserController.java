package com.smart.controller;

import com.smart.dao.UserDao;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //登录界面
    @RequestMapping("/Login")
    public String login() {
        return "user/Login";
    }
    //管理员添加系统用户（即点击“新增”按钮时)
    @RequestMapping(path = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(User user, HttpServletRequest request) {
        //loginUserId为当前登录用户的用户ID
        int loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        User loginUser = userDao.selectForUserId(loginUserId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/addUser");
        modelAndView.addObject("user", user);
        modelAndView.addObject("loginUser", loginUser);
        return modelAndView;
    }
    //管理员确认添加用户时的请求处理方法
    @RequestMapping(path = "/addUserConfirm", method = RequestMethod.POST)
    public ModelAndView addUserConfirm(User user, HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {
        user.setUserName(new String (
                user.getUserName().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        user.setUser_memo(new String (
                user.getUser_memo().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        user.setPassword(new String(
                user.getPassword().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        userDao.addUser(user);
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userDao.select();
        int loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        User loginUser = userDao.selectForUserId(loginUserId);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('添加用户成功！');</script>");
        out.flush();
        if (loginUser.getUser_type()) {
            modelAndView.setViewName("/user/admin_view");
        } else {
            modelAndView.setViewName("/user/user_view");
        }
        modelAndView.addObject("users", users);
        modelAndView.addObject("loginUser", loginUser);
        return modelAndView;
    }
    @RequestMapping(path = "/comeBack", method = RequestMethod.GET)
    public ModelAndView comeBack(HttpServletRequest request) {
        List<User> users = userDao.select();
        int loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        User loginUser = userDao.selectForUserId(loginUserId);
        ModelAndView modelAndView = new ModelAndView();
        if (loginUser.getUser_type()) {
            modelAndView.setViewName("/user/admin_view");
        } else {
            modelAndView.setViewName("/user/user_view");
        }
        modelAndView.addObject("users", users);
        modelAndView.addObject("loginUser", loginUser);
        return modelAndView;
    }

    //登录界面的请求处理方法
    @RequestMapping(path = "/Login", method = RequestMethod.POST)
    public ModelAndView show_view(User user, HttpServletResponse response) throws IOException {
        //将表单映射对象中为字符串的成员变量字符集更改为UTF-8，避免中文乱码无法与数据库进行用户验证
        user.setUserName(new String (
                user.getUserName().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        user.setPassword(new String(
                user.getPassword().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        List<User> users = userDao.select();
        User loginUser = userDao.select_loginUser(user);
        ModelAndView modelAndView = new ModelAndView();
        //当loginUser为null时即表示数据库中并未查询到此用户
        if (loginUser == null) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script type='text/javascript'>alert('用户名或密码错误');</script>");
            out.flush();
            modelAndView.setViewName("/user/Login");
        } else {     //当数据库中查询到了此用户则根据登录用户的用户类型进行甄别来访问不同的用户界面
            if (loginUser.getUser_type()) {
                modelAndView.setViewName("/user/admin_view");
            } else {
                modelAndView.setViewName("/user/user_view");
            }
            modelAndView.addObject("users", users);
            modelAndView.addObject("loginUser", loginUser);
        }
        return modelAndView;
    }
    //用于处理用户界面中的新增、修改和详细请求，根据请求参数的不同进行不同的处理
    @RequestMapping(path = "/view", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request) {
        //用于甄别用户点击的时哪个按钮的请求参数
        String option = request.getParameter("option");
        int loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        User loginUser = userDao.selectForUserId(loginUserId);
        User user = userDao.selectForUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        switch (option) {
            //修改按钮请求处理
            case "modify":
                modelAndView.setViewName("/user/modify");
                modelAndView.addObject("user", user);
                modelAndView.addObject("loginUser", loginUser);
                break;
            //删除按钮请求处理
            case "delete":
                userDao.deleteUser(userId);
                List<User> users = userDao.select();
                if (loginUser.getUser_type()) {
                    modelAndView.setViewName("/user/admin_view");
                } else {
                    modelAndView.setViewName("/user/user_view");
                }
                modelAndView.addObject("users", users);
                modelAndView.addObject("loginUser", loginUser);
                break;
            //详细按钮请求处理
            case "query":
                modelAndView.setViewName("/user/query");
                modelAndView.addObject("user", user);
                modelAndView.addObject("loginUser", loginUser);
                break;
            default:
                break;
        }
        return modelAndView;
    }
    //修改界面的相应请求处理方法
    @RequestMapping(path = "/modify", method = RequestMethod.POST)
    public ModelAndView modify(User user,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        //更改字符集避免中文乱码
        user.setUserName(new String (
                user.getUserName().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        user.setUser_memo(new String (
                user.getUser_memo().getBytes("iso8859-1"),
                StandardCharsets.UTF_8));
        userDao.modifyUserMessage(user);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('成功修改用户信息！');</script>");
        out.flush();
        List<User> users = userDao.select();
        int loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        User loginUser = userDao.selectForUserId(loginUserId);
        if (loginUser.getUser_type()) {
            modelAndView.setViewName("/user/admin_view");
        } else {
            modelAndView.setViewName("/user/user_view");
        }
        modelAndView.addObject("users", users);
        modelAndView.addObject("loginUser", loginUser);
        return modelAndView;
    }
}
