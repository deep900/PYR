/**
 * 
 */
package com.pradheep.web.jobs;

/**
 * @author Pradheep
 *
 */
public class EventQuickSummary {

	private boolean isFoodProvided;

	private int adultCount;

	private int childCount;

	private int totalCount;

	private int vegeterianFoodCount;

	private int nonVegCount;

	private int nonVegHallalCount;

	private int foodNotRequiredCount;

	private int potentialDuplicatesFound;

	private String eventName;

	public boolean isFoodProvided() {
		return isFoodProvided;
	}

	public void setFoodProvided(boolean isFoodProvided) {
		this.isFoodProvided = isFoodProvided;
	}

	public int getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getVegeterianFoodCount() {
		return vegeterianFoodCount;
	}

	public void setVegeterianFoodCount(int vegeterianFoodCount) {
		this.vegeterianFoodCount = vegeterianFoodCount;
	}

	public int getNonVegCount() {
		return nonVegCount;
	}

	public void setNonVegCount(int nonVegCount) {
		this.nonVegCount = nonVegCount;
	}

	public int getNonVegHallalCount() {
		return nonVegHallalCount;
	}

	public void setNonVegHallalCount(int nonVegHallalCount) {
		this.nonVegHallalCount = nonVegHallalCount;
	}

	public int getFoodNotRequiredCount() {
		return foodNotRequiredCount;
	}

	public void setFoodNotRequiredCount(int foodNotRequiredCount) {
		this.foodNotRequiredCount = foodNotRequiredCount;
	}

	public int getPotentialDuplicatesFound() {
		return potentialDuplicatesFound;
	}

	public void setPotentialDuplicatesFound(int potentialDuplicatesFound) {
		this.potentialDuplicatesFound = potentialDuplicatesFound;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String toString() {
		return isFoodProvided ? "<b>Quick summary for the event :" + eventName + "</b><br> <table style='border: 1px solid black'>" + " <tr style='border: 1px solid black'>"
				+ "<td style='border: 1px solid black'> SNO </td> <td style='border: 1px solid black'> Particulars </td> <td style='border: 1px solid black'> Value </td> </tr>"
				+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 1 </td> <td style='border: 1px solid black'> Adult Count </td> <td style='border: 1px solid black'> " + adultCount + " </td> </tr>"
				+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 2 </td> <td style='border: 1px solid black'> Child Count </td> <td style='border: 1px solid black'> " + childCount + " </td> </tr>"
				+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 3 </td> <td style='border: 1px solid black'> Total Count </td> <td style='border: 1px solid black'> " + totalCount + " </td> </tr>"
				+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 4 </td> <td style='border: 1px solid black'> Vegeterian Count </td> <td style='border: 1px solid black'> " + vegeterianFoodCount + " </td> </tr>"
				+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 5 </td> <td style='border: 1px solid black'> Non-Vegeterian Count </td> <td style='border: 1px solid black'> " + nonVegCount + " </td> </tr>"
				+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 6 </td> <td style='border: 1px solid black'> Non-Vegeterian Hallal Count </td> <td style='border: 1px solid black'> " + nonVegHallalCount
				+ " </td> </tr>" + "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 7 </td> <td style='border: 1px solid black'> Food Not Required Count </td> <td style='border: 1px solid black'> " + foodNotRequiredCount
				+ " </td> </tr>" + "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 8 </td> <td style='border: 1px solid black'> <font color='red'><b>Potential Duplicate Records </b></font></td> <td style='border: 1px solid black'> "
				+ potentialDuplicatesFound + " </td>" + "</tr></table>"
				: "<b>Quick summary for the event :" + eventName + "</b><br> <table style='border: 1px solid black'>" + " <tr style='border: 1px solid black'>"
						+ "<td style='border: 1px solid black'> SNO </td> <td style='border: 1px solid black'> Particulars </td> <td style='border: 1px solid black'> Value </td> </tr>"
						+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 1 </td> <td style='border: 1px solid black'> Adult Count </td> <td style='border: 1px solid black'> " + adultCount + " </td> </tr>"
						+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 2 </td> <td style='border: 1px solid black'> Child Count </td> <td style='border: 1px solid black'> " + childCount + " </td> </tr>"
						+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 3 </td> <td style='border: 1px solid black'> Total Count </td> <td style='border: 1px solid black'> " + totalCount + " </td> </tr>"
						+ "<tr style='border: 1px solid black'> <td style='border: 1px solid black'> 4 </td> <td style='border: 1px solid black'> <font color='red'><b>Potential Duplicate Records </b></font></td> <td style='border: 1px solid black'> " + potentialDuplicatesFound
						+ " </td></tr></table>";
	}
}
