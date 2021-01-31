package com.ss.sgerkin.main;

import java.io.File;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * Driver to start the application with an embedded Tomcat server.
 */
public class Main {

  private static final String webDir = "src/main/webapp/";

  /**
   * Initializes and starts the app with an embedded Tomcat server.
   *
   * @param args unused.
   * @throws Exception if the server cannot be started.
   */
  public static void main(String[] args) throws Exception {

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);

    var context = tomcat.addWebapp("/", new File(webDir).getAbsolutePath());

    var additionWebInfClasses = new File("target/classes");
    WebResourceRoot resources = new StandardRoot(context);
    var resourceSet = new DirResourceSet(resources, "/WEB-INF/classes",
                                         additionWebInfClasses.getAbsolutePath(), "/");
    resources.addPreResources(resourceSet);
    context.setResources(resources);

    // add a dummy login
    tomcat.addUser("test", "test");
    tomcat.addRole("test", "admin");

    tomcat.start();
    tomcat.getServer().await();
  }
}