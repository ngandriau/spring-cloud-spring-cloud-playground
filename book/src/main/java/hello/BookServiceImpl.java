package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	public boolean isBookAvailable(String bookTitle){

		log.info("isBookAvailable("+bookTitle+")");

		return  true;

	}
}
