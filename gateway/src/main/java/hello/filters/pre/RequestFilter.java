package hello.filters.pre;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import hello.filters.utils.FilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static hello.filters.utils.FilterUtils.PRE_FILTER_TYPE;

public class RequestFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(RequestFilter.class);

	@Autowired
	FilterUtils filterUtils;

	@Override
	public String filterType() {
		return PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	private boolean isCorrelationIdPresent() {
		if (filterUtils.getCorrelationId() != null) {
			return true;
		}

		return false;
	}

	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));


		if (isCorrelationIdPresent()) {
			log.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
		} else {
			String newCorrelationId = generateCorrelationId();
			filterUtils.setCorrelationId(newCorrelationId);
			log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
		}

		return null;
	}


}
