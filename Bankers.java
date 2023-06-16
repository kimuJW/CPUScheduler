import java.util.Scanner;
public class Bankers {
	private int need[][],allocate[][],max[][],avail[],numP,numR;

	private void input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("201801544 Jinwon Kim");
		System.out.print("Enter no. of processes and resources : ");
		numP=sc.nextInt();  //# of process
		numR=sc.nextInt();  //# of resources
		need=new int[numP][numR];  //initializing arrays
		max=new int[numP][numR];
		allocate=new int[numP][numR];
		avail=new int[numR];

		System.out.println("Enter allocation matrix -->");
		for(int i=0;i<numP;i++)
			for(int j=0;j<numR;j++)
				allocate[i][j]=sc.nextInt();  //allocation matrix

		System.out.println("Enter max matrix -->");
		for(int i=0;i<numP;i++)
			for(int j=0;j<numR;j++)
				max[i][j]=sc.nextInt();  //max matrix

		System.out.println("Enter available matrix -->");
		for(int j=0;j<numR;j++)
			avail[j]=sc.nextInt();  //available matrix

		sc.close();
	}

	private int[][] calc_need(){
		for(int i=0;i<numP;i++)
			for(int j=0;j<numR;j++)  //calculating need matrix
				need[i][j]=max[i][j]-allocate[i][j];

		return need;
	}

	private boolean check(int i){
		//checking if all resources for ith process can be allocated
		for(int j=0;j<numR;j++) 
			if(avail[j]<need[i][j])
				return false;

		return true;
	}

	public void isSafe() {
	    input();
	    calc_need();

	    boolean[] finish = new boolean[numP];
	    int[] safe = new int[numP];
	    int count = 0;

	    while (count < numP) {
	        boolean safeOk = false;
	        for (int i = 0; i < numP; i++) {
	            if (!finish[i] && check(i)) {
	                for (int j = 0; j < numR; j++) {
	                    avail[j] += allocate[i][j];
	                }
	                safe[count] = i;
	                finish[i] = true;           
	                safeOk = true;
	                count++;
	                System.out.println("Allocated process: " + i);
	            }
	        }
	        if (!safeOk) {
	            System.out.println("All processes can't be allocated safely");
	            return;
	        }
	    }

	    System.out.println("\nSafely allocated");
	}
	
	public static void main(String[] args) {
		new Bankers().isSafe();

	}

}
