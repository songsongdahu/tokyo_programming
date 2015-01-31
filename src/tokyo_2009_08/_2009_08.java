package tokyo_2009_08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class _2009_08 {
	//問2
	public void quest2() throws IOException{
		File file = new File("src/tokyo_2009_08/1000.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String nextline;
		int menseki = 0;
		while((nextline = br.readLine())!=null){
			String[] next = nextline.split(" ");
			menseki += Integer.parseInt(next[2]) * Integer.parseInt(next[3]);
		}
		System.out.println(menseki);
		br.close();
		fr.close();
	}
	
	//問3
	public void quest3() throws IOException{
		//3-1
		File file = new File("src/tokyo_2009_08/1000.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		//存放输入的数据
		ArrayList<String> input = new ArrayList<String>();
		String nextline;
		while((nextline = br.readLine())!=null){
			input.add(nextline);
		}
		int atusa[][] = new int[999][999];
		for(int i=0;i<999;i++){
			for(int j=0;j<999;j++){
				atusa[i][j] = 0;
			}
		}
		for(int k=0;k<input.size();k++){
			String input_str[] = input.get(k).split(" ");
			int inputs[] = new int[4];
			for(int index=0;index<4;index++){
				inputs[index] = Integer.parseInt(input_str[index]);
			}
			for(int i=inputs[0];i<inputs[0]+inputs[2];i++){
				for(int j=inputs[1];j<inputs[1]+inputs[3];j++){
					atusa[i][j]++;
				}
			}
		}
		int max = 0, max_i = -1, max_j = -1;
		for(int i=0;i<999;i++){
			for(int j=0;j<999;j++){
				if(max<atusa[i][j]){
					max = atusa[i][j];
					max_i = i;
					max_j = j;
				}
			}
		}
		System.out.println("max:"+max_i+" "+max_j+" "+max);
		//3-2
		ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>();
		for(int i=0;i<999;i++){
			total.add(new ArrayList<String>());
			total.get(total.size()-1).add(input.get(i));
		}
		int size;
		do{
			size = total.size();
			for(int i=0;i<total.size();i++){
				for(int j=i+1;j<total.size();j++){
					if(merge(total.get(i), total.get(j))){
						total.get(i).addAll(total.get(j));
						total.remove(j);
					}
				}
			}
		} while(size!=total.size());
		System.out.println(total.size());
		//3-3
		int max_size = 0, max_size_i = -1; 
		for(int i=0;i<total.size();i++){
			if(max_size<total.get(i).size()){
				max_size = total.get(i).size();
				max_size_i = i;
			}
		}
		System.out.println("max_size:" + max_size);
		System.out.println("max_size_i:" + max_size_i);
		//3-4
		int max_menseki = 0, max_menseki_i = -1;
		for(int i=0;i<total.size();i++){
			if(max_menseki<menseki(total.get(i))){
				max_menseki = menseki(total.get(i));
				max_menseki_i = i;
			}
		}
		System.out.println("max_menseki:" + max_menseki);
		System.out.println("max_menseki_i:" + max_menseki_i);
		br.close();
		fr.close();
	}
	
	public int menseki(ArrayList<String> arr){
		int menseki = 0;
		int menseki_arr[][] = new int[999][999];
		for(int k=0;k<arr.size();k++){
			String arr_str[] = arr.get(k).split(" ");
			int arr_int[] = new int[4];
			for(int index=0;index<4;index++){
				arr_int[index] = Integer.parseInt(arr_str[index]);
			}
			for(int i=arr_int[0];i<arr_int[0]+arr_int[2];i++){
				for(int j=arr_int[1];j<arr_int[1]+arr_int[3];j++){
					menseki_arr[i][j] = 1;
				}
			}
		}
		for(int i=0;i<999;i++){
			for(int j=0;j<999;j++){
				menseki += menseki_arr[i][j];
			}
		}
		return menseki;
	}
	
	public boolean merge(ArrayList<String> arr1, ArrayList<String> arr2){
		boolean merge = false;
		for(int i=0;i<arr1.size();i++){
			for(int j=0;j<arr2.size();j++){
				String str_a[] = arr1.get(i).split(" ");
				String str_b[] = arr2.get(j).split(" ");
				int a[] = new int[4], b[] = new int[4];
				for(int k=0;k<4;k++){
					a[k] = Integer.parseInt(str_a[k]);
					b[k] = Integer.parseInt(str_b[k]);
				}
				if(a[0]+a[2]<b[0]||b[0]+b[2]<a[0]){
					
				} else if(a[1]+a[3]<b[1]||b[1]+b[3]<a[1]){
					
				} else {
					int s = 0;
					if(a[0]==b[0]||a[1]==b[1])
						s++;
					if(a[0]==b[0]+b[2]||a[1]==b[1])
						s++;
					if(a[0]==b[0]||a[1]==b[1]+b[3])
						s++;
					if(a[0]==b[0]+b[2]||a[1]==b[1]+b[3])
						s++;
					if(a[0]+a[2]==b[0]||a[1]==b[1])
						s++;
					if(a[0]+a[2]==b[0]+b[2]||a[1]==b[1])
						s++;
					if(a[0]+a[2]==b[0]||a[1]==b[1]+b[3])
						s++;
					if(a[0]+a[2]==b[0]+b[2]||a[1]==b[1]+b[3])
						s++;
					if(a[0]==b[0]||a[1]+a[3]==b[1])
						s++;
					if(a[0]==b[0]+b[2]||a[1]+a[3]==b[1])
						s++;
					if(a[0]==b[0]||a[1]+a[3]==b[1]+b[3])
						s++;
					if(a[0]==b[0]+b[2]||a[1]+a[3]==b[1]+b[3])
						s++;
					if(a[0]+a[2]==b[0]||a[1]+a[3]==b[1])
						s++;
					if(a[0]+a[2]==b[0]+b[2]||a[1]+a[3]==b[1])
						s++;
					if(a[0]+a[2]==b[0]||a[1]+a[3]==b[1]+b[3])
						s++;
					if(a[0]+a[2]==b[0]+b[2]||a[1]+a[3]==b[1]+b[3])
						s++;
					if(s!=1){
						merge = true;
					}
				}
			}
		}
		return merge;
	}
	
	public static void main(String[] args) throws IOException {
		_2009_08 test = new _2009_08();
		test.quest3();
	}
}
