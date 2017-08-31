package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@SpringBootApplication
public class BookApplication {

  private static final Logger log = LoggerFactory.getLogger(BookApplication.class);

  private @Autowired
  HttpServletRequest request;

  private @Autowired
  BookServiceImpl bookService;

  @RequestMapping(value = "/available")
  public String available() {

    log.info("available()");

    boolean checkAvail = bookService.isBookAvailable("???");
    return "Spring in Action";
  }

  @RequestMapping(value = "/checked-out")
  public String checkedOut() {

    return "Spring Boot in Action";
  }

  public static void main(String[] args) {
    SpringApplication.run(BookApplication.class, args);
  }
}
