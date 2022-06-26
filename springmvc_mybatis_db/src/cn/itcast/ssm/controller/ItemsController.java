package cn.itcast.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.ssm.controller.validation.ValidGroup1;
import cn.itcast.ssm.po.original.TItem;
import cn.itcast.ssm.service.ItemService;
import cn.itcast.ssm.vo.AAA;
import cn.itcast.ssm.vo.VoItem;

/**
 *
 * <p>
 * Title: ItemsController
 * </p>
 * <p>
 * Description:商品的controller
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 *
 * @author 传智.燕青
 * @date 2015-4-13下午4:03:35
 * @version 1.0
 */
@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/items")

public class ItemsController {

	@Autowired
	private ItemService itemsService;

	// 商品分类
	//itemtypes表示最终将方法返回值放在request中的key
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {

		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("", "");

		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}

	/**
	 * 测试Controller 返回JAVA对象 ModelAndView
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/initItems")
	public ModelAndView initItems() throws Exception {

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", null);

		// 指定视图
		// 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
		// modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		// 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
		modelAndView.setViewName("items/itemsList");

		return modelAndView;

	}
	// 商品查询
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(@ModelAttribute VoItem voItem,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 测试forward后request是否可以共享

//		System.out.println(request.getParameter("id"));

		// 调用service查找 数据库，查询商品列表
		List<TItem> itemLst = itemsService.findItemList(voItem);
		voItem.setItemLst(itemLst);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("voItemPic", voItem);
		// 指定视图
		// 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
		// modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		// 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
		modelAndView.setViewName("items/itemsList");

		return modelAndView;

	}


	/**
	 * 试Controller 返回String JSP画面
	 *
	 * @param model
	 * @param items_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addItem", method = { RequestMethod.POST, RequestMethod.GET })
	// @RequestParam里边指定request传入参数名称和形参进行绑定。
	// 通过required属性指定参数是否必须要传入
	// 通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参绑定。
	public String addItem(Model model,HttpSession session)
			throws Exception {

		// 通过形参中的model将model数据传到页面
		// 相当于modelAndView.addObject方法

		VoItem voItem = new VoItem();

		voItem.setPicMode("insert_mode");

		model.addAttribute("voItem", voItem);

		return "items/editItems";
	}

	/**
	 * 试Controller 返回String JSP画面
	 *
	 * @param model
	 * @param items_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editItem", method = { RequestMethod.POST, RequestMethod.GET })
	// @RequestParam里边指定request传入参数名称和形参进行绑定。
	// 通过required属性指定参数是否必须要传入
	// 通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参绑定。
	public String editItem(Model model, @RequestParam(value = "id", required = true,defaultValue="1") String itemId, HttpSession session)
			throws Exception {

		// 调用service根据商品id查询商品信息
		TItem item = itemsService.findItemsById(itemId);

		VoItem voItem = new VoItem();
		BeanUtils.copyProperties(voItem,item);

		// 通过形参中的model将model数据传到页面
		// 相当于modelAndView.addObject方法
		model.addAttribute("voItem", voItem);

		if (session.getAttribute("num")==null) {
			session.setAttribute("num", "1");
		} else {
			session.setAttribute("num", Integer.valueOf(session.getAttribute("num").toString())+1);
		}

		return "items/editItems";
	}



	/**
	 *
	 * 自定义日期类型绑定
	 * @param request
	 * @param id
	 * @param itemsCustom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addItemsSubmit")
	public String addItemsSubmit(
			Model model,
			HttpServletRequest request,
			@Validated(value = { ValidGroup1.class }) VoItem voItem,//@ModelAttribute("aaaaitems")
			BindingResult bindingResult,
			MultipartFile multiItemPic//接收商品图片
			) throws Exception {

		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			// 将错误信息传到页面
			model.addAttribute("allErrors", allErrors);

			//可以直接使用model将提交pojo回显到页面
			model.addAttribute("items", voItem);

			// 出错重新到商品修改页面
			return "items/editItems";
		}
		//原始名称
		String originalFilename = multiItemPic.getOriginalFilename();
		//上传图片
		if(multiItemPic!=null && originalFilename!=null && originalFilename.length()>0){

			//存储图片的物理路径
			//String pic_path = "C:\\IDE\\wsp\\workspace_test\\file_servers\\upload\\temp\\";
			//String pic_path = "D:\\IDE\\FILE_SERVER\\upload\\tmp\\";

			String pic_path = "C:\\IDE\\file\\";

//			String pic_path = "C:\\souka\\upload\\temp\\";

			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);

			//将内存中的数据写入磁盘
			multiItemPic.transferTo(newFile);

			//将新图片名称写到itemsCustom中
			voItem.setItemPic(newFileName);

		}
		voItem.setDeleteFlg("0");
		// 调用service增加商品信息，页面需要将商品信息传到此方法
		itemsService.insertItem(voItem);

		// 重定向到商品查询列表
		return "redirect:queryItems.action";
		// 页面转发
		// return "forward:queryItems.action";
		//return "success";
	}

	/**
	 *
	 * 自定义日期类型绑定
	 * @param request
	 * @param id
	 * @param itemsCustom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(
			Model model,
			HttpServletRequest request,
			@Validated(value = { ValidGroup1.class }) VoItem voItem,//@ModelAttribute("aaaaitems")
			BindingResult bindingResult,
			MultipartFile multiItemPic//接收商品图片
			) throws Exception {

		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			// 将错误信息传到页面
			model.addAttribute("allErrors", allErrors);

			//可以直接使用model将提交pojo回显到页面
			model.addAttribute("items", voItem);

			// 出错重新到商品修改页面
			return "items/editItems";
		}
		//原始名称
		String originalFilename = multiItemPic.getOriginalFilename();
		//上传图片
		if(multiItemPic!=null && originalFilename!=null && originalFilename.length()>0){

			//存储图片的物理路径
			//String pic_path = "C:\\IDE\\wsp\\workspace_test\\file_servers\\upload\\temp\\";
			//String pic_path = "D:\\IDE\\FILE_SERVER\\upload\\tmp\\";

			String pic_path = "C:\\IDE\\file\\";

//			String pic_path = "C:\\souka\\upload\\temp\\";

			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);

			//将内存中的数据写入磁盘
			multiItemPic.transferTo(newFile);

			//将新图片名称写到itemsCustom中
			voItem.setItemPic(newFileName);

		}

		// 调用service更新商品信息，页面需要将商品信息传到此方法
		itemsService.updateItems(voItem);

		// 重定向到商品查询列表
		return "redirect:queryItems.action";
		// 页面转发
		// return "forward:queryItems.action";
		//return "success";
	}

	/**
	 * 测试Controller 返回JAVA对象 ModelAndView
	 *
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/editItems")
	// 限制http请求方法，可以post和get
	@RequestMapping(value = "/editItemsRetMV", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView editItemsRetMV() throws Exception {

		// 调用service根据商品id查询商品信息
		TItem item = itemsService.findItemsById("1");

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// 将商品信息放到model
		modelAndView.addObject("voItem", item);

		// 商品修改页面
		modelAndView.setViewName("items/editItems");

		return modelAndView;
	}

	@RequestMapping(value = "/editItemsRetV", method = { RequestMethod.POST, RequestMethod.GET })
	public String editItemsRetV(Model model) throws Exception {

		// 调用service根据商品id查询商品信息
		TItem item = itemsService.findItemsById("1");

		// 通过形参中的model将model数据传到页面
		// 相当于modelAndView.addObject方法
		model.addAttribute("voItem", item);

		return "items/editItems";
	}

	// 商品信息修改提交
	/**
	 * 测试Controller 返回String 重定向
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editItemsRetRedirect")
	public String editItemsRetRedirect() throws Exception {

		// 调用service更新商品信息，页面需要将商品信息传到此方法
		// ............s

		// 重定向到商品查询列表
		return "redirect:queryItems.action";
	}

	/**
	 * 测试Controller 返回String 定向
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editItemsRetForward")
	public String editItemsRetForward() throws Exception {

		// 调用service更新商品信息，页面需要将商品信息传到此方法
		// ............s

		// 定向到商品查询列表
		return "forward:queryItems.action";
	}

	/**
	 * 测试Controller 返回JSON
	 *
	 */
	@RequestMapping("/testJson01")
	@ResponseBody
	public Map<String, Object> testJson01(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", true);
		map.put("message", "controller->testJson01->" + request.getParameter("name") + request.getParameter("age"));
		return map;
	}

