package com.example;


	
	
	class DataObject {
	    private final String data;
	 
	    private static int objectCounter = 0;
	    // standard constructors/getters
	     public DataObject(String data){
	    	 this.data=data;
	     }
	    public static DataObject get(String data) {
	        setObjectCounter(getObjectCounter() + 1);
	        return new DataObject(data);
	    }
		public static int getObjectCounter() {
			return objectCounter;
		}
		public static void setObjectCounter(int objectCounter) {
			DataObject.objectCounter = objectCounter;
		}
		public String getData() {
			return data;
		}
	}


