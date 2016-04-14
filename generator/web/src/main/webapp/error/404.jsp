<%@page contentType="text/html" isErrorPage="true" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>    
   <title>Custom Plugin Generator</title>   
   <meta charset="UTF-8" /> 
   <meta name="viewport" content="width=device-width,initial-scale=1" />
   <link rel="icon" type="image/png" sizes="48x48" href="/resources/images/favicon-96x96.png" />
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"></link>
   <link rel="stylesheet" href="/resources/styles/custom.css"/>
   <fmt:setBundle basename="messages_en_US" var="lang" />
</head>
 <body> 
 	<div class="container-fluid">
 	<header>  
	    <nav id="apg-navbar" class="navbar navbar-inverse navbar-static-top" >
	      	<label class="badge navbar-text">      		
	      		<img class="icon-bar" alt="Avocent Custom Plugin Generator" src="/resources/images/favicon-32x32.png"/>
	      		Avocent Custom Plugin Generator
	    	</label> 
	    </nav>  
    </header> 
    <main id="apg-content-pane" class="panel panel-body">
	      <div class="col-lg-6">	
	    	<span class="row text text-warning">
    		   <fmt:message key="http.404.h1" bundle="${lang}"/>
    	   </span>	
			<span class="row text text-default">
			   <fmt:message key="http.404.goto" bundle="${lang}"/> <a href="/"><fmt:message key="http.404.home" bundle="${lang}"/></a> <fmt:message key="http.404.page" bundle="${lang}"/>
			</span>   
		</div>
    </main>    
   	<footer class="panel panel-footer text-center">
   		<span class="text-success">&copy; Avocent Corporation</span>
   	</footer> 
   	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.10.0.js"></script>   
</body>
</html>
