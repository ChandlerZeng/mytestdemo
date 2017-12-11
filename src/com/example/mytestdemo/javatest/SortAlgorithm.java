package com.example.mytestdemo.javatest;

import java.util.Arrays;
import java.util.Random;

/** 
* @author  zengcq 
* @date 创建时间：2017年11月23日 上午9:56:11 
* @version 1.0 
*/
public class SortAlgorithm {
	public static void main(String[] args){
		int[] numbers = {10,20,15,0,6,7,2,1,-5,55,1};
		shellSort(numbers);
		System.out.println(Arrays.toString(numbers));
//		bubleSort(numbers);
//		int[] numbers2 = {10,20,15,0,6,7,2,1,-5,55,1};
//		quickSort(numbers2, 0, numbers2.length-1);
//		int[] numbers3 = {10,20,15,0,6,7,2,1,-5,55,1};
//		selectSort(numbers3);
//		
//		int[] numbers4 = {10,20,15,0,6,7,2,1,-5,55,1};
//		insertSort(numbers4);
//		
//		int[] numbers5 = {10,20,15,0,6,7,2,1,-5,55,1};
//		shellSort(numbers5);
		bubleSort(getRandomArray());
		long quicktime = System.currentTimeMillis();
		int[] arrayquick = getRandomArray();
		quickSort(arrayquick, 0, arrayquick.length-1);
		long cost_time = System.currentTimeMillis()-quicktime;
		System.out.println("random quickSort:"+" costtime:"+cost_time);
//		System.out.println(builder.toString());
		
		long mergetime = System.currentTimeMillis();
		int[] arraymerge = getRandomArray();
		mergeSort(arraymerge, 0, arraymerge.length-1);
		long cost_time3 = System.currentTimeMillis()-mergetime;
		System.out.println("random mergeSort:"+" costtime:"+cost_time3);
		
		long mergetime2 = System.currentTimeMillis();
		int[] arraymerge2 = getSortArray();
		mergeSort(arraymerge2, 0, arraymerge2.length-1);
		long cost_time32 = System.currentTimeMillis()-mergetime2;
		System.out.println("sort mergeSort:"+" costtime:"+cost_time32);
		
		shellSort(getRandomArray());
		bubleSort(getRandomArray());
		selectSort(getRandomArray());
		insertSort(getRandomArray());
		heapSort(getRandomArray());
		heapSort(getSortArray());
		
		long arraySortTime = System.currentTimeMillis();
		Arrays.sort(getRandomArray());
		long cost_timeArraySort = System.currentTimeMillis()-arraySortTime;
		System.out.println("Random Arrays.sort:"+" costtime:"+cost_timeArraySort);
		
		
		long arraySortTime2 = System.currentTimeMillis();
		Arrays.sort(getSortArray());
		long cost_timeArraySort2 = System.currentTimeMillis()-arraySortTime2;
		System.out.println("Sort Arrays.sort:"+" costtime:"+cost_timeArraySort2);
		
		
		
//		bubleSort(getSortArray());
		long quicktime2 = System.currentTimeMillis();
		int[] arrayquick2 = getSortArray();
		quickSort(arrayquick2, 0, arrayquick2.length-1);
		long cost_time2 = System.currentTimeMillis()-quicktime2;
		System.out.println("sort quickSort:"+" costtime:"+cost_time2);
//		System.out.println(builder.toString());
		
		shellSort(getSortArray());
		bubleSort(getSortArray());
		selectSort(getSortArray());
		insertSort(getSortArray());
		
	}
	private static int[] getSortArray(){
		int n = 500;
		int[] array = new int[n];
		for(int i=0;i<n;i++){
			array[i] = i;
		}
		return array;
	}
	
