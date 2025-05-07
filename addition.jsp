<%@ page info="This is Addition Quiz Page " %>

<html>
<title>Addition Quiz</title>

<form action="./quizAns" >
20 + 6 = <input type = "text" name = "one" size = 3/><br>
27 + 6 = <input type = "text" name = "two" size = 3/><br>
28 + 8 = <input type = "text" name = "three" size = 3 /><br>
28 + 10 = <input type = "text" name = "four" size = 3/><br>
28 + 7 = <input type = "text" name = "five" size = 3/><br>
29 + 10 = <input type = "text" name = "six" size = 3/><br>
22 + 9 = <input type = "text" name = "seven" size = 3/><br>
29 + 12 = <input type = "text" name ="eight" size = 3/><br>
21 + 6 = <input type = "text" name = "nine" size = 3/><br>
27 + 12 = <input type = "text" name = "ten" size = 3/><br>

<input type = "submit" value = "Submit"/> Click the browser's Refresh button to get a new quiz

</form>

</html>

web.xml

<web-app>
<welcome-file-list>
<welcome-file>additionQuiz.jsp</welcome-file>
</welcome-file-list>

<servlet>
<servlet-name>quizAnswers</servlet-name>
<servlet-class>QuizAnswerServlet</servlet-class>
</servlet>

<servlet-mapping>
<servlet-name>quizAnswers</servlet-name>
<url-pattern>/quizAns</url-pattern>
</servlet-mapping>

</web-app>

QuizAnswerServlet.java ( Servlet )

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QuizAnswerServlet extends HttpServlet
{
public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
int count = 0;

int first = Integer.parseInt(req.getParameter("one").trim());
int second = Integer.parseInt(req.getParameter("two").trim());
int third = Integer.parseInt(req.getParameter("three").trim());
int fourth = Integer.parseInt(req.getParameter("four").trim());
int fifth = Integer.parseInt(req.getParameter("five").trim());
int sixth = Integer.parseInt(req.getParameter("six").trim());
int seventh = Integer.parseInt(req.getParameter("seven").trim());
int eighth = Integer.parseInt(req.getParameter("eight").trim());
int nineth = Integer.parseInt(req.getParameter("nine").trim());
int tenth = Integer.parseInt(req.getParameter("ten").trim());

if(20 + 6 == first)
count++;
if(27 + 6 == second)
count++;
if(28 + 8 == third)
count++;
if(28 + 10 == fourth)
count++;
if(28 + 7 == fifth)
count++;
if(29 + 10 == sixth)
count++;
if(22 + 9 == seventh)
count++;
if(29 + 12 == eighth)
count++;
if(21 + 6 == nineth)
count++;
if(27 + 12 == tenth)
count++;

out.println("<html><body>");

out.println("20 + 6 = "+first+""+( 20 + 6 == first ? "Correct" : "Wrong" ) + "<br>");
out.println("27 + 6 = "+second+""+( 27 + 6 == second ? "Correct" : "Wrong" )+ "<br>");
out.println("\n28 + 8 = "+third+""+( 28 + 8 == third ? "Correct" : "Wrong" )+ "<br>");
out.println("\n28 + 10 = "+fourth+""+( 28 + 10 == fourth ? "Correct" : "Wrong" )+ "<br>");
out.println("\n28 + 7 = "+fifth+""+( 28 + 7 == fifth ? "Correct" : "Wrong" )+ "<br>");
out.println("\n29 + 10 = "+sixth+""+( 29 + 10 == sixth ? "Correct" : "Wrong" )+ "<br>");
out.println("\n22 + 9 = "+seventh+""+( 22 + 9 == seventh ? "Correct" : "Wrong" )+ "<br>");
out.println("\n29 + 12 = "+eighth+""+( 29 + 12 == eighth ? "Correct" : "Wrong" )+ "<br>");
out.println("\n21 + 6 = "+nineth+""+( 21 + 6 == nineth ? "Correct" : "Wrong" )+ "<br>");
out.println("\n27 + 12 = "+tenth+""+( 27 + 12 == tenth ? "Correct" : "Wrong" )+ "<br>");

out.println("The total Correct count is : "+count);

out.println("</body></html>");

out.close();
}
}
