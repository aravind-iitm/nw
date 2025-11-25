package EXAM;
import java.util.*;
public class hamming{
    static int[] encode(int[] d){
        int r=0; 
        while((1<<r)<d.length+r+1) r++;
        int[] h=new int[d.length+r]; 
        int di=0;
        for(int i=1,hi=0;hi<h.length;i++,hi++)
            h[hi]=((i&(i-1))==0)?0:d[di++];

        for(int i=0;i<r;i++){
            int p=1<<i,par=0;
            for(int j=1;j<=h.length;j++)
                if((j&p)!=0) par^=h[j-1];
            h[p-1]=par;
        }
        return h;
    }

    static int[] decode(int[] h){
        int r=0; 
        while((1<<r)<h.length+1) r++;
        int err=0;
        for(int i=0;i<r;i++){
            int p=1<<i,par=0;
            for(int j=1;j<=h.length;j++)
                if((j&p)!=0) par^=h[j-1];
            if(par==1) err+=p;
        }
        if(err!=0){ 
            System.out.println("Error at bit "+err); 
            h[err-1]^=1; 
        }
        else System.out.println("No error detected.");
        return h;
    }

    static String toStringBits(int[] x){
        StringBuilder sb=new StringBuilder();
        for(int i:x) sb.append(i);
        return sb.toString();
    }

    public static void main(String[] a){
        Scanner s=new Scanner(System.in);
        System.out.print("Enter number of data bits: ");
        int n=s.nextInt();
        int d[]=new int[n];
        System.out.println("Enter data bits :");
        for(int i=0;i<n;i++) d[i]=s.nextInt();

        int h[]=encode(d);
        System.out.println("Hamming Code: "+toStringBits(h));

        System.out.print("Enter error position (0 for none): ");
        int e=s.nextInt(); 
        if(e>0) h[e-1]^=1;

        System.out.println("Sent bits: "+toStringBits(h));
        System.out.println("Corrected bits: "+toStringBits(decode(h)));
    }
}
