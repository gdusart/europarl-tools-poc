package be.gdusart.europarltools.scheduling.reverseproxy;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
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
	public TaskExecutor executor() {
		ThreadPoolTaskExecutor exe = new ThreadPoolTaskExecutor();
		exe.setCorePoolSize(2);
		exe.setMaxPoolSize(10);
		exe.setQueueCapacity(1000);
		return exe;
	}

}
