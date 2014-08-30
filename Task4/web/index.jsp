<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>XML parser</title>
  </head>
  <body>
      <h3>Select parser type</h3>

      <form name="parserTypeForm" action="parseaction" method="post">
          <input type="text" name="sourceFile" />
          <br />
          <input type="text" name="parserType" />
          <br />
          <input type="submit" name="button" value="Parse" />
      </form>
  </body>
</html>
