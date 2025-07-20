package study.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import study.servlet.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    /// json으로 얻은 데이터를 HelloData로 변환시키기 위한 라이브러리 추가
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        /// json도 그냥 문자라서 TEXT와 똑같이 처리 가능
        System.out.println("messageBody = " + messageBody);

        /// 데이터는 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData = " + helloData.toString());

        response.getWriter().write("ok");
    }
}
