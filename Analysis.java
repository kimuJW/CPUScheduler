import java.util.Arrays;


public class Analysis {

	public PlatformInfo pInfo;

	public static void main(String[] args) {
		
	}
	public String run(PlatformInfo p) {
		
		pInfo = p;
		int schedulable=0;
		
		switch(p.scheduler) {
		case 1000:	//utilization bound for RM 	
			schedulable=UBTestforRM();  
			break;
		
		case 1001: 	//utilization bound for EDF 
			schedulable=UBTestforEDF();
			break;
			
		case 1002: //RTA for RM
			schedulable=RTATestforRM_Uni();
			break;
		}

		String result=schedulable+" "+pInfo.numTask;

		return result;
	}
	
	

	public int UBTestforRM () {
		
		double Usys=0;
		for(int i=0; i<pInfo.tasks.size(); i++)
			Usys+=pInfo.tasks.get(i).execTime/pInfo.tasks.get(i).Period;
		
		if(Usys<=pInfo.tasks.size()*(Math.pow(2, 1.0/pInfo.tasks.size())-1))
			return 1;
		else
			return 0;	
	}

	public int UBTestforEDF () {
		 
		double Usys=0;
		for(int i=0; i<pInfo.tasks.size(); i++)
			Usys+=pInfo.tasks.get(i).execTime/pInfo.tasks.get(i).Period;
		
		if(Usys<=1)
			return 1;
		else
			return 0;	
	
	}	
	
	public int RTATestforRM_Uni() 
	{
		for(int k=0; k<pInfo.tasks.size(); k++)
		{
			Task task_k = pInfo.tasks.get(k);
			int sum=0;
			double interval = task_k.execTime;
			while(interval<=task_k.Deadline) 
			{
				sum=(int)task_k.execTime;
				for(int i=0;i<pInfo.tasks.size();i++)
				{
					if(i<k)
					{
						Task task_i = pInfo.tasks.get(i);
						sum+=Math.ceil(interval/task_i.Period)*task_i.execTime;
					}
				}
				if(interval==sum)
					break;
				else
					interval = sum;				
			}
			
			if(interval>task_k.Deadline) 
			{
				return 0;
			}
			else if(interval==sum) 
			{
				 continue;
			}
			//1. interval이 deadline 보다 클 때
			//2. interval이 sum(그 interval 안에 존재하는 execution)과 같을 때 튀어나옴
			//if 1번 case이면 deadline miss 이므로 return 0;
			//if 2번 case이면 continue;
	}	
	return 1;
	}
}