	private static int[] getRandomArray(){
		int n = 500;
		if(n>0){
			int[] array = new int[n];
			Random random = new Random();
			for(int i=0;i<n;i++){
				array[i] = random.nextInt(100000);
			}
			return array;
		}
		return null;
		
	}
	/**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。  
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。  
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。 
     * @param numbers 需要排序的整型数组
     */
	public static void bubleSort(int[] array){
		long time = System.currentTimeMillis();
		int temp;
		for(int i=0;i<array.length-1;i++){
			for(int j=0;j<array.length-1-i;j++){
				if((array[j])>(array[j+1])){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		long cost = System.currentTimeMillis()-time;
//		System.out.println("bubleSort:"+Arrays.toString(array)+" cost time:"+cost);
		System.out.println("bubleSort:"+" cost time:"+cost);
	}
	
	/*把整个序列看做一个数组，把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；
	交换了以后再和小的那端比，比它小不交换，比他大交换。这样循环往复，一趟排序完成，左边就是比中轴小的，
	右边就是比中轴大的，然后再用分治法，分别对这两个独立的数组进行排序。*/
	static StringBuilder builder = new StringBuilder();
	private static int getMiddle(int[] array,int low,int high){
		int temp = array[low];
		while(low<high){
			while(low<high && array[high]>=temp){
				high--;
			}
			array[low] = array[high];
			while(low<high && array[low]<=temp){
				low++;
			}
			array[high] = array[low];
		}
		array[low] = temp;
//		builder.append(low);
//		builder.append(",");
		return low;
	}
	
	public static void quickSort(int[] array, int low, int high){
		if(low<high){
			int middle = getMiddle(array, low, high);
			quickSort(array, middle+1, high);
			quickSort(array, low, middle-1);
//			System.out.println(Arrays.toString(array));
		}
	}
	
	/**
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置  
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。 
     * 以此类推，直到所有元素均排序完毕。 
     * @param numbers
     */
	public static void selectSort(int[] array){
		long time = System.currentTimeMillis();
		int temp;
		int k;
		for(int i=0;i<array.length;i++){
			k = i;
			for(int j=i;j<array.length;j++){
				if(array[j]<array[k]){
					k = j;
				}
			}
			temp = array[i];
			array[i] = array[k];
			array[k] = temp;
			
		}
		long cost = System.currentTimeMillis()-time;
		System.out.println("selectSort:"+" costtime:"+cost);
//		System.out.println("selectSort:"+Arrays.toString(array)+" costtime:"+cost);
//		long costafter = System.currentTimeMillis()-time;
//		System.out.println("selectSort:"+" costtime:"+costafter);
		
	}
	
	/**  
     * 插入排序
     * 
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描 
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置  
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置  
     * 将新元素插入到该位置中  
     * 重复步骤2  
     * @param numbers  待排序数组
     */  
	public static void insertSort(int[] array){
		long time = System.currentTimeMillis();
		int temp;
		int j;
		for(int i=0;i<array.length;i++){
			temp = array[i];
			for(j=i;j>0&&temp<array[j-1];j--){
				array[j] = array[j-1];
			}
			
			array[j] = temp;
		}
		long cost = System.currentTimeMillis()-time;
		System.out.println("insertSort:"+" costtime:"+cost);
//		System.out.println("insertSort:"+Arrays.toString(array)+" costtime:"+cost);
//		long costafter = System.currentTimeMillis()-time;
//		System.out.println("insertSort:"+" costtime:"+costafter);
		
	}
	
	public static void insert_sort(int[] array){
		int temp;
		int j;
		for(int i=0;i<array.length;i++){
			temp = array[i];
			for(j=i;j>0&&temp<array[j-1];j--){
				array[j] = array[j-1];
			}
			array[j] = temp;
		}
	}
	
	/**希尔排序的原理:根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，然后将较大值移到前面，较小值
	 * 移到后面，最后将整个数组进行插入排序，这样比起一开始就用插入排序减少了数据交换和移动的次数，可以说希尔排序是加强
	 * 版的插入排序
	 * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
	 * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
	 * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
	 * 第一次后increment的值变为3/2=1,此时对数组进行插入排序，
	 *实现数组从大到小排
	 */
	public static void shellSort(int[] array){
		long time = System.currentTimeMillis();
		int temp;
		int j;
		for(int inclement = array.length/2;inclement>0;inclement/=2){
			for(int i = inclement;i<array.length;i++){
				temp = array[i];
				for(j = i;j>=inclement;j-=inclement){
					if(temp<array[j-inclement]){
						array[j] = array[j-inclement];
					}else{
						break;
					}
				}
				array[j] = temp;
			}
		}
		long cost = System.currentTimeMillis()-time;
//		System.out.println("shellSort:"+Arrays.toString(array)+" costtime:"+cost);
		System.out.println("shellSort:"+" costtime:"+cost);
	}
	
	/**
     * 归并排序
     * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
     * 时间复杂度为O(nlogn)
     * 稳定排序方式
     * @param nums 待排序数组
     * @return 输出有序数组
     */
    public static int[] mergeSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
        	mergeSort(nums, low, mid);
            // 右边
        	mergeSort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    /**
     * 将数组中low到high位置的数进行排序
     * @param nums 待排序数组
     * @param low 待排的开始位置
     * @param mid 待排中间位置
     * @param high 待排结束位置
     */
    public static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }
    
    public static void heapSort(int[] data){
    	long time = System.currentTimeMillis();
    	int arrayLength=data.length;  
        //循环建堆  
        for(int i=0;i<arrayLength-1;i++){  
            //建堆  
            buildMaxHeap(data,arrayLength-1-i);  
            //交换堆顶和最后一个元素  
            swap(data,0,arrayLength-1-i);  
        }  
        
        long cost = System.currentTimeMillis()-time;
		System.out.println("heapSort:"+" costtime:"+cost);
    }
    
    //对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex){
         //从lastIndex处节点（最后一个节点）的父节点开始 
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点 
            int k=i;
            //如果当前k节点的子节点存在  
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引 
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex<lastIndex){  
                    //若果右子节点的值较大  
                    if(data[biggerIndex]<data[biggerIndex+1]){  
                        //biggerIndex总是记录较大子节点的索引  
                        biggerIndex++;  
                    }  
                }  
                //如果k节点的值小于其较大的子节点的值  
                if(data[k]<data[biggerIndex]){  
                    //交换他们  
                    swap(data,k,biggerIndex);  
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  
                    k=biggerIndex;  
                }else{  
                    break;  
                }  
            }
        }
    }
    //交换
    private static void swap(int[] data, int i, int j) {  
        int tmp=data[i];  
        data[i]=data[j];  
        data[j]=tmp;  
    } 
	
}
