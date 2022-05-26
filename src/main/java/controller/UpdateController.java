package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import service.UpdateServiceImpl;
import service.ViewServiceImpl;
import service.WriterService;
import service.WriterServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		vo.setNum(Integer.parseInt(request.getParameter("num")));

		ViewServiceImpl service = new ViewServiceImpl();
		BoardVO bvo = service.read(vo);
		
		request.setAttribute("vo", bvo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//넘어온 값을 변수에 저장
		String num = request.getParameter("num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		BoardVO vo = new BoardVO();
		vo.setNum(Integer.parseInt(num));
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		UpdateServiceImpl service = new UpdateServiceImpl();
		service.update(vo);
		
		//페이지 이동
		response.sendRedirect("list");
		
		
	}

}



