package ec.edu.espe.springlab1.interceptor;

import ec.edu.espe.springlab1.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public RequestLoggingInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, @NonNull HttpServletResponse resp, Object handler) throws Exception {
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ") || !jwtUtil.isValid(header.substring(7))) {
            resp.setStatus(401);
            resp.getWriter().write("{\"error\":\"Token inválido o ausente\"}");
            return false;
        }
        req.setAttribute("t0", System.currentTimeMillis());
        System.out.println("preHandle " + req.getMethod() + " " + req.getRequestURI());
        return true;
    }

    //Calcular la duracion total y registrar el status HTTP
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex){
        Long t0 = (Long)req.getAttribute("t0");
        long elapsed = (t0 == null) ? -1 :(System.currentTimeMillis() - t0);
        System.out.println("afterCompletion -> status: "+ resp.getStatus()+" tiempo: "+ elapsed+" ms");
    }

}

