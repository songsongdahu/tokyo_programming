package tokyo_2014_02;

public class _2014_02 {
	//(1)
	public int func1(int x){
		if(x<=2)
			return 1;
		return func1(x-1) + func1(x-2);
	}
	
	//(2)
	public long func2(int x){
		if(x<=2)
			return 1;
		else {
			long arr[] = new long[x+1];
			arr[1] = 1;
			arr[2] = 1;
			for(int i=3;i<=x;i++){
				arr[i] = arr[i-1] + arr[i-2];
			}
			return arr[x];
		}
	}
	
	//(3)
	//�ӷ����䵽64λ
	public String str_plus64(String str_a, String str_b){
		int arr_a[] = new int[8];
		int arr_b[] = new int[8];
		//��ȫ��64λ
		while(str_a.length()<64){
			str_a = " " + str_a;
		}
		while(str_b.length()<64){
			str_b = " " + str_b;
		}
		//�ָ��8*8λ��ת��Ϊint
		for(int i=0;i<8;i++){
			try{
				arr_a[i] = Integer.parseInt(str_a.substring(i*8, (i+1)*8).trim());
			} catch (NumberFormatException nfe){
				arr_a[i] = 0;
			}
		}
		for(int i=0;i<8;i++){
			try{
				arr_b[i] = Integer.parseInt(str_b.substring(i*8, (i+1)*8).trim());
			} catch (NumberFormatException nfe){
				arr_b[i] = 0;
			}
		}
		
		//�ֱ����ӷ�
		int carry = 0;//��λ
		int sum[] = new int[8];//���
		for(int i=7;i>=0;i--){
			//�ӵ�λ��ʼ��
			sum[i] = arr_a[i] + arr_b[i] + carry;
			if(sum[i]>=100000000){
				//����н�λ
				sum[i] = sum[i] -100000000;
				carry = 1;
			} else {
				carry = 0;
			}
		}
		//���Ͻ��
		String arr_sum = "";
		for(int i=7;i>=0;i--){
			String temp = ""+sum[i];
			while(temp.length()<8){
				temp = "0" + temp;
			}
			arr_sum = temp + arr_sum;
		}
		if(carry==1){
			arr_sum = "1" + arr_sum;
		}
		//ȥ��0
		while(arr_sum.charAt(0)=='0'){
			arr_sum = arr_sum.substring(1);
		}
		return arr_sum;
	}
	//(4)
	public String func4(int x){
		if(x<=2)
			return "1";
		else {
			String arr[] = new String[x+1];
			arr[1] = "1";
			arr[2] = "1";
			for(int i=3;i<=x;i++){
				arr[i] = str_plus64(arr[i-1],arr[i-2]);
			}
			return arr[x];
		}
	}
	
	//(5)
	//32λ�����˷�
	public String str_mult32(String str_a, String str_b){
		String product = "";
		for(int i=0;i<str_b.length();i++){
			product = product + "0";//��λ
			for(int j=0;j<Integer.parseInt(str_b.charAt(i)+"");j++){
				product = str_plus64(product, str_a);
			}
		}
		return product;
	}
	
	//32λ�������˷�
	public String str_mult32f(String a1, int a2, String b1, int b2){
		//�ȷֱ��str_a1��str_b1������������˷����˴����Ϊԭ�����10^62��
		String product1 = str_mult32(a1, b1);
		//Ȼ���ٴӺ���ȥ��10^62��
		int product2 = a2 + b2 - 62;
		if(product1.length()>=32){
			product2 += product1.length() - 1;
			product1 = product1.substring(0, 32);
		}
		return product1 + " " +product2;
	}
	
