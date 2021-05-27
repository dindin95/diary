package com.luna.common.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Getter
@ToString
@Log4j
public class PageMaker {

	// 이전 페이지
	private boolean prev;
	// 다음 페이지
	private boolean next;
	// 시작 페이지
	private int start;
	// 마지막 페이지
	private int end;
	private PageDTO pageDTO;
	// 전체 게시글 수
	private int total;
	// 맨끝 페이지
	private int lastPage;

	// 기본 생성자
	public PageMaker(PageDTO pageDTO, int total) {
		this.pageDTO = pageDTO;
		this.total = total;

		// 페이지 게시글 수
		int perSheet = pageDTO.getPerSheet();

		// 현재 페이지 번호
		double currentPage = (double) pageDTO.getPage();

		// 임시 마지막 번호 13 -> 1.3 -> 2.0 -> 20
		int tempEnd = (int) (Math.ceil(currentPage * 0.1) * 10);

		// 20 - 9 = 11 이니깐 시작 페이지
		this.start = tempEnd - 9;

		// 마지막 페이지
		int realEnd = (int) (Math.ceil(total / (double) perSheet));

		// 더 작은 값이 end가 된다 진짜 마지막은 13이고 임시페이지가 20인 경우
		this.end = realEnd < tempEnd ? realEnd : tempEnd;

		this.prev = start > 1;

		this.next = end * perSheet < total;

		this.lastPage = (int) (Math.ceil(total / (double) perSheet));
	}

	// 링크 파라미터로 지정하면 게시물을 들어가서 뒤로가기든 목록으로가면 1페이지가 아닌 그전에 접속했던 페이지로 감
	// pageDTO page는 default로 1로 지정해줬으니깐 페이지 번호 맘대로 하기 위해서 int고 perSheet는 10으로 고정
	public String getLink(int page) {
		return "page=" + page + "&perSheet=" + this.pageDTO.getPerSheet();
	}

}
