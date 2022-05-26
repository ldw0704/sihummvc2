package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import service.ViewServiceImpl;
import service.WriterService;
import service.WriterServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/view")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewController() {
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
		dispatcher.forward(request, response);
	}


}



