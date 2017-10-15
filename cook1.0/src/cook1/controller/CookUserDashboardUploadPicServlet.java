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
 * Servlet implementation class CookUserDashboardUploadPicServlet
 */
@WebServlet("/CookUserDashboardUploadPicServlet")
public class CookUserDashboardUploadPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserDashboardUploadPicServlet() {
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
		
		
		//System.out.println(request.getContentType());
		//System.out.println(request.getContentLength());
		
		/* addmenu_menuPicUpload_picName to store the uploaded pic filename*/
		String addmenu_menuPicUpload_picName = new String();
		/* addmenu_menuPicUpload_picContentType to store the content type of uploaded pic filename in this case is "multipart/form-data"*/
		String addmenu_menuPicUpload_picContentType = request.getContentType();
		/* addmenu_counter to simply pass the value from/to addmenu.jsp*/
		String addmenu_counter = new String();
		
		/* get the input stream data and store it in addmenu_menuPicUpload_picContentBuffer (byte array)*/
		while(addmenu_menuPicUpload_picContentType!=null && addmenu_menuPicUpload_picContentType.indexOf("multipart/form-data")>=0){
			DataInputStream dis = new DataInputStream(request.getInputStream());
			
			int addmenu_menuPicUpload_picContentLength = request.getContentLength();
			byte[] addmenu_menuPicUpload_picContentBuffer = new byte[addmenu_menuPicUpload_picContentLength];
			
			int readBuffer = 0;
			int ttlReadBuffer = 0;
			
			while(ttlReadBuffer<addmenu_menuPicUpload_picContentLength){
				readBuffer = dis.read(addmenu_menuPicUpload_picContentBuffer, ttlReadBuffer, addmenu_menuPicUpload_picContentLength); 
				//read UP to Max addmenu_menuPicUpload_picContentLength, starting from ttlReadBuffer, and 
				//store/ append the read bytes in addmenu_menuPicUpload_picContentBuffer
				
				ttlReadBuffer += readBuffer;				
			}
			
			dis.close();
			
			/* addmenu_menuPicUpload_picContentBufferString stores the String value of addmenu_menuPicUpload_picContentBuffer*/
			String addmenu_menuPicUpload_picContentBufferString = new String(addmenu_menuPicUpload_picContentBuffer);
			
			
			/* TIPS: when form is submitted via multipart/form-data, you cannot use request.getParameter() to get the submitted value.
			 * This is because the value is in the stream which you can only get by acquiring it from the stream*/
			/* TIPS: normally substring method is String.substring(startIndex-inclusive, endIndex-exclusive)
			 * the endIndex-exclusive part might NOT be applicable for "\n". see the following practice when I need to -1 for 
			 * the value of addmenu_counter to extract out the extra "\n" at the end of value. Try to printout both values to compare*/
			addmenu_counter = addmenu_menuPicUpload_picContentBufferString.substring(
					addmenu_menuPicUpload_picContentBufferString.indexOf("addmenu_counter\""));
			addmenu_counter = addmenu_counter.substring(addmenu_counter.indexOf("\n")+1);
			addmenu_counter = addmenu_counter.substring(addmenu_counter.indexOf("\n")+1);
			addmenu_counter = addmenu_counter.substring(0, addmenu_counter.indexOf("\n")-1);
			
			/* addmenu_menuPicUpload_picName stores the filename from the uploaded picture file*/
			addmenu_menuPicUpload_picName = 
					addmenu_menuPicUpload_picContentBufferString.substring(addmenu_menuPicUpload_picContentBufferString.indexOf("filename=\"")+10);
			addmenu_menuPicUpload_picName = addmenu_menuPicUpload_picName.substring(0, addmenu_menuPicUpload_picName.indexOf("\n"));
			addmenu_menuPicUpload_picName = 
					addmenu_menuPicUpload_picName.substring(addmenu_menuPicUpload_picName.indexOf("\\")+1, addmenu_menuPicUpload_picName.indexOf("\""));
			/* testing*/
			//System.out.println("=================\n"+addmenu_menuPicUpload_picName);
			
			
			/* addmenu_menuPicUpload_fileBoundary is later used to define the index of the end of file (addmenu_menuPicUpload_fileBeginPos)*/
			String addmenu_menuPicUpload_fileBoundary = 
					addmenu_menuPicUpload_picContentType.substring(addmenu_menuPicUpload_picContentType.indexOf("=")+1,
							addmenu_menuPicUpload_picContentType.length());
			
			
			/* addmenu_menuPicUpload_fileBeginPos stores index of the beginning of the file*/
			int addmenu_menuPicUpload_fileBeginPos;
			addmenu_menuPicUpload_fileBeginPos = addmenu_menuPicUpload_picContentBufferString.indexOf("filename=\"");
			addmenu_menuPicUpload_fileBeginPos = addmenu_menuPicUpload_picContentBufferString.indexOf("\n", addmenu_menuPicUpload_fileBeginPos)+1;
			addmenu_menuPicUpload_fileBeginPos = addmenu_menuPicUpload_picContentBufferString.indexOf("\n", addmenu_menuPicUpload_fileBeginPos)+1;
			addmenu_menuPicUpload_fileBeginPos = addmenu_menuPicUpload_picContentBufferString.indexOf("\n", addmenu_menuPicUpload_fileBeginPos)+1;
			
			
			/* addmenu_menuPicUpload_fileEndPos stores index of the end of the file*/
			int addmenu_menuPicUpload_fileEndPos;
			addmenu_menuPicUpload_fileEndPos = 
					addmenu_menuPicUpload_picContentBufferString.indexOf(addmenu_menuPicUpload_fileBoundary, addmenu_menuPicUpload_fileBeginPos)-3; 
			
			
			/* addmenu_menuPicUpload_fileHeaderSizeInByte stores size of the file header(up to the beginning of file content)*/
			int addmenu_menuPicUpload_fileHeaderSizeInByte = 
					(addmenu_menuPicUpload_picContentBufferString.substring(0, addmenu_menuPicUpload_fileBeginPos).getBytes()).length;
			/* addmenu_menuPicUpload_fileWholeSizeInByte stores size of the whole file (up to the end of file content only)*/
			int addmenu_menuPicUpload_fileWholeSizeInByte = 
					(addmenu_menuPicUpload_picContentBufferString.substring(0, addmenu_menuPicUpload_fileEndPos).getBytes()).length;					
			
			
			/* define output stream file & its location
			 * addmenu_menuPicUpload_webDirectoryLocation   ... destination upload at web server directory
			 * addmenu_menuPicUpload_localDirectoryLocation ... destination upload at local machine directory*/
			String addmenu_menuPicUpload_webDirectoryLocation = (getServletContext().getRealPath("/")).replace("\\", "/") + "img/";
			String addmenu_menuPicUpload_localDirectoryLocation = 
					addmenu_menuPicUpload_webDirectoryLocation.substring(0, addmenu_menuPicUpload_webDirectoryLocation.lastIndexOf(".metadata")) + 
					"Cook1/WebContent/img/";
			/* testing*/
			//System.out.println(addmenu_menuPicUpload_webDirectoryLocation);
			//System.out.println(addmenu_menuPicUpload_localDirectoryLocation);
			/* compare: request.getContextPath() vs getServletContext().getRealPath("")
			 * - HttpServletRequest.getContextPath() or request.getContextPath() returns url http://localhost:8080/Cook1/ because I use localhost
			 * - getServletContext().getRealPath("") returns the real path of the directory structure on the computer*/
			//out.println(request.getContextPath() + "<br/>");
			//out.println(getServletContext().getRealPath("/"));
			
			/* create the files (to web & local directory) in which uploaded file content will be written*/ 
			File addmenu_menuPicUpload_webDirectoryLocationFile = 
					new File(addmenu_menuPicUpload_webDirectoryLocation + addmenu_menuPicUpload_picName);
			File addmenu_menuPicUpload_localDirectoryLocationFile = 
					new File(addmenu_menuPicUpload_localDirectoryLocation + addmenu_menuPicUpload_picName);
			
			/* write the uploaded file content into the files*/
			try{
				FileOutputStream fosWeb = new FileOutputStream(addmenu_menuPicUpload_webDirectoryLocationFile);
				FileOutputStream fosLocal = new FileOutputStream(addmenu_menuPicUpload_localDirectoryLocationFile);
				
				fosWeb.write(addmenu_menuPicUpload_picContentBuffer, addmenu_menuPicUpload_fileHeaderSizeInByte, 
						(addmenu_menuPicUpload_fileWholeSizeInByte-addmenu_menuPicUpload_fileHeaderSizeInByte));
				fosLocal.write(addmenu_menuPicUpload_picContentBuffer, addmenu_menuPicUpload_fileHeaderSizeInByte, 
						(addmenu_menuPicUpload_fileWholeSizeInByte-addmenu_menuPicUpload_fileHeaderSizeInByte));
				
				fosWeb.flush();
				fosLocal.flush();
				
				fosWeb.close();
				fosLocal.close();
				
				break;
				
			}catch(Exception err){
				System.out.println(err.getMessage());
				break;
			}
		}
		
		/* Testing*/
		//System.out.println("addmenu_counter value: " + addmenu_counter + "testing");
		
		request.setAttribute("addmenu_counter", Integer.parseInt(addmenu_counter));
		request.setAttribute("addmenu_menuPicUpload_picName", addmenu_menuPicUpload_picName);
		RequestDispatcher rd = request.getRequestDispatcher("addmenu.jsp");
		rd.forward(request, response);
		
	}

}
