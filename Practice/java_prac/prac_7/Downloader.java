package Practice;

import java.lang.Thread;

public class Downloader {
	
	private OnDownloadListener mListener;
	
	public Downloader(OnDownloadListener listener) {
		mListener = listener;
	}
	public void start() {
		System.out.println("Download Start");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		mListener.onDownFinish();
	}
}
