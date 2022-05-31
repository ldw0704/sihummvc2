package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
		request.setCharacterEncoding("UTF-8");
		String saveFolder = "upload";

		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		File targetDir = new File(realFolder);
		if(!targetDir.exists()) {
			targetDir.mkdir();
		}
		
		int maxSize = 10*1024*1024;//10Mb
		String encType = "UTF-8";
		
		//넘어온 값을 변수에 저장
		MultipartRequest multi = 
				new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		String num = multi.getParameter("num");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		String realFileName = multi.getParameter("realFileName");
		String realSaveFileName = multi.getParameter("realSaveFileName");
		
		
		String newRealFileName = multi.getOriginalFileName("upfile");
		String newRealSaveFileName = multi.getFilesystemName("upfile");

		if(newRealFileName==null||"".equals(newRealFileName)) {
			newRealFileName = realFileName;
			newRealSaveFileName = realSaveFileName;
		}
		
		BoardVO vo = new BoardVO();
		vo.setNum(Integer.parseInt(num));
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setRealFileName(newRealFileName);
		vo.setRealSaveFileName(newRealSaveFileName);
		
		UpdateServiceImpl service = new UpdateServiceImpl();
		int res = service.update(vo);
		
		//Files.deleteIfExists(null);
		//페이지 이동
		response.sendRedirect("list");		
		
	}

}



