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
		for (int i : arr1) {
			System.out.print(i+" ");
		}
		System.out.println();
		arr1 = sortOddEven(arr1);
		//indexSelection(arr1, 0, 0,false);
		for (int i : arr1) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

	public static void indexSelection(int[]arr,int start, int end,boolean assending){
		if(assending){
			for(int i = start;i<=end;i++){
				int min_index = i;
				for(int j = i;j<=end;j++){
					if(arr[j]<arr[min_index])
						min_index = j; 
				}
				int temp = arr[i];
				arr[i] = arr[min_index];
				arr[min_index] = temp; 	
			}
		}
		else{
			for(int i = start;i<=end;i++){
				int min_index = i;
				for(int j = i;j<=end;j++){
					if(arr[j]>arr[min_index])
						min_index = j; 
				}
				int temp = arr[i];
				arr[i] = arr[min_index];
				arr[min_index] = temp; 	
			}
		}
	}
	public static int[] sortOddEven(int[]arr){
		int []arr1 = new int[arr.length];
		int even = arr.length-1;
		int odd = 0;
		for (int i = 0; i < arr1.length; i++) {
			if(arr[i]%2==0){
				arr1[even--]= arr[i];
				indexSelection(arr, even, arr.length-1,false);
			}
			else{
				arr1[odd++]= arr[i];
				indexSelection(arr, 0, odd,true);
			}
			for (int i : arr) {
				System.out.print(i+" ");
			}	
		}
		return arr1;
	}

	public static void randomise(Integer[] arr) {
		for(int i =0;i<arr.length;i++) {
			arr[i] = (int) ((Math.random()*31)*3.1452*(i+1));
		}
	}
}