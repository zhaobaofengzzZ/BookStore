package zbf.bookstore.web.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dsna.util.images.ValidateCode;

public class ImageCode extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取输出流
		ServletOutputStream os = response.getOutputStream();
		//获取验证码图片
		ValidateCode vd = new ValidateCode(110, 25, 4, 9);
		//获取验证码里的字符串
		String imgCode = vd.getCode();
		//将验证码里的字符串放在session作用域里
		HttpSession session = request.getSession();
		session.setAttribute("imgCode", imgCode);
		//将验证码图片在页面画出
		vd.write(os);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
