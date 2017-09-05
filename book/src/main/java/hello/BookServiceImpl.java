package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	Tracer tracer;

	public boolean isBookAvailable(String bookTitle){

		log.info("isBookAvailable("+bookTitle+")");

		Span newSpan = tracer.createSpan("fakeReadInDbForBookAvailable");

		try{
			log.info("isBookAvailable() - fake access DB!");
			Thread.sleep(100);
		}catch (Exception ex){
			log.error("Ex while faking db access", ex);
		}finally {

			newSpan.tag("book.service", "db");
			newSpan.logEvent(
					org.springframework.cloud.sleuth.Span.CLIENT_RECV);
			tracer.close(newSpan);
		}

		return  true;

	}
}
