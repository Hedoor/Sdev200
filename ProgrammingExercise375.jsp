<html>

<head> Factorial program using Java servelet </head>

<body> // Write logic of the program

<table align="center" border = "1"> // To create table

<tr>

<td>Number</td> // To display number message in the table

<td>Factorial</td>

//To display number message in the table



</tr>

<% for (int i = 0;i<= 10; i++) { %>

<tr>

<td>

<% = i %> // Display the number to perform factorial

</td>

<td>

<%= computeFactorial(i) %> // call the function

</td>

</tr>

</table>
<% } %>

<%! private long computeFactorial(int n) {

if (n == 0) // number is zero

return 1; // Return 1 to the program

else

return n*computeFactorial(n - 1); %> // it is a non zero value then perform calculation

%>

}

</table>

</body>

</html>
