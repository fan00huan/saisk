package cn.itcast.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.po.original.TUser;
import cn.itcast.ssm.service.UserService;
import cn.itcast.ssm.vo.VoUser;

@Controller
public class UserController {

	@Autowired
	private UserService userService;


	// 用户检索一览画面的初期化
	@RequestMapping("/initUserLst")
	public String initUserLst()
			throws Exception {
	    return "userinfo/userList";
	}


    // 用户检索一览画面的检索按钮
    @RequestMapping("/queryUsers")
    public ModelAndView queryItems(@ModelAttribute VoUser voUser,
            HttpServletRequest request,HttpServletResponse response) throws Exception {
        // 测试forward后request是否可以共享

//      System.out.println(request.getParameter("id"));

        // 调用service查找 数据库，查询商品列表
        List<TUser> userLst = userService.findUserLst(voUser.getUsername());
        voUser.setUserLst(userLst);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("voUserPic", voUser);
        // 指定视图
        // 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
        // modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        // 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
        modelAndView.setViewName("userinfo/userList");

        return modelAndView;

    }

}
