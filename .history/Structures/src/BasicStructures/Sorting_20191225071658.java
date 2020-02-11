package BasicStructures;

public class Sorting<T extends Comparable<T>>{
	T [] arr;

	public Sorting(T[] arr) {
		super();
		this.arr = arr;
	}
	
	public T[] getArr() {
		return arr;
	}

	public void setArr(T[] arr) {
		this.arr = arr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<arr.length;i++) {
			sb.append(arr[i]+" ");
		}
		return sb.toString();
	}
	
	public void BubleSort() {
		System.out.println("Buble Sorting");
		for(int i = 0;i<arr.length;i++) {
			for(int j = 0;j<arr.length-1;j++) {
				if(j+1<arr.length) {
					if(arr[j].compareTo(arr[j+1])==1) {
						T temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
					}
				}
			}
		}
	}
	
	public void selectionSort() {
		System.out.println("selection soting");
		for(int i =0;i<arr.length;i++) {
			int minimum = i;
			for(int j =i;j<arr.length;j++) {
				if(arr[j].compareTo(arr[minimum])==-1) {
					minimum = j;
				}
			}
			T temp = arr[i];
			arr[i] = arr[minimum];
			arr[minimum] = temp;
		}
	}
	
	public void insertionSorting() {
		System.out.println("Insertion sorting");
		for(int i = 1;i<arr.length;i++) {
			for(int j=0;j<=i;j++) {
				if(arr[j].compareTo(arr[i])==1) {
					T temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
	
	public void QuickSort() {
		QuickSort(arr,0,(arr.length-1));
	}
	int pivotal; 
	private void QuickSort(T []arr,int start , int last) {
		if(start>=last)
			return;
		else {
			int pivotal = partition(arr, start, last);
			if(this.pivotal==pivotal)
				return;
			else
				this.pivotal=pivotal;
			System.out.println("start "+start+" last "+last+" pivotal "+pivotal);
			QuickSort(arr, start, pivotal-1);
			QuickSort(arr, pivotal+1, last);
		}
	}
	
	private int partition(T arr[],int start,int last) {
		int pivotal = start;
		int min_index = start;
		while(start<=last){	
			while((arr[start].compareTo(arr[pivotal])==-1||arr[start].compareTo(arr[pivotal])==0)) {
				start++;
			}
			while((arr[last].compareTo(arr[pivotal])==1||arr[last].compareTo(arr[pivotal])==0)) {
				last--;
			}
			if(start<last) {
				T temp = arr[start];
				arr[start] = arr[last];
				arr[last] = temp;
			}
		}
		
		if(last>=min_index) {
			T temp = arr[last];
			arr[last] = arr[pivotal];
			arr[pivotal] = temp;
		}
		System.out.println(this.toString());
		return last;
	}

	public static void sortOddEven(int[]arr){
		int []arr1 = new int[arr.length];
		int even = arr.length-1;
		int odd = 0;
		for (int i = 0; i < arr1.length; i++) {
			if(arr[i]%2==0){
				arr1[even--]= arr[i];
			}
			else{
				arr[odd++]= arr[i];
			}
		}
	}
}

class TestSort{
	public static void main(String[] args) {
		Integer []arr = new Integer[10];
		int []arr1= {76,13,106,154,284,21,15,330,421,75};
		System.out.println();
		for(int i =0;i<10;i++) {
			//((Math.random()*31)*3.1452*i);
			arr[i] = arr1[i];
		}
		Sorting<Integer> s = new Sorting<>(arr);
		System.out.println(s);
		s.sortOddEven();
	}

	public static void randomise(Integer[] arr) {
		for(int i =0;i<arr.length;i++) {
			arr[i] = (int) ((Math.random()*31)*3.1452*(i+1));
		}
	}
}