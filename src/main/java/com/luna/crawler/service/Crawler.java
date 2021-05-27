package com.luna.crawler.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class Crawler {


	public void doGet() throws IOException {

		try {
		String url = "https://together.kakao.com/api/boards/3/contents?limit=10";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36";
		Document doc = Jsoup.connect(url)
				.header("Accept","application/json, text/plain, */*")
				.header("Referer", "https://together.kakao.com/magazines")
				.header("Cookie", "webid=d1cbd553f8ac41729d4ff3a453d41446; webid_ts=1610332786301; kd_lang=ko; _kadu=ToQjLyTdmSDuu3P__1613527980099; _ga=GA1.2.114067040.1613528000; _kdt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkZXZlbG9wZXJJZCI6NjU1NzUyLCJhY2NvdW50SWQiOjMyMDI5MzAsImVtYWlsIjoiYXVkd2xzMTk5NUBuYXZlci5jb20iLCJ0b2tlbiI6IjhhYzhjZjc4YTg0YjkzNzdhZGZjNDEzY2I0NDVhYmMxNzk0OGZiN2FmMTY3ZTY0MDhmMzI4NGExNjc2OWFkYjgifQ.nab_7DQiepnfmc4uNCYoM3H_poArIrPtFgWzlBysPOY; TIARA=2XS5.z5cxsGN5wssgTlrmK2y_LAk.HdefmfoZUxvqwMkRMxsovBwSnA9TE6tuM.U1NoYceUrDEJnObWCcomMfYbUzGjZTzUh; _fbp=fb.1.1614136987555.1020160310; _gid=GA1.2.882733378.1620889616; __T_=1; _T_ANO=aLuerNwtYnoKVn000mFbtvQWfFRlzb9YOJaZPhOsSSGDqFtFzC/vy3krnUidYvefkgLjae6Yd/Ibe5TPbQ9jW3x8vt08Zku9q7Y3qOMdJ+BIoc8iFDw8GbKorejlOXo5dKc8RUJZNjtQ4F0Ln7z7zUqf+cq5swkwuw86Nr2au3a8qOwnwEsm4C/B9Wa7QcaIcKaP7mExcNW7MTZU7n3I7mSvxfJdGXttNEUDgPPn9Gq8IgZ5xZBAX8SFWjBgT6jJhqkRz3UToVK6oZ7/biZQCgjJ9g8xhfXAvFhT425BBak2Vtu0aBIBliiWWp85I/61VC0QeTNbgC2nNEjCm7XhPA==; _auroville-api_session=Vkd1bzFRdkxGc2hxMnBLUmZVOHVJd2VQLzM3TVNiV2dURzVlMi9oWDA2eFZtb0NrTnY0U2hKdXlqc3k4KzVhNmtLVzVDWE9ZaUlmWGp0MS9XWUZkT0E9PS0tRzRONkxQbVNYM3FIKzZ6M1lTOTJoZz09--d0587f6a69f88f8dd2dc842c4b367f8f08290f9a; _gat=1")
				.header("User-Agent", userAgent)
				.header("Sec-Fetch-Site", "same-origin")
				.get();
		Elements eles = doc.select("div[class=\"group_catalog\"]");
		
		log.info("------------doc-------------"+doc);			
		log.info("------------eles-------------"+eles);

		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}

} //end crawler
