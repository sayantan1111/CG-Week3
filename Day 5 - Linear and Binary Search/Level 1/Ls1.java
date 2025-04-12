import java.util.*;
class Ls1{
    public static int idx(int [] arr){
       
        for(int i=0;i<arr.length;i++){
            if(arr[i]<0){ 
                return i;
            }
                
        }
        return -1;
    }
    public static void main(String [] args){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int []arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=s.nextInt();
        }
        
        int result=idx(arr);
        System.out.println(result);
        s.close();
    }
}