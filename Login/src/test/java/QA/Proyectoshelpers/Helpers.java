package QA.Proyectoshelpers;

public class Helpers {
	
	 public void sleepSecunds(int secunds){
		 
	     try {
	            Thread.sleep(secunds *1000);
	    }catch (InterruptedException e){
	            e.printStackTrace();
	     }
	 }

}