	//(6)
	//����ʵ�ּ���
	public String str_sub64(String str_a, String str_b){
		int arr_a[] = new int[8];
		int arr_b[] = new int[8];
		//��ȫ��64λ
		while(str_a.length()<64){
			str_a = " " + str_a;
		}
		while(str_b.length()<64){
			str_b = " " + str_b;
		}
		
		//�ж�ab�Ĵ�С
		int symbol = 0;//0Ϊ�� 1Ϊ��
		for(int i=0;i<64;i++){
			if(str_a.charAt(i)>str_b.charAt(i)){
				symbol = 0;
				break;
			}
			if(str_a.charAt(i)<str_b.charAt(i)){
				symbol = 1;
				break;
			}
		}
		
		//�ָ��8*8λ��ת��Ϊint
		for(int i=0;i<8;i++){
			try{
				arr_a[i] = Integer.parseInt(str_a.substring(i*8, (i+1)*8).trim());
			} catch (NumberFormatException nfe){
				arr_a[i] = 0;
			}
		}
		for(int i=0;i<8;i++){
			try{
				arr_b[i] = Integer.parseInt(str_b.substring(i*8, (i+1)*8).trim());
			} catch (NumberFormatException nfe){
				arr_b[i] = 0;
			}
		}
		
		int sub[] = new int[8];//���
		//�ֱ�������
		if(symbol==0){
			int carry = 0;//��λ
			for(int i=7;i>=0;i--){
				//�ӵ�λ��ʼ��
				sub[i] = arr_a[i] - arr_b[i] - carry;
				if(sub[i]<0){
					//����н�λ
					sub[i] = sub[i] + 100000000;
					carry = 1;
				} else {
					carry = 0;
				}
			}
		} else {
			int carry = 0;//��λ
			for(int i=7;i>=0;i--){
				//�ӵ�λ��ʼ��
				sub[i] = arr_b[i] - arr_a[i] - carry;
				if(sub[i]<0){
					//����н�λ
					sub[i] = sub[i] + 100000000;
					carry = 1;
				} else {
					carry = 0;
				}
			}
		}
		
		//���Ͻ��
		String arr_sub = "";
		for(int i=7;i>=0;i--){
			String temp = ""+sub[i];
			while(temp.length()<8){
				temp = "0" + temp;
			}
			arr_sub = temp + arr_sub;
		}
		//ȥ��0
		while(arr_sub.charAt(0)=='0'&&arr_sub.length()>1){
			arr_sub = arr_sub.substring(1);
		}
		if(symbol==1){
			arr_sub = "-"+arr_sub;
		}
		return arr_sub;
	}
		
	//�������ļ���
	public String str_sub32f(String a1, int a2, String b1, int b2){
		while(a2<b2){
			a1 = "0"+a1.substring(0,31);
			a2++;
		}
		while(b2<a2){
			b1 = "0"+b1.substring(0,31);
			b2++;
		}
		String sum1 = str_sub64(a1,b1);
		int sum2 = a2;
		int sym = 1;//1Ϊ�� 0Ϊ��
		if(sum1.charAt(0)=='-'){
			sym = 0;
			sum1 = sum1.substring(1);
		}
		//��λ
		//Ӧ�ò������32λ ����û��
		if(sum1.length()>32){
			sum1 = sum1.substring(0,32);
			sum2 += sum1.length()-31;
		}
		while(sum1.length()<32){
			sum1 = sum1 + "0";
			sum2 --;
		}
		if(sym==0){
			sum1 = "-" + sum1;
		}
		return sum1+" "+sum2;
	}
	
	public String func6(){
		//��֪�����1-2֮��,���s2��ԶΪ0
		String result = "10000000000000000000000000000000";
		for(int i=0;i<31;i++){
			String plus_0 = "", plus;
			for(int k=0;k<30-i;k++){
				plus_0 += "0";
			}
			for(int j=0;j<10;j++){
				plus = j + plus_0;
				String result1 = str_plus64(result,plus);
				plus = (j + 1) + plus_0;
				String result2 = str_plus64(result,plus);
				//����(2x-1)^2
				String temp1 = str_plus64(result1, result1);
				temp1 = str_sub32f(temp1,0,"10000000000000000000000000000000",0).split(" ")[0];
				temp1 = str_mult32f(temp1,0,temp1,0).split(" ")[0];
				
				String temp2 = str_plus64(result2, result2);
				temp2 = str_sub32f(temp2,0,"10000000000000000000000000000000",0).split(" ")[0];
				temp2 = str_mult32f(temp2,0,temp2,0).split(" ")[0];
				if(temp1.charAt(0)<='4'&&temp2.charAt(0)>='5'){
					result = result1;
					break;
				}
			}
		}
		return result;
	}
	
