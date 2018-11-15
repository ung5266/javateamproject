package dynamic_beat_2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream; //java i.o

import javazoom.jl.player.Player; // javazoom -> JLayer

public class Music extends Thread{//thread 사용. thread - 현재 동작하고 있는 process에서 
	// 한 가지 수행이아닌 두가지 이상의 일을 수행하게 한다.

	private Player player; // Jlayer
	private boolean isLoop; // 현재 곡 무한반복인지 / 한번 재생 후 꺼지는지 
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	//constructor
	public Music(String name,boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			//해당 파일 위치 가져오게 함.
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			//해당 파일버퍼에 담아서 읽어옴.
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {//0.0001 초까지 반환.
		if(player == null) {return 0;}
		return player.getPosition();
	}
	//음악 종료 시키는 메소
	public void close() { 
		isLoop = false;
		player.close();
		this.interrupt();//thread 곡 종료 / 현재곡 종료.
	}
	
	@Override
	//override 로 exception 처리.
	public void run() { // thread - 무조건 써야하는 메소
		try {
			do {
				
			player.play();
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
			}while(isLoop); // isLoop ==true -> 곡 무한반복.
		}catch(Exception e) {
			System.out.println(e.getMessage());//오류 발생시 해당 메시지 출력.
		}
}
}
