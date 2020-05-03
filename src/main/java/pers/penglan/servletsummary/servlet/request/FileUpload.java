package pers.penglan.servletsummary.servlet.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

/**
 * 文件上传
 * @Author PENGL
 */
@WebServlet(urlPatterns = "/request/fileupload")
@MultipartConfig(location = "FileDownload", maxFileSize = 10*1024*1024, maxRequestSize = 20*1024*1024, fileSizeThreshold = 500*1024)
public class FileUpload extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Collection<Part> collection = request.getParts();
        logger.info(">>begin");
        for (Part part : collection) {
            logger.info("== File");
            logger.info("ContentType:" + part.getContentType());
            logger.info("Name:" + part.getName());
            logger.info("SubmittedFileName:" + part.getSubmittedFileName());
            logger.info("Size:" + part.getSize());
            part.write(part.getSubmittedFileName());
        }
        logger.info(">>end");

    }
}
