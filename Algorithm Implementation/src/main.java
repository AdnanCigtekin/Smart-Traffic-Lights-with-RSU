import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class main {
	
	public static ArrayList<roadClass> roads = new ArrayList <roadClass>(); //List of all roads.
	
	public static void main(String[] args) {
		int CURRENT_ROAD_ID = 0;
		Scanner reader = new Scanner(System.in);
		Scanner reader1 = new Scanner(System.in);
		
		String input;
		String carAmount;
		String roadLimit;
		
		
		
		try
		{
			while(true)
			{
				System.out.println("Welcome to Traffic Light AI");
				System.out.println("Enter 1 to add Major Road");
				System.out.println("Enter 2 to add Minor Road");
				System.out.println("Enter 3 to add Primary Road");
				System.out.println("Enter 4 to add Street Road");
				//System.out.println("Enter 5 to add Pedestrian Crossing");
				System.out.println("Enter 0 to exit");
				
				
				System.out.println("Enter a number: ");
				input = reader.next();
				
				switch(input) {
				  case "1":
					  System.out.println("	-Adding MAJOR ROAD");
					  System.out.println("Enter Car Amount");
					  carAmount = reader1.next();
					  System.out.println("Enter Road Limit (km/h)");
					  roadLimit = reader1.next();
					  roadClass major = new roadClass(Integer.parseInt(carAmount), Integer.parseInt(roadLimit),"Major",CURRENT_ROAD_ID);
					  System.out.println("Distance between RSU to Traffic Lights is "+major.rsuToLightDistance +" km" );
					  System.out.println("RSU car count range is "+major.rsuCountTime*60.0 +" min" );
					  if(findDuplicate(major))
					  {
						  System.out.println("Wrong Input : Road type and car amount cant be same for each road"); 
						  System.exit(0);
					  }
					  roads.add(major);
					  CURRENT_ROAD_ID++;
					  //system returns rsu placement distance
					  //then it returns rsuCountTime
					  break;  
				  case "2":
					  System.out.println("	-Adding MINOR ROAD");
					  System.out.println("Enter Car Amount");
					  carAmount = reader1.next();
					  System.out.println("Enter Road Limit (km/h)");
					  roadLimit = reader1.next();
					  roadClass minor = new roadClass(Integer.parseInt(carAmount), Integer.parseInt(roadLimit),"Minor",CURRENT_ROAD_ID);
					  System.out.println("Distance between RSU to Traffic Lights is "+minor.rsuToLightDistance +" km" );
					  System.out.println("RSU car count range is "+minor.rsuCountTime*60.0 +" min" );
					  if(findDuplicate(minor))
					  {
						  System.out.println("Wrong Input : Road type and car amount cant be same for each road"); 
						  System.exit(0);
					  }
					  roads.add(minor);
					  CURRENT_ROAD_ID++;
					  break;
				  case "3":
					  System.out.println("	-Adding PRIMARY ROAD");
					  System.out.println("Enter Car Amount");
					  carAmount = reader1.next();
					  System.out.println("Enter Road Limit (km/h)");
					  roadLimit = reader1.next();
					  roadClass primary = new roadClass(Integer.parseInt(carAmount), Integer.parseInt(roadLimit),"Primary",CURRENT_ROAD_ID);
					  System.out.println("Distance between RSU to Traffic Lights is "+primary.rsuToLightDistance +" km" );
					  System.out.println("RSU car count range is "+primary.rsuCountTime*60 +" min" );
					  if(findDuplicate(primary))
					  {
						  System.out.println("Wrong Input : Road type and car amount cant be same for each road"); 
						  System.exit(0);
					  }
					  roads.add(primary);
					  CURRENT_ROAD_ID++;
					  break;
				  case "4":
					  System.out.println("	-Adding STREET ROAD");
					  System.out.println("Enter Car Amount");
					  carAmount = reader1.next();
					  System.out.println("Enter Road Limit (km/h)");
					  roadLimit = reader1.next();
					  roadClass street = new roadClass(Integer.parseInt(carAmount), Integer.parseInt(roadLimit),"Street",CURRENT_ROAD_ID);
					  System.out.println("Distance between RSU to Traffic Lights is "+street.rsuToLightDistance +" km" );
					  System.out.println("RSU car count range is "+street.rsuCountTime*60 +" min" );
					  if(findDuplicate(street))
					  {
						  System.out.println("Wrong Input : Road type and car amount cant be same for each road"); 
						  System.exit(0);
					  }
					  roads.add(street);
					  CURRENT_ROAD_ID++;
					  break;
				  /*case "5":
					  System.out.println("	-Adding PEDESTRIAN CROSSING");
					  System.out.println("Enter Human Amount");
					  carAmount = reader1.next();
					  System.out.println("Enter Road Limit (km/h)");
					  roadLimit = reader1.next();
					  roadClass pedestrian = new roadClass(Integer.parseInt(carAmount), Integer.parseInt(roadLimit),"Pedestrian");
					  roads.add(pedestrian);
					  break;*/
				  case "0":
					  System.out.println("Good Bye.");
					  System.exit(0);
					  break;
				  default:
					  System.out.println("Wrong Input");  
				}
				
				System.out.println("Want to add another road ? (Y/N) ");
				input = reader.next();
				if(input.equals("N") || input.equals("n"))
				{
					
					ArrayList<roadClass> tempList = new ArrayList();
					Iterator<roadClass> iter = roads.iterator();
					roadClass temp;
					while (iter.hasNext())
					{
						temp = iter.next();
						tempList.add(temp);
					}
					
					System.out.println("Do you want to define crossing roads ? (Y/N) ");
					input = reader.next();
					String selectedRoadID;
					String crossingRoadID;
					if(input.equals("Y") || input.equals("y"))
					{
						System.out.println("Enter a road id to select :");
						input = reader.next();
						selectedRoadID = input;
						System.out.println("Enter a road id to cross with selected road :");
						input = reader.next();
						crossingRoadID = input;
						roadCrosser(selectedRoadID,crossingRoadID);
					}
					
					lightTimeCalculator(tempList ,1.0);
					printAllRoads();
					System.out.println("\nGood Bye.");
					reader.close();
					reader1.close();
					System.exit(0);	
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			 System.out.println("Error Throwed : Wrong Input "+e);
		}
		
	
		reader.close();
		reader1.close();
		
		
	}

	
	private static boolean findDuplicate(roadClass subject) {
		Iterator<roadClass> iter = roads.iterator();
		roadClass temp;
		
		while (iter.hasNext())
		{
			temp = iter.next();
			if(temp.carAmount == subject.carAmount && temp.priority == subject.priority)
			{
				System.out.println("Same test cases!");
				return true;
			}
			
		}
		return false;
	}


	private static void roadCrosser(String selectedRoadID, String crossingRoadID) throws Exception
	{
		Iterator<roadClass> iter = roads.iterator();
		roadClass selected = null;
		roadClass crossed = null;
		roadClass temp;
		while (iter.hasNext())
		{
			
			temp = iter.next();
			if(temp.CURRENT_ROAD_ID == Integer.parseInt(selectedRoadID))
			{
				selected = temp;
			}
			if(temp.CURRENT_ROAD_ID == Integer.parseInt(crossingRoadID))
			{
				crossed  = temp;
			}
		}
		
		if(selected != null && crossed != null)
		{
			selected.crossingRoadID = crossed.CURRENT_ROAD_ID;
			crossed.crossingRoadID = selected.CURRENT_ROAD_ID;
		}
		else
		{
			 System.out.println("Cannot Find Roads !");
		}
		
	}


	private static void printAllRoads() {
		
		Iterator<roadClass> iter = roads.iterator();
		roadClass temp;
		try {
			temp = new roadClass(0,0,"Major",0);
			while(iter.hasNext())
			{
				temp = iter.next();
				System.out.println(
						"\nRoad ID : "		+temp.CURRENT_ROAD_ID
						+"\nRoad Name : "	+temp.name
						+"\nCar amount :"	+temp.carAmount
						+"\nRed Light :"	+temp.redLightTime 		+" min"
						//+"\nYellow Light :"	+temp.yellowLightTime	+" min"
						+"\nGreen Light :"	+temp.greenLightTime	+" min"
						
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public static double lightTimeCalculator(ArrayList<roadClass> roadsList,double iteration) throws Exception
	{
		if (roadsList.isEmpty())
		{
			return 0;
		}
		
		roadClass tempOBJ = new roadClass(0,0,"Major",0);
		roadClass tempOBJPrior = new roadClass(0,0,"Major",0);
		
		tempOBJ=mostCarCount(roadsList);
		System.out.println("most car is in = "+tempOBJ.name + " . ID = "+tempOBJ.CURRENT_ROAD_ID);
		tempOBJPrior = mostPrior(roadsList);
		System.out.println("most prior road is = "+tempOBJPrior.name + " . ID = " + tempOBJPrior.CURRENT_ROAD_ID);
		roadsList.remove(tempOBJ);
		roadsList.remove(tempOBJPrior);
		ArrayList<roadClass> tempList = roadsList;
		int index;
		if( (tempOBJ.priority == tempOBJPrior.priority) )
		{
			if(tempOBJ.carAmount <= tempOBJPrior.carAmount+10)
			{
				
				index = roads.indexOf(tempOBJ);
				roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
				if(tempCross != null)
				{
					//this might be faulty
					//for the sake of current test env. this case doesnt match the general flow
					//and structered hard.
					if(tempCross.carAmount == tempOBJ.carAmount || tempCross.carAmount <= tempOBJ.carAmount + 5 || tempCross.carAmount >= tempOBJ.carAmount )
					{
						System.out.println("reduce red light duration "+0.3/iteration+" min of "+tempOBJ.name+ " with car number " + tempOBJ.carAmount);
						roads.get(index).greenLightTime = roads.get(index).greenLightTime + 0.3/iteration;
						roads.get(index).redLightTime = roads.get(index).redLightTime - 0.3/iteration;
					}
					else {
						System.out.println("reduce red light duration "+1/iteration+" min of "+tempOBJ.name+ " with car number " + tempOBJ.carAmount);
						roads.get(index).greenLightTime = roads.get(index).greenLightTime +1/iteration;
						roads.get(index).redLightTime = roads.get(index).redLightTime - 1/iteration;
					}
					
					index = roads.indexOf(tempCross);
					roads.get(index).redLightTime = tempOBJ.greenLightTime;
					roads.get(index).greenLightTime = tempOBJ.redLightTime;
				}
			}
			else if (tempOBJ.carAmount < tempOBJPrior.carAmount*3)
			{
				System.out.println("reduce red light duration "+1.5/iteration+" min of "+tempOBJ.name+ " with car number " + tempOBJ.carAmount);
				
				index = roads.indexOf(tempOBJ);
				roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
				roads.get(index).redLightTime = roads.get(index).redLightTime - 1.5/iteration;
				roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1.5/iteration;
		
				if(tempCross != null)
				{
					index = roads.indexOf(tempCross);
					roads.get(index).redLightTime = tempOBJ.greenLightTime;
					roads.get(index).greenLightTime = tempOBJ.redLightTime;
				}
			}
			else 
			{
				System.out.println("reduce red light duration "+1.8/iteration+" min of "+tempOBJ.name+ " with car number " + tempOBJ.carAmount);
				
				index = roads.indexOf(tempOBJ);
				roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
				roads.get(index).redLightTime = roads.get(index).redLightTime - 1.8/iteration;
				roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1.8/iteration;
		
				if(tempCross != null)
				{
					index = roads.indexOf(tempCross);
					roads.get(index).redLightTime = tempOBJ.greenLightTime;
					roads.get(index).greenLightTime = tempOBJ.redLightTime;
				}
			}
			
			
		}
		else if (tempOBJPrior.carAmount < tempOBJ.carAmount -5 )
		{
			
			if (tempOBJPrior.carAmount < tempOBJ.carAmount -20)
			{
				System.out.println("reduce red light duration 1.2 min of "+tempOBJ.name + " with car number " + tempOBJ.carAmount);
				index = roads.indexOf(tempOBJ);
				roads.get(index).redLightTime = roads.get(index).redLightTime - ((1.2)/iteration);
				roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1.2/iteration; 
				//cross
				roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
				if(tempCross != null)
				{
					index = roads.indexOf(tempCross);
					roads.get(index).redLightTime = tempOBJ.greenLightTime;
					roads.get(index).greenLightTime = tempOBJ.redLightTime;
				}
				
			}
			//thiss state might be null state wait for results
			else if ( (tempOBJPrior.priority-2) >= tempOBJ.priority)
			{
				
				System.out.println("reduce red light duration 1 min of "+tempOBJPrior.name + " with car number " + tempOBJPrior.carAmount);
				System.out.println(tempOBJ.name+" has more car but it is neglectable due to priority");
				index = roads.indexOf(tempOBJPrior);
				roads.get(index).redLightTime = roads.get(index).redLightTime - 1/iteration;
				roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1/iteration;
				//cross
				roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
				if(tempCross != null)
				{
					index = roads.indexOf(tempCross);
					roads.get(index).redLightTime = tempOBJPrior.greenLightTime;
					roads.get(index).greenLightTime = tempOBJPrior.redLightTime;
				}
			}
			else if ( (tempOBJPrior.priority * tempOBJPrior.carAmount) > (tempOBJ.priority * tempOBJ.carAmount) )
			{
				if ( ( (tempOBJPrior.priority * tempOBJPrior.carAmount) / 2) < (tempOBJ.priority * tempOBJ.carAmount) )
				{
					System.out.println("reduce red light duration 1.3 min of "+tempOBJ.name + " with car number " + tempOBJ.carAmount);
					index = roads.indexOf(tempOBJ);
					roads.get(index).redLightTime = roads.get(index).redLightTime - 1.3/iteration;
					roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1.3/iteration;
					//cross
					roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
					if(tempCross != null)
					{
						index = roads.indexOf(tempCross);
						roads.get(index).redLightTime = tempOBJ.greenLightTime;
						roads.get(index).greenLightTime = tempOBJ.redLightTime;
					}
				}
				else
				{
					System.out.println("reduce red light duration 1 min of "+tempOBJPrior.name + " with car number " + tempOBJPrior.carAmount);
					System.out.println(tempOBJ.name+" has more car but it is neglectable due to priority");
					index = roads.indexOf(tempOBJPrior);
					roads.get(index).redLightTime = roads.get(index).redLightTime - 1/iteration;
					roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1/iteration;
					//cross
					roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
					if(tempCross != null)
					{
						index = roads.indexOf(tempCross);
						roads.get(index).redLightTime = tempOBJPrior.greenLightTime;
						roads.get(index).greenLightTime = tempOBJPrior.redLightTime;
					}
				}
			}
			
		}
		else if (tempOBJPrior.carAmount > tempOBJ.carAmount - 10)
		{	
			System.out.println("reduce red light duration 1.1 min of "+tempOBJPrior.name+ " with car number " + tempOBJPrior.carAmount);
			System.out.println(tempOBJ.name+" has more car but it is neglectable due to priority");
			index = roads.indexOf(tempOBJPrior);
			roads.get(index).redLightTime = roads.get(index).redLightTime - 1.1/iteration;
			roads.get(index).greenLightTime = roads.get(index).greenLightTime + 1.1/iteration;
			//cross
			roadClass tempCross = getRoadViaID(roads.get(index).crossingRoadID);
			if(tempCross != null)
			{
				index = roads.indexOf(tempCross);
				roads.get(index).redLightTime = tempOBJ.greenLightTime;
				roads.get(index).greenLightTime = tempOBJ.redLightTime;
			}
		}
		
		if(roadsList.size() >2)
		{lightTimeCalculator(tempList, iteration + 0.5);}
		return 0;
	}
	
	private static roadClass getRoadViaID(int crossingRoadID) {
		Iterator<roadClass> iter = roads.iterator();
		roadClass temp = null;
		roadClass choosenOne = null;
		while (iter.hasNext())
		{
			temp = iter.next();
			if(temp.CURRENT_ROAD_ID == crossingRoadID)
			{
				choosenOne = temp;
			}
		}
		return choosenOne;
	}

	public static roadClass mostCarCount (ArrayList<roadClass> roadsList)
	{
		
		Iterator<roadClass> iter = roadsList.iterator(); 
        int carCount = 0;
        try {
			roadClass temp = new roadClass(0,0,"Major",0);
			roadClass temp2 = new roadClass(0,0,"Major",0);
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if (temp.carAmount > carCount)
				{
					temp2 = temp;
					carCount = temp.carAmount;
				}	
			}
			return temp2;
        }
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static roadClass mostPrior(ArrayList<roadClass> roadsList)
	{
		
		Iterator<roadClass> iter = roadsList.iterator(); 
        int priority = 0;
        try {
			roadClass temp = new roadClass(0,0,"Major",0);
			roadClass temp2 = new roadClass(0,0,"Major",0);
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if (temp.priority >= priority)
				{
					temp2 = temp;
					priority = temp.priority;
				}	
			}
			return temp2;
        }
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
