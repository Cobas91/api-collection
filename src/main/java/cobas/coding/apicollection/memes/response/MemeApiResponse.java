package cobas.coding.apicollection.memes.response;

import lombok.Data;

@Data
public class MemeApiResponse {
	private String postLink;
	private String subreddit;
	private String title;
	private String url;
	private String nsfw;
	private String spoiler;
	private String author;
	private int ups;

}
