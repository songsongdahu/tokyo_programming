package tokyo_2010_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _2010_02 {
	//問1
	ArrayList<vertex> V;
	ArrayList<vertex> R;
	public void quest1() throws IOException{
		V = new ArrayList<vertex>();
		V.add(new vertex(0));
		
		boolean first_vflag = true;
		int first_v1000 = -1;
		boolean first_rflag = true;
		int first_r1000 = -1;
		boolean first_cflag = true;
		int first_c1000 = -1;
		
		File file = new File("src/tokyo_2010_02/a.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String nextline;
		int count = 1;
		while((nextline = br.readLine())!=null){
			insert(nextline);
			if(V.size()>=1000&&first_vflag){
				first_v1000 = count;
				first_vflag = false;
			}
			boolean cycle = bfs_r();
			if(R.size()>=1000&&first_rflag){
				first_r1000 = count;
				first_rflag = false;
			}
			if(cycle&&first_cflag){
				first_c1000 = count;
				first_cflag = false;
			}
			count++;
		}
		
		System.out.println("1-1");
		System.out.println("|V|:"+V.size());
		
		int out[] = new int[10001];
		int in[] = new int[10001];
		for(int i=0;i<10001;i++){
			out[i] = 0;
			in[i] = 0;
		}
		for(int i=0;i<V.size();i++){
			vertex v = V.get(i);
			out[v.getNum()] = v.getNexts().size();
			for(int j=0;j<v.getNexts().size();j++){
				in[v.getNexts().get(j).getNum()]++;
			}
		}
		int max_out = 0, max_in = 0;
		int max_out_i = -1, max_in_i = -1;
		for(int i=0;i<10001;i++){
			if(max_out<out[i]){
				max_out = out[i];
				max_out_i = i;
			}
			if(max_in<in[i]){
				max_in = in[i];
				max_in_i = i;
			}
		}
		System.out.println("1-2");
		System.out.println("max_out:"+max_out);
		System.out.println("max_out_i:"+max_out_i);
		System.out.println("max_in:"+max_in);
		System.out.println("max_in_i:"+max_in_i);
		System.out.println("1-3");
		System.out.println("first_v1000:"+first_v1000);
		System.out.println("first_r1000:"+first_r1000);
		System.out.println("1-4");
		System.out.println("first_c1000:"+first_c1000);

		fr.close();
		br.close();
	}
	
	public boolean bfs_r(){
		boolean cycle = false;
		int flag[] = new int[10001];
		Queue<vertex> queue = new LinkedList<vertex>();
		//初期化
		R = new ArrayList<vertex>();
		R.add(V.get(0));
		for(int i=0;i<V.get(0).getNexts().size();i++){
			queue.add(V.get(0).getNexts().get(i));
			R.add(V.get(0).getNexts().get(i));
			int temp_num = V.get(0).getNexts().get(i).getNum();
			if(temp_num==0)
				cycle = true;
			flag[temp_num] = 1;
		}
		//ループ
		vertex nextVertex;
		while((nextVertex = queue.poll()) != null){
			R.add(nextVertex);
			for(int i=0;i<nextVertex.getNexts().size();i++){
				if(flag[nextVertex.getNexts().get(i).getNum()]==0){
					queue.add(nextVertex.getNexts().get(i));
					int temp_num = nextVertex.getNexts().get(i).getNum();
					if(temp_num==0)
						cycle = true;
					flag[temp_num] = 1;
				}
			}
		}
		return cycle;
	}
	
	public void insert(String edge){
		int x = Integer.parseInt(edge.split("->")[0]);
		int y = Integer.parseInt(edge.split("->")[1]);
		boolean insert_x = true;
		boolean insert_y = true;
		int x_pos = -1;
		int y_pos = -1;
		for(int i=0;i<V.size();i++){
			if(x==V.get(i).getNum()){
				insert_x = false;
				x_pos = i;
				break;
			}
		}
		if(insert_x){
			V.add(new vertex(x));
			x_pos = V.size() - 1;
		}
		for(int i=0;i<V.size();i++){
			if(y==V.get(i).getNum()){
				insert_y = false;
				y_pos = i;
				break;
			}
		}
		if(insert_y){
			V.add(new vertex(y));
			y_pos = V.size() - 1;
		}
		if(!V.get(x_pos).hasV(V.get(y_pos))){
			V.get(x_pos).addNext(V.get(y_pos));
		}
	}
	
	//問2
	public void quest2() throws IOException{
		V = new ArrayList<vertex>();
		V.add(new vertex(0));
		
		File file = new File("src/tokyo_2010_02/a.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String nextline;
		int count = 1;
		int size_r1 = 0, size_r2 = 0;
		ArrayList<Integer> count_set = new ArrayList<Integer>();
		while((nextline = br.readLine())!=null){
			if(nextline.charAt(0)=='!'){
				delete(nextline.substring(1));
			} else {
				insert(nextline);
			}
			bfs_r();
			size_r1 = size_r2;
			size_r2 = R.size();
			if(size_r1<1000&&size_r2>=1000){
				count_set.add(count);
			}
			count++;
		}
		
		System.out.println("2-1");
		System.out.println("|V|:"+V.size());
		bfs_r();
		System.out.println("2-2");
		System.out.println("|R|:"+R.size());
		System.out.println("2-3");
		for(int i=0;i<count_set.size();i++){
			System.out.println(count_set.get(i)+" ");
		}
		
		fr.close();
		br.close();
	}
	
	public void delete(String edge){
		int x = Integer.parseInt(edge.split("->")[0]);
		int y = Integer.parseInt(edge.split("->")[1]);
		for(int i=0;i<V.size();i++){
			if(V.get(i).getNum()==x){
				vertex temp = V.get(i);
				for(int j=0;j<temp.getNexts().size();j++){
					if(temp.getNexts().get(j).getNum()==y){
						temp.getNexts().remove(j);
						break;
					}
				}
				break;
			}
		}
	}
	
	//問3
	public void quest3() throws IOException{
		V = new ArrayList<vertex>();
		V.add(new vertex(0));
		
		File file = new File("src/tokyo_2010_02/c.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String nextline;
		int count = 1;
		ArrayList<Integer> count_set = new ArrayList<Integer>();
		while((nextline = br.readLine())!=null){
			if(nextline.charAt(0)=='!'){
				delete(nextline.substring(1));
			} else {
				insert(nextline);
			}
			bfs_r_s1();
			count++;
		}
		
		System.out.println("3-1");
		System.out.println("|V|:"+V.size());
		int size_a = 0;
		for(int i=0;i<V.size();i++){
			size_a += V.get(i).getNexts().size();
		}
		System.out.println("|A|:"+size_a);
		
		fr.close();
		br.close();
	}
	
	public void bfs_r_s1(){
		int flag[] = new int[10001];
		flag[0] = 1;
		Queue<vertex> queue = new LinkedList<vertex>();
		//初期化
		R = new ArrayList<vertex>();
		R.add(V.get(0));
		for(int i=0;i<V.get(0).getNexts().size();i++){
			queue.add(V.get(0).getNexts().get(i));
			R.add(V.get(0).getNexts().get(i));
			int temp_num = V.get(0).getNexts().get(i).getNum();
			flag[temp_num] = 1;
		}
		//ループ
		vertex nextVertex;
		while((nextVertex = queue.poll()) != null){
			R.add(nextVertex);
			for(int i=0;i<nextVertex.getNexts().size();i++){
				if(flag[nextVertex.getNexts().get(i).getNum()]==0){
					queue.add(nextVertex.getNexts().get(i));
					int temp_num = nextVertex.getNexts().get(i).getNum();
					flag[temp_num] = 1;
				}
			}
		}
		//delete
		for(int i=0;i<V.size();i++){
			if(flag[V.get(i).getNum()]==0){
				V.remove(i);
			} else {
				vertex tempVertex = V.get(i);
				for(int j=0;j<tempVertex.getNexts().size();j++){
					if(flag[tempVertex.getNexts().get(j).getNum()]==0){
						tempVertex.getNexts().remove(j);
					}
				}
			}
		}
	}
	
	public void quest3_2() throws IOException{
		V = new ArrayList<vertex>();
		V.add(new vertex(0));
		
		File file = new File("src/tokyo_2010_02/c.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String nextline;
		int count = 1;
		ArrayList<Integer> count_set = new ArrayList<Integer>();
		while((nextline = br.readLine())!=null){
			if(nextline.charAt(0)=='!'){
				delete(nextline.substring(1));
			} else {
				insert(nextline);
			}
			bfs_r_s2();
			count++;
		}
		
		System.out.println("3-2");
		System.out.println("|V|:"+V.size());
		
		fr.close();
		br.close();
	}
	
	public void bfs_r_s2(){
		int in[] = new int[10001];
		in[0] = -10000;
		for(int i=0;i<V.size();i++){
			vertex v = V.get(i);
			for(int j=0;j<v.getNexts().size();j++){
				in[v.getNexts().get(j).getNum()]++;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<V.size();i++){
			if(in[V.get(i).getNum()]==0){
				queue.add(i);
			}
		}
		Integer next;
		while((next = queue.poll()) != null){
			for(int i=0;i<V.get(next).getNexts().size();i++){
				int temp_num = V.get(next).getNexts().get(i).getNum();
				in[temp_num]--;
				if(in[temp_num]==0){
					for(int j=0;j<V.size();j++){
						if(V.get(j).getNum()==temp_num){
							queue.add(j);
							break;
						}
					}
				}
			}
			V.get(next).setNum(-1);
		}
		for(int i=0;i<V.size();i++){
			if(V.get(i).getNum()==-1){
				V.remove(i);
				i--;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		_2010_02 test = new _2010_02();
		//test.quest1();
		//test.quest2();
		test.quest3();
		test.quest3_2();
	}
}
