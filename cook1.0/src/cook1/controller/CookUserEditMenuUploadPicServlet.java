package cook1.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookUserEditMenuUploadPicServlet
 */
@WebServlet("/CookUserEditMenuUploadPicServlet")
public class CookUserEditMenuUploadPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserEditMenuUploadPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		/*cookuserEditmenuResponse to store the value of
		editmenu_menuPicUpload_menuId ... contains value of menuId that is being edited
		editmenu_menuPicUpload_picName ... contains value of filename of the edited pic*/
		String[] cookuserEditmenuUploadPicResponse = new String[2];
		String editmenu_menuPicUpload_menuId = new String();
		String editmenu_menuPicUpload_picName = new String();
		
		String editmenu_menuPicUpload_picContentType = request.getContentType();
		
		while(editmenu_menuPicUpload_picContentType!=null && editmenu_menuPicUpload_picContentType.indexOf("multipart/form-data")>=0){
			DataInputStream dis = new DataInputStream(request.getInputStream());
			
			int editmenu_menuPicUpload_picContentLength = request.getContentLength();
			byte[] editmenu_menuPicUpload_picContentBuffer = new byte[editmenu_menuPicUpload_picContentLength];
			
			int readBuffer = 0;
			int ttlReadBuffer = 0;
			
			while(ttlReadBuffer<editmenu_menuPicUpload_picContentLength){
				readBuffer = dis.read(editmenu_menuPicUpload_picContentBuffer, ttlReadBuffer, editmenu_menuPicUpload_picContentLength);
				
				ttlReadBuffer += readBuffer;				
			}
			
			dis.close();
			
			
			String editmenu_menuPicUpload_picContentBufferString = new String(editmenu_menuPicUpload_picContentBuffer);
			
			
			editmenu_menuPicUpload_menuId = editmenu_menuPicUpload_picContentBufferString.substring(
					editmenu_menuPicUpload_picContentBufferString.indexOf("name=\"editmenu_menuPicUpload_menuId\"")+36);
			editmenu_menuPicUpload_menuId = editmenu_menuPicUpload_menuId.substring(editmenu_menuPicUpload_menuId.indexOf("\n")+1);
			editmenu_menuPicUpload_menuId = editmenu_menuPicUpload_menuId.substring(editmenu_menuPicUpload_menuId.indexOf("\n")+1);
			editmenu_menuPicUpload_menuId = editmenu_menuPicUpload_menuId.substring(
					0, editmenu_menuPicUpload_menuId.indexOf("\n")-1);
			//System.out.println("menuId: " + editmenu_menuPicUpload_menuId);
			
			
			editmenu_menuPicUpload_picName = editmenu_menuPicUpload_picContentBufferString.substring(
					editmenu_menuPicUpload_picContentBufferString.indexOf("filename=\"")+10);
			editmenu_menuPicUpload_picName = editmenu_menuPicUpload_picName.substring(0, editmenu_menuPicUpload_picName.indexOf("\n"));
			editmenu_menuPicUpload_picName = editmenu_menuPicUpload_picName.substring(
					editmenu_menuPicUpload_picName.indexOf("\\")+1,editmenu_menuPicUpload_picName.indexOf("\""));
			//System.out.println("picName: " + editmenu_menuPicUpload_picName);
			
			
			//System.out.println(editmenu_menuPicUpload_picContentType);
			//System.out.println("test: "+editmenu_menuPicUpload_picContentType.indexOf("\n")); //content type has no "\n"
			String editmenu_menuPicUpload_fileBoundary = editmenu_menuPicUpload_picContentType.substring(
					editmenu_menuPicUpload_picContentType.indexOf("=")+1, editmenu_menuPicUpload_picContentType.length());
			/*testing*/
			//System.out.println(editmenu_menuPicUpload_fileBoundary);
			//System.out.println("fileBoundary length: " + editmenu_menuPicUpload_fileBoundary.length());
			
			
			int editmenu_menuPicUpload_fileBeginPos = 0;
			editmenu_menuPicUpload_fileBeginPos = editmenu_menuPicUpload_picContentBufferString.indexOf("filename=\"");
			editmenu_menuPicUpload_fileBeginPos = editmenu_menuPicUpload_picContentBufferString.indexOf("\n", editmenu_menuPicUpload_fileBeginPos)+1;
			editmenu_menuPicUpload_fileBeginPos = editmenu_menuPicUpload_picContentBufferString.indexOf("\n", editmenu_menuPicUpload_fileBeginPos)+1;
			editmenu_menuPicUpload_fileBeginPos = editmenu_menuPicUpload_picContentBufferString.indexOf("\n", editmenu_menuPicUpload_fileBeginPos)+1;
			/*testing*/
			//System.out.println(editmenu_menuPicUpload_picContentBufferString.substring(editmenu_menuPicUpload_fileBeginPos));
					
			
			int editmenu_menuPicUpload_fileEndPos = 0;
			editmenu_menuPicUpload_fileEndPos = 
					editmenu_menuPicUpload_picContentBufferString.indexOf(editmenu_menuPicUpload_fileBoundary, editmenu_menuPicUpload_fileBeginPos)-4;
			/*testing*/
			//System.out.println("fileBeginPos: " + editmenu_menuPicUpload_fileBeginPos);
			//System.out.println("fileEndPos: " + editmenu_menuPicUpload_fileEndPos);
			
			
			int editmenu_menuPicUpload_fileHeaderInByte = (editmenu_menuPicUpload_picContentBufferString.substring(
					0, editmenu_menuPicUpload_fileBeginPos).getBytes()).length;
			int editmenu_menuPicUpload_fileWholeInByte = (editmenu_menuPicUpload_picContentBufferString.substring(
					0, editmenu_menuPicUpload_fileEndPos).getBytes()).length;
			/*testing*/
			//System.out.println("fileHeaderInByte: " + editmenu_menuPicUpload_fileHeaderInByte);
			//System.out.println("fileWholeInByte: " + editmenu_menuPicUpload_fileWholeInByte);
			
			
			String editmenu_menuPicUpload_fileLocationWeb = getServletContext().getRealPath("/").replace("\\", "/") + "img/";
			String editmenu_menuPicUpload_fileLocationLocal = editmenu_menuPicUpload_fileLocationWeb.substring(0,
					editmenu_menuPicUpload_fileLocationWeb.lastIndexOf(".metadata")) + "Cook1/WebContent/img/";
			/*testing*/
			//System.out.println("fileLocationWeb: " + editmenu_menuPicUpload_fileLocationWeb);
			//System.out.println("fileLocationLocal: " + editmenu_menuPicUpload_fileLocationLocal);
			
			
			File editmenu_menuPicUpload_fileForWeb = new File(editmenu_menuPicUpload_fileLocationWeb + editmenu_menuPicUpload_picName);
			File editmenu_menuPicUpload_fileForLocal = new File(editmenu_menuPicUpload_fileLocationLocal + editmenu_menuPicUpload_picName);
			
			
			try{
				FileOutputStream fos_web = new FileOutputStream(editmenu_menuPicUpload_fileForWeb);
				FileOutputStream fos_local = new FileOutputStream(editmenu_menuPicUpload_fileForLocal);
				
				fos_web.write(editmenu_menuPicUpload_picContentBuffer, 
						editmenu_menuPicUpload_fileHeaderInByte, (editmenu_menuPicUpload_fileWholeInByte-editmenu_menuPicUpload_fileHeaderInByte));
				fos_local.write(editmenu_menuPicUpload_picContentBuffer, 
						editmenu_menuPicUpload_fileHeaderInByte, (editmenu_menuPicUpload_fileWholeInByte-editmenu_menuPicUpload_fileHeaderInByte));
				
				fos_web.flush();
				fos_local.flush();
				
				fos_web.close();
				fos_local.close();
				
				break;
			}catch(Exception err){
				System.out.println(err.getMessage());
				break;
			}		
		}
		
		
		cookuserEditmenuUploadPicResponse[0] = editmenu_menuPicUpload_menuId;
		cookuserEditmenuUploadPicResponse[1] = editmenu_menuPicUpload_picName;
		
		
		request.setAttribute("cookuserEditmenuUploadPicResponse", cookuserEditmenuUploadPicResponse);
		RequestDispatcher rd = request.getRequestDispatcher("CookUserDashboardEditMenuViewServlet");
		rd.forward(request, response);
		
	}

}
