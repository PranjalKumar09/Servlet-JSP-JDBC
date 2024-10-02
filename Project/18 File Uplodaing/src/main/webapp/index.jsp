    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="WEB-INF/mylib.tld" prefix="t" %>
    <%@ page isELIgnored="false" %>
    <%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, com.servlet.DBConnect" %>


    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="card">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <p class="text-center fs-3">Image Upload</p>
                            
                            <%
                            String msg = (String) session.getAttribute("msg");
                            if (msg!= null) { %>
                                <h4 class="text-center text-success"><%=msg %></h4>
                                <%
                                session.removeAttribute("msg");
                            }
                            %>


                            <form action="upload" method="post" enctype="multipart/form-data">
                                <div class="mb-3">
                                    <label>Image</label>
                                    <input type="file" class="form-control" name="files" required>
                                    
                                </div>
                                <div class="mb-3">
                                    <label>Remark</label>
                                    <input type="text" class="form-control" name="remark" required>
                                    
                                </div>
                                <div class="text-center">
                                    <button class="btn-primary">Upload</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table mt-4">
            <thead>
                <tr>
                <th scope="col">Image</th>
                <th scope="col">Remark</th>
                </tr>
            </thead>
            <tbody>
            <%
                Connection conn = DBConnect.getConn();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM img_details");
                ResultSet rs = ps.executeQuery();
            %>
            <%
                while (rs.next()) { %>
                    <tr>
                    <%
                    String imgName = rs.getString("img_name");
                    if (imgName != null && imgName.endsWith(".pdf")) { %>
                        <th scope="row">
                            <img src="imgs/pdf.png" width="200px" height="100px">
                        </th>
                        <td><a href="download?fn=<%= imgName %>" class="btn btn-primary btn-sm">Download</a></td>
                        <% } else if (imgName != null && imgName.endsWith(".xls")) { %>
                            <th scope="row">
                                <img src="imgs/xlsx.png" width="200px" height="100px">
                            </th>
                            <td><a href="download?fn=<%= imgName %>" class="btn btn-primary btn-sm">Download</a></td>
                            <% } else { %>
                                <th scope="row">
                                    <img src="imgs/<%= imgName %>" width="200px" height="100px">
                                </th>
                                <td><a href="download?fn=<%= imgName %>" class="btn btn-primary btn-sm">Download</a></td>
                    <% } %>
                    
                    <td><%= rs.getString("remark") %></td>
                    </tr>
                <% } %>

            </tbody>
    </table>
        </div>
    </body>
    </html>

