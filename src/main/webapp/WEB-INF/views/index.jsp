<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
</head>
<body>
    <!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
         button. Handle errors like empty fields -->
         <h3>Enter the details</h3>
       <form action="saveNote" method="post">
       <label> NoteID: <input type="text" name="noteId" /></label><br>
        <c:if test="${noteIdEmpty}"> This field is Required </c:if> 
       <label> Title: <input type="text" name="noteTitle" /> </label><br>
        <c:if test="${titleEmpty}"> This field is Required </c:if> 
        <c:if test="${titleNull}"> Field is Null </c:if> <br>
       <label> Content: <input type="text" name="noteContent" /> </label><br>
             <c:if test="${contentEmpty}"> This field is Required </c:if> 
         <c:if test="${contentNull}"> Field is Null </c:if> <br>
       <label> Status:<select name="noteStatus">
           <option>Status A</option>
           <option>Status B</option>
           <option>Status C</option>
           <option>Status D</option>
       </select></label><br>
       <input type="submit" value="send"  />
   </form>

 
     
    <!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
    <table border="2" width="70%" cellpadding="2">  
<tr><th>NoteId</th><th>Title</th><th>Content</th><th>Status</th><th>Creation Time</th><th>DeleteButton</th></tr>  
  <c:forEach var="note" items="${message}">  
  <tr>  
  <td>${note.noteId}</td>  
  <td>${note.noteTitle}</td>  
  <td>${note.noteContent}</td>  
  <td>${note.noteStatus}</td>  
  <td>${note.createdAt }</td>
  <td><a href="deleteNote?noteId=${note.noteId}" ><input type="button" value="Delete"></a></td>
 
  </tr>  
  </c:forEach>  
  </table>  
    
</body>
</html>