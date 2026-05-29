package com.classwork2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/uploadProfile")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1MB - data written to disk after this limit
    maxFileSize = 1024 * 1024 * 10,       // 10MB - Allow up to 10MB to reach the servlet
    maxRequestSize = 1024 * 1024 * 20     // 20MB - Total request limit
)
public class UploadProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = "";
        long maxSize = 5 * 1024 * 1024; // Your business logic limit: 5MB

        try {
            // 1. Retrieve the file part safely
            Part filePart = request.getPart("uploadProfile");

            // 2. Initial null/empty check
            if (filePart == null || filePart.getSize() == 0) {
                message = "Please select a file!";
            } else {
                String fileName = filePart.getSubmittedFileName();
                long fileSize = filePart.getSize();

                // 3. Validation Logic
                if (!fileName.toLowerCase().endsWith(".pdf")) {
                    message = "Error: Invalid file type! Please upload a PDF document.";
                } 
                else if (fileSize > maxSize) {
                    message = "Error: File size exceeds the 5MB limit! Please upload a smaller PDF.";
                } 
                else {
                    // 4. Define upload directory
                    String uploadPath = getServletContext().getRealPath("") + File.separator + "employee_profiles";
                    File uploadDir = new File(uploadPath);

                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    // 5. Save the file with a unique name
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                    String filePath = uploadPath + File.separator + uniqueFileName;

                    filePart.write(filePath);
                    message = "Success: Profile document uploaded successfully!";
                }
            }
        } catch (ServletException e) {
            // This catches files that are even larger than the 10MB @MultipartConfig limit
            message = "Error: The file is way too large for the server to process.";
        } catch (Exception e) {
            message = "Error: An unexpected error occurred: " + e.getMessage();
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(message);
    }
}