	//(7)
	public String sqrt_5(){
		//��֪�����2-3֮��,���s2��ԶΪ0
		String result = "20000000000000000000000000000000";
		for(int i=0;i<31;i++){
			String plus_0 = "", plus;
			for(int k=0;k<30-i;k++){
				plus_0 += "0";
			}
			for(int j=0;j<10;j++){
				plus = j + plus_0;
				String result1 = str_plus64(result,plus);
				plus = (j + 1) + plus_0;
				String result2 = str_plus64(result,plus);
				
				String temp1 = str_mult32f(result1, 0, result1, 0).split(" ")[0];
				String temp2 = str_mult32f(result2, 0, result2, 0).split(" ")[0];
				
				if(temp1.charAt(0)<='4'&&temp2.charAt(0)>='5'){
					result = result1;
					break;
				}
			}
		}
		return result;
	}
	
	public String func7(int x ,String sqrt5, String t){
		String result = "10000000000000000000000000000000";
		int r = 0;
		for(int i=0;i<x;i++){
			result = str_mult32f(result, r, t, 0);
			r = Integer.parseInt(result.split(" ")[1]);
			result = result.split(" ")[0];
		}
		result = str_mult32f(result, r, sqrt5, 0);
		r = Integer.parseInt(result.split(" ")[1]);
		result = result.split(" ")[0];
		
		result = str_mult32f(result, r-1, "20000000000000000000000000000000", 0);
		return result;
	}
	//(8)
	//�������Ա� x�������ȷ���1 y�󷵻�0
	public int comp_float(String x1, int x2, String y1, int y2){
		if(x2>y2){
			return 1;
		} else if(y2<x2){
			return 0;
		} else{
			while(x1.length()<32){
				x1 = "0" + x1;
			}
			while(y1.length()<32){
				y1 = "0" + y1;
			}
			for(int i=0;i<32;i++){
				if(x1.charAt(i)>y1.charAt(i)){
					return 1;
				} else if(x1.charAt(i)<y1.charAt(i)) {
					return 0;
				}
			}
			return 1;
		}
	}
	public int func8(){
		String max_1 = "", temp_1;
		int max_2 = -10, temp_2;
		int max_i = 0;
		for(int i=1;i<=140;i++){
			String float_x1 = func7(i,"22360679774997896964091736687312","16180339887498948482045868343656");
			int float_x2 = Integer.parseInt(float_x1.split(" ")[1]);
			float_x1 = float_x1.split(" ")[0];
			
			String float_y1 = func4(i);
			int float_y2 = float_y1.length()-1;
			while(float_y1.length()<32){
				float_y1 = float_y1 + "0";
			}
			
			temp_1 = str_sub32f(float_x1, float_x2, float_y1, float_y2);
			temp_2 = Integer.parseInt(temp_1.split(" ")[1]);
			temp_1 = temp_1.split(" ")[0];
			if(temp_1.charAt(0)=='-'){
				temp_1 = temp_1.substring(1);
			}
			if(comp_float(max_1, max_2, temp_1, temp_2)==0){
				max_1 = temp_1;
				max_2 = temp_2;
				max_i = i;
			}
		}
		return max_i;
	}
	public static void main(String[] args) {
		_2014_02 main = new _2014_02();
		System.out.println(main.func1(10));
		System.out.println(main.func2(10));
		System.out.println(main.func4(140));
		System.out.println(main.str_mult32("140","200000000000000000000000000000001"));
		String sqrt5 = main.sqrt_5();
		String t = main.func6();
		System.out.println(main.func4(2));
		System.out.println(main.func7(2,sqrt5,t));
		System.out.println(main.func6());//16180339887498948482045868343656
		System.out.println(main.sqrt_5());//22360679774997896964091736687312
		
		//System.out.println(main.func8());
	}
}
