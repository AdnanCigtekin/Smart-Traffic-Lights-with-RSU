
public class roadClass {

	
	public  String name;
	public  double redLightTime = 2.0; 				//(min) 1min = 60sec
	public  double greenLightTime = 0.5; 			//(min)
	public  double yellowLightTime = 0.2; 			//(min)
	public  double rsuCountTime; 					//(hour)
	public  double rsuToLightDistance; 				//(km)
	public  int priority;		 					//(higher = prior)
	public  double roadLimit; 						//(km/h)
	public  int carAmount;
	public 	int CURRENT_ROAD_ID;
	public 	int crossingRoadID;
	
	
	roadClass(int carCount , double roadLimit , String type, int CURRENT_ROAD_ID) throws Exception
	{
		if(type == "Major")
		{
			this.name = "Major";
			this.priority = 5;
			this.roadLimit = roadLimit;
			this.carAmount = carCount;
			this.rsuToLightDistance = (this.roadLimit / 60.0 );
			this.rsuCountTime = (this.rsuToLightDistance / this.roadLimit) ;
			this.CURRENT_ROAD_ID = CURRENT_ROAD_ID;
			
		}
		else if (type == "Minor")
		{
			this.name = "Minor";
			this.priority = 4;
			this.roadLimit = roadLimit;
			this.carAmount = carCount;
			this.rsuToLightDistance = (this.roadLimit / 60.0 );
			this.rsuCountTime = (this.rsuToLightDistance / this.roadLimit) ;
			this.CURRENT_ROAD_ID = CURRENT_ROAD_ID;
		}
		else if (type == "Primary")
		{
			this.name = "Primary";
			this.priority = 3;
			this.roadLimit = roadLimit;
			this.carAmount = carCount;
			this.rsuToLightDistance = (this.roadLimit / 60.0 );
			this.rsuCountTime = (this.rsuToLightDistance / this.roadLimit) ;
			this.CURRENT_ROAD_ID = CURRENT_ROAD_ID;
		}
		else if (type == "Street")
		{
			this.name = "Street";
			this.priority = 2;
			this.roadLimit = roadLimit;
			this.carAmount = carCount;
			this.rsuToLightDistance = (this.roadLimit / 60.0 );
			this.rsuCountTime = (this.rsuToLightDistance / this.roadLimit) ;
			this.CURRENT_ROAD_ID = CURRENT_ROAD_ID;
		}
		/*
		else if (type == "Pedestrian")
		{
			this.name = "Pedestrian";
			this.priority = 1;
			this.roadLimit = roadLimit;
			this.carAmount = carCount;
			this.rsuToLightDistance = (this.roadLimit / 60.0 );
			this.rsuCountTime = (this.rsuToLightDistance / this.roadLimit) ;
			this.CURRENT_ROAD_ID = CURRENT_ROAD_ID;
		}
		*/
	}
	
}
