package cobas.coding.apicollection.memes;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Slf4j
public class PictureDownloader {

	private String fileDestination;

	private String fileFormat;

	public PictureDownloader(String fileDestination, String fileFormat) {
		this.fileDestination = fileDestination;
		this.fileFormat = fileFormat;
	}

	public byte[] load(String url, String name) {
		log.info("Download Meme: {}", name);
		name = name.replaceAll("[^a-zA-Z0-9\\-]", "_");
		name = checkName(name);
		try {
			byte[] result = download(url);
			saveToFile(result, name);
			return result;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	private void saveToFile(byte[] imageArray, String name) throws IOException {
		FileOutputStream fos = new FileOutputStream(this.fileDestination+name+"."+fileFormat);
		fos.write(imageArray);
		fos.close();
	}
	private String checkName(String name){
		File f = new File(this.fileDestination+name+"."+fileFormat);
		if(f.exists() && !f.isDirectory()) {
			return name+ UUID.randomUUID();
		}
		return name;
	}

	private byte[] download(String downloadLink) throws MalformedURLException {
		URL url = new URL(downloadLink);
		try(InputStream in = new BufferedInputStream(url.openStream()); ByteArrayOutputStream out = new ByteArrayOutputStream()){
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
				out.write(buf, 0, n);
			}
			return out.toByteArray();
		}catch (IOException e){
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
