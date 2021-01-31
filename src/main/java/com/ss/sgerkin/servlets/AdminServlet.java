package com.ss.sgerkin.servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for displaying the administration dashboard.
 */
public class AdminServlet extends HttpServlet {

  /**
   * Displays the administration dashboard.
   *
   * @param req  the request received.
   * @param resp the response to send.
   * @throws IOException if unable to write to the response.
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (var out = resp.getWriter()) {
      var username = req.getUserPrincipal().getName();

      out.println("<html><body>");
      out.println("<h1>Hello, " + username + "!</h1>");

      out.println(
          "<form action='/admin/dashboard.html' method='POST'>"
              + "<input type='submit' value='Logout'/>"
              + "</form>");

      out.println("</body></html>");
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  /**
   * Logs out the current user and redirects to the index.
   *
   * @param req  the request received.
   * @param resp the response to send.
   * @throws IOException if unable to redirect.
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.getSession().invalidate();

    try {
      resp.sendRedirect("/");
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    }
  }
}
