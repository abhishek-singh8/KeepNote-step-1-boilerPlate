
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
</head>
<body>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
		<form method="post" action=/dispatcher/saveNote >
        NoteId: <input type="text" name="noteId" /> <br />
        NoteTitle: <input type="text" name="noteTitle" /> <br />
        NoteContent: <input type="text" name="noteContent" /> <br />
        NoteStatus:  <select name="cars">
					    <option value="active">Active</option>
					    <option value="inactive">Inactive</option>
					  </select> <br />
	    CreatedBy:  <input type="text" name="createdBy"/><br/>
        <input type="submit" value="Send">
        </form>
	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
</body>
</html>