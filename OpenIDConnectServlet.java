package pt.isel.si;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenIDConnectServlet extends HttpServlet {
    public static final String CLIENT_ID = ""; /* the client id of your web client */
    public static final String CLIENT_SECRET = ""; /* the client secret of your web client */
    public static final String REDIRECT_URI = ""; /* the callback uri id of your web client */

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("--New request was received --");
        System.out.println(req.getRequestURI());
        System.out.println(req.getMethod());
        System.out.println(req.getHeader("Accept"));

        resp.setStatus(302);
        resp.setHeader("Location",
            // google's authorization endpoint
            "https://accounts.google.com/o/oauth2/v2/auth?"+
            "scope=openid%20email&"+
            // =some.security.state&
            "redirect_uri="+REDIRECT_URI+"&"+
            "response_type=code&"+
            "client_id="+CLIENT_ID+
            "&prompt=consent");
    }
}
