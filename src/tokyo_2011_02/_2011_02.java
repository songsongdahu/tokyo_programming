package tokyo_2011_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class _2011_02 {
	private int[][] banmen = new int[3][3];
	
	public _2011_02(){
		initBanmen();
	}
	public void initBanmen(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				banmen[i][j] = 0;
			}
		}
	}
	//(1)
	public void showBanmen(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(banmen[i][j] == 0){
					System.out.print("-");
				} else if(banmen[i][j] == 1){
					System.out.print("O");
				} else if(banmen[i][j] == 2){
					System.out.print("X");
				} else {
					System.out.println("banmen data error");
				}
			}
			System.out.println();
		}
	}
	
	public void quest1(){
		banmen[0][0] = 1;
		banmen[0][1] = 2;
		banmen[1][0] = 1;
		banmen[1][1] = 2;
		banmen[1][2] = 2;
		banmen[2][0] = 1;
		banmen[2][1] = 1;
		System.out.println("鍟�1:");
		showBanmen();
	}
	
	//(2)
	public boolean input(String location, int player){
		if(location.length()!=2){
			System.out.println("input error, please input again:");
			return false;
		} else {
			int x,y;
			switch(location.charAt(0)){
			case '1':
				x = 0;break;
			case '2': 
				x = 1;break;
			case '3':
				x = 2;break;
			default:
				System.out.println("input error, please input again:");
				return false;
			}
			switch(location.charAt(1)){
			case 'A':
				y = 0;break;
			case 'B': 
				y = 1;break;
			case 'C':
				y = 2;break;
			default:
				System.out.println("input error, please input again:");
				return false;
			}
			if(banmen[x][y]!=0){
				System.out.println("input error, please input again:");
				return false;
			}
			if(player==1){
				banmen[x][y] = 1;
			} else if(player==2){
				banmen[x][y] = 2;
			} else {
				System.out.println("player error");
				return false;
			}
			return true;
		}
	}
	/*
	 * -1 銇俱仩
	 * 0 寮曘亶鍒嗐亼
	 * 1 player1 win
	 * 2 player2 win
	 */
	public int gameOver(){
		for(int i=0;i<3;i++){
			if(banmen[i][0]==1&&banmen[i][1]==1&&banmen[i][2]==1){
				return 1;
			}
			if(banmen[0][i]==1&&banmen[1][i]==1&&banmen[2][i]==1){
				return 1;
			}
			if(banmen[i][0]==2&&banmen[i][1]==2&&banmen[i][2]==2){
				return 2;
			}
			if(banmen[0][i]==2&&banmen[1][i]==2&&banmen[2][i]==2){
				return 2;
			}
		}
		if(banmen[0][0]==1&&banmen[1][1]==1&&banmen[2][2]==1){
			return 1;
		}
		if(banmen[0][2]==1&&banmen[1][1]==1&&banmen[2][0]==1){
			return 1;
		}
		if(banmen[0][0]==2&&banmen[1][1]==2&&banmen[2][2]==2){
			return 2;
		}
		if(banmen[0][2]==2&&banmen[1][1]==2&&banmen[2][0]==2){
			return 2;
		}
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(banmen[i][j]==0){
					return -1;
				}
			}
		}
		return 0;
	}
	
	public void gameStart2(){
		int gameOver = gameOver();
		int turn = 1;//1 is player1;2 is player2
		Scanner scan = new Scanner(System.in);
		
		System.out.println("閵堝眰鍏楅妷鐘仯閵堣￥鍏楅妷锟�:");
		showBanmen();
		while(gameOver==-1){
			System.out.println("player"+turn+" input:");
			String input = scan.nextLine();
			while(!input(input, turn)){
				input = scan.nextLine();
			}
			if(turn == 1){
				turn = 2;
			} else {
				turn = 1;
			}
			gameOver = gameOver();
			showBanmen();
		}
		if(gameOver==0){
			System.out.println("寮曘亶鍒嗐亼");
		} else if(gameOver==1){
			System.out.println("player1 鍕濄仱");
		} else if(gameOver==2){
			System.out.println("player2 鍕濄仱");
		}
		System.out.println("銈层兗銉犮偑銉笺儛銉�");
	}
	
	//(3)
	public String randomInput(){
		ArrayList<String> arr = new ArrayList<String>();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(banmen[i][j]==0){
					arr.add(""+(i+1)+(char)(j+65));
				}
			}
		}
		int ran = (int) (Math.random()*arr.size());
		return arr.get(ran);
	}
	
	public void gameStart3(){
		int gameOver = gameOver();
		int turn = 1;//1 is player1;2 is com
		Scanner scan = new Scanner(System.in);
		
		System.out.println("銈层兗銉犮偣銈裤兗銉�:");
		showBanmen();
		while(gameOver==-1){
			if(turn == 1){
				System.out.println("player1 input:");
				String input = scan.nextLine();
				while(!input(input, turn)){
					input = scan.nextLine();
				}
				turn = 2;
			} else if(turn == 2){
				System.out.println("computer input:");
				String input = randomInput();
				System.out.println(input);
				input(input, turn);
				turn = 1;
			}
			gameOver = gameOver();
			showBanmen();
		}
		if(gameOver==0){
			System.out.println("寮曘亶鍒嗐亼");
		} else if(gameOver==1){
			System.out.println("player1 鍕濄仱");
		} else if(gameOver==2){
			System.out.println("com 鍕濄仱");
		}
		System.out.println("銈层兗銉犮偑銉笺儛銉�");
	}
	
	//(4)
	public void gameStart4() throws InterruptedException{
		int gameOver = gameOver();
		int turn = 1;//1 is com1;2 is com2
		Scanner scan = new Scanner(System.in);
		
		System.out.println("銈层兗銉犮偣銈裤兗銉�:");
		showBanmen();
		while(gameOver==-1){
			Thread.sleep(1000);
			if(turn == 1){
				System.out.println("com1 input:");
				String input = randomInput();
				System.out.println(input);
				input(input, turn);
				turn = 2;
			} else if(turn == 2){
				System.out.println("com2 input:");
				String input = randomInput();
				System.out.println(input);
				input(input, turn);
				turn = 1;
			}
			gameOver = gameOver();
			showBanmen();
		}
		if(gameOver==0){
			System.out.println("寮曘亶鍒嗐亼");
		} else if(gameOver==1){
			System.out.println("com1 鍕濄仱");
		} else if(gameOver==2){
			System.out.println("com2 鍕濄仱");
		}
		System.out.println("銈层兗銉犮偑銉笺儛銉�");
	}
	
	//(5)
	public String wisdomInput(){
		//k=2銆�銇嬨仾銈夈仛鍕濄仱 k=1 銇嬨仾銈夈仛璨犮亼銈�
		for(int k=2;k>=1;k--){
			for(int i=0;i<3;i++){
				if(banmen[i][0]==0&&banmen[i][1]==k&&banmen[i][2]==k){
					return (i+1)+"A";
				}
				if(banmen[i][0]==k&&banmen[i][1]==0&&banmen[i][2]==k){
					return (i+1)+"B";
				}
				if(banmen[i][0]==k&&banmen[i][1]==k&&banmen[i][2]==0){
					return (i+1)+"C";
				}
				if(banmen[0][i]==0&&banmen[1][i]==k&&banmen[2][i]==k){
					return "1"+(char)(i+65);
				}
				if(banmen[0][i]==k&&banmen[1][i]==0&&banmen[2][i]==k){
					return "2"+(char)(i+65);
				}
				if(banmen[0][i]==k&&banmen[1][i]==k&&banmen[2][i]==0){
					return "3"+(char)(i+65);
				}
			}
			if(banmen[0][0]==0&&banmen[1][1]==k&&banmen[2][2]==k){
				return "1A";
			}
			if(banmen[0][0]==k&&banmen[1][1]==0&&banmen[2][2]==k){
				return "2B";
			}
			if(banmen[0][0]==k&&banmen[1][1]==k&&banmen[2][2]==0){
				return "3C";
			}
			if(banmen[0][2]==0&&banmen[1][1]==k&&banmen[2][0]==k){
				return "1C";
			}
			if(banmen[0][2]==k&&banmen[1][1]==0&&banmen[2][0]==k){
				return "2B";
			}
			if(banmen[0][2]==k&&banmen[1][1]==k&&banmen[2][0]==0){
				return "3A";
			}
		}
		
		return randomInput();
	}
	
	//(6)
	public void gameStart6(){
		int gameOver = gameOver();
		int turn = 1;//1 is player1;2 is com
		Scanner scan = new Scanner(System.in);
		
		System.out.println("銈层兗銉犮偣銈裤兗銉�:");
		showBanmen();
		while(gameOver==-1){
			if(turn == 1){
				System.out.println("player1 input:");
				String input = scan.nextLine();
				while(!input(input, turn)){
					input = scan.nextLine();
				}
				turn = 2;
			} else if(turn == 2){
				System.out.println("computer input:");
				String input = wisdomInput();
				System.out.println(input);
				input(input, turn);
				turn = 1;
			}
			gameOver = gameOver();
			showBanmen();
		}
		if(gameOver==0){
			System.out.println("寮曘亶鍒嗐亼");
		} else if(gameOver==1){
			System.out.println("player1 鍕濄仱");
		} else if(gameOver==2){
			System.out.println("com 鍕濄仱");
		}
		System.out.println("銈层兗銉犮偑銉笺儛銉�");
	}
	
	//(7)
	static final int PORT = 10000;
	static final String HOST = "127.0.0.1";
	public void startServer() throws IOException, InterruptedException{
		int gameOver = gameOver();
		int turn = 1;//1 is com1;2 is com2
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("wait player 2...");
		Socket socket = serverSocket.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
		String str = br.readLine();
		System.out.println(str);
		
		System.out.println("銈层兗銉犮偣銈裤兗銉�:");
		showBanmen();
		while(gameOver==-1){
			Thread.sleep(1000);
			if(turn == 1){
				System.out.println("com1 input:");
				String input = wisdomInput();
	            pw.println(input);
				System.out.println(input);
				input(input, turn);
				turn = 2;
			} else if(turn == 2){
				System.out.println("com2 input:");
				String input = br.readLine();
				System.out.println(input);
				input(input, turn);
				turn = 1;
			}
			gameOver = gameOver();
			showBanmen();
		}
		if(gameOver==0){
			System.out.println("寮曘亶鍒嗐亼");
		} else if(gameOver==1){
			System.out.println("com1 鍕濄仱");
		} else if(gameOver==2){
			System.out.println("com2 鍕濄仱");
		}
		System.out.println("銈层兗銉犮偑銉笺儛銉�");
		socket.close();
		serverSocket.close();
	}
	
	public void startClient() throws InterruptedException, UnknownHostException, IOException{
		int gameOver = gameOver();
		int turn = 1;//1 is com1;2 is com2
		
		Socket socket = new Socket(HOST,PORT);
		PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader br =new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw.println("player2 entered.");
		
		System.out.println("銈层兗銉犮偣銈裤兗銉�:");
		showBanmen();
		while(gameOver==-1){
			Thread.sleep(1000);
			if(turn == 1){
				System.out.println("com1 input:");
				String input = br.readLine();
				System.out.println(input);
				input(input, turn);
				turn = 2;
			} else if(turn == 2){
				System.out.println("com2 input:");
				String input = wisdomInput();
	            pw.println(input);
				System.out.println(input);
				input(input, turn);
				turn = 1;
			}
			gameOver = gameOver();
			showBanmen();
		}
		if(gameOver==0){
			System.out.println("寮曘亶鍒嗐亼");
		} else if(gameOver==1){
			System.out.println("com1 鍕濄仱");
		} else if(gameOver==2){
			System.out.println("com2 鍕濄仱");
		}
		System.out.println("銈层兗銉犮偑銉笺儛銉�");
		socket.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		_2011_02 main = new _2011_02();
		//main.quest1();
		//main.gameStart2();
		//main.gameStart3();
		main.gameStart6();
	}
}
