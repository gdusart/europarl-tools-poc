package be.gdusart.europarltools.rp.scheduling;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class RPSchedulerHttpClientConfiguration {

	public static final String HTTP_CLIENT_QUALIFIER = "RPSchedulerHttpClientConfiguration_httpclient";
	public static final String EXECUTOR_QUALIFIER = "RPSchedulerHttpClientConfiguration_executor";

	@Bean
	@Qualifier(HTTP_CLIENT_QUALIFIER)
	public CloseableHttpClient httpClient() {
		CloseableHttpClient clt = HttpClients.custom().useSystemProperties().setMaxConnTotal(10).setMaxConnPerRoute(5)
				.build();

		return clt;
	}

	@Bean
	@Qualifier(EXECUTOR_QUALIFIER)
	public AsyncTaskExecutor executor() {
		ThreadPoolTaskExecutor exe = new ThreadPoolTaskExecutor();
		exe.setCorePoolSize(20);
		exe.setMaxPoolSize(50);
		exe.setQueueCapacity(200);
		return exe;
	}

}
