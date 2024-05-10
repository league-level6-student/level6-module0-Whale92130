package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;

import _01_intro_to_APIs.data_transfer_objects.Result;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsApiTest {

	NewsApi newsApi;

	@Mock
	WebClient webClient;

	@Mock
	WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

	@Mock
	WebClient.RequestHeadersSpec requestHeadersSpecMock;

	@Mock
	WebClient.ResponseSpec responseSpecMock;

	@Mock
	Mono<ApiExampleWrapper> resultMonoMock;

	@Mock
	ApiExampleWrapper apiExampleWrapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		newsApi = new NewsApi();
		newsApi.setWebClient(webClient);
	}

	@Test
	void itShouldGetNewsStoryByTopic() {
		// given
		String topic = "cars";
		// when

		when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(resultMonoMock);
		when(resultMonoMock.block()).thenReturn(apiExampleWrapper);

		ApiExampleWrapper wrapper = newsApi.getNewsStoryByTopic(topic);
		// then
		verify(webClient, times(1)).get();
		assertEquals(apiExampleWrapper, wrapper);

	}

	@Test
	void itShouldFindStory() {
		// given
		String topic = "food";
		String storyTitle = "World Central Kitchen to resume food aid in Gaza";
		String storyContent = "World Central Kitchen is to resume distributing food in Gaza, nearly a month after seven of its aid workers were killed in an Israeli air strike. \n"
				+ "The aid organisation said it has 276 trucks with ei… [+3463 chars]";
		String storyLink = "https://www.bbc.co.uk/news/world-middle-east-68915931";

		ApiExampleWrapper expectedResults = new ApiExampleWrapper();
		Article article = new Article();
		article.setTitle(storyTitle);
		article.setUrl(storyLink);
		article.setContent(storyContent);
		List<Article> list = new ArrayList<Article>();
		list.add(article);
		expectedResults.setArticles(list);
		// when

		when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(resultMonoMock);
		when(resultMonoMock.block()).thenReturn(expectedResults);
		// then
		String actualStory = newsApi.findStory(topic);
		String expectedStory = "Title: " + storyTitle + "\n\n" + "Content: " + storyContent + "\n\n" + "Source: "
				+ storyLink;

		verify(webClient, times(1)).get();
		assertEquals(expectedStory, actualStory);
	}

}