	/**
	 * 测试Controller 返回VOID 定向
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/testVoidForward")
	public void testVoidForward(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("queryItems.action").forward(request, response);
	}

	/**
	 * 测试Controller 返回VOID 重定向
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/testVoidRedirect")
	public void testVoidRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect("queryItems.action");
	}
	/**
	 * 测试Controller 返回VOID 输出JSON
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/testVoidJson")
	public void testVoidJson(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {


		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", true);
		map.put("message", "controller->testJson01->testVoidJson"+ request.getParameter("name") + request.getParameter("age") + "--"+Integer.valueOf(session.getAttribute("num").toString()));
		String json = mapper.writeValueAsString(map);
		System.out.println(json);

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}
	//////////////////////////////////////////////////////////////////////////////////


	//@RequestBody List<ForListReceive> list
	// 批量删除 商品信息
	@RequestMapping("/deleteItems")
//	public String deleteItems(Integer[] items_id) throws Exception {
	public String deleteItems(@ModelAttribute VoItem voItem, @ModelAttribute AAA a,@RequestParam String[] items_id,HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 从JSP传到JAVA程序有三种方法
		// 1。传参数（JSP的NAME和参数的名称要一致）
		// 2。通过request取值
		// 3。通过BEAN 实现GET，SET方法取值  （推荐使用）

		 String[]  itemIds=items_id;//a.getItems_id(); //request.getParameterValues("items_id");
		// 调用service批量删除商品
		// ...
		 itemsService.deleteItems(itemIds);
		// 定向到商品查询列表
		return "forward:queryItems.action";

	}

	// 批量修改商品提交
	// 通过Items接收批量提交的商品信息，将商品信息存储到items中itemsList属性中。
	@RequestMapping("/editItemsAll")
	public String editItemsAll(VoItem items)
			throws Exception {


		return "success";
	}
	// 批量修改商品提交
	// 通过Items接收批量提交的商品信息，将商品信息存储到items中itemsList属性中。
	@RequestMapping("/editItemsMap")
	public String editItemsMap(VoItem items)
			throws Exception {


		return "success";
	}

	//查询商品信息，输出json
	///itemsView/{id}里边的{id}表示占位符，通过@PathVariable获取占位符中的参数，
	//如果占位符中的名称和形参名一致，在@PathVariable可以不指定名称
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody TItem itemsView(@PathVariable("id") String id)throws Exception{

		//调用service查询商品信息
		TItem item = itemsService.findItemsById(id);

		return item;

	}
}

// 10 @RequestMapping
//  url映射
// 定义controller方法对应的url，进行处理器映射使用。
//
//
//  窄化请求映射
//
//
//  限制http请求方法
// 出于安全性考虑，对http的链接进行方法限制。
// 如果限制请求为post方法，进行get请求，报错：

//
// 11 controller方法的返回值
//
//  返回ModelAndView
// 需要方法结束时，定义ModelAndView，将model和view分别进行设置。
//
//  返回string
// 如果controller方法返回string，
//
// 1、表示返回逻辑视图名。
// 真正视图(jsp路径)=前缀+逻辑视图名+后缀
//
// 2、redirect重定向
// 商品修改提交后，重定向到商品查询列表。
// redirect重定向特点：浏览器地址栏中的url会变化。修改提交的request数据无法传到重定向的地址。因为重定向后重新进行request（request无法共享）
//
//
// 3、forward页面转发
// 通过forward进行页面转发，浏览器地址栏url不变，request可以共享。
//
//
//
//  返回void
//
// 在controller方法形参上可以定义request和response，使用request或response指定响应结果：
// 1、使用request转向页面，如下：
// request.getRequestDispatcher("页面路径").forward(request, response);
//
// 2、也可以通过response页面重定向：
// response.sendRedirect("url")
//
// 3、也可以通过response指定响应结果，例如响应json数据如下：
// response.setCharacterEncoding("utf-8");
// response.setContentType("application/json;charset=utf-8");
// response.getWriter().write("json串");
