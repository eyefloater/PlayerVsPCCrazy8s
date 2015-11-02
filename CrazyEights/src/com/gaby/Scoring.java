package com.gaby;

import java.util.List;

public class Scoring {

	//iterates through hand, adding points based on card values
	public static int getScore(List<Card> playerHand) {
		int i;
		int score = 0;
		for(i=1; i < playerHand.size(); i++){
			if(playerHand.get(i).number.equals("Ace")){
				score += 1;}
			if(playerHand.get(i).number.equals("2")){
				score += 2;}
			if(playerHand.get(i).number.equals("3")){
				score += 3;}
			if(playerHand.get(i).number.equals("4")){
				score += 4;}
			if(playerHand.get(i).number.equals("5")){
				score += 5;}
			if(playerHand.get(i).number.equals("6")){
				score += 6;}
			if(playerHand.get(i).number.equals("7")){
				score += 7;}
			if(playerHand.get(i).number.equals("8")){
				score += 8;}
			if(playerHand.get(i).number.equals("9")){
				score += 9;}
			if(playerHand.get(i).number.equals("10")){
				score += 10;}
			if(playerHand.get(i).number.equals("Jack")){
				score += 10;}
			if(playerHand.get(i).number.equals("Queen")){
				score += 10;}
			if(playerHand.get(i).number.equals("King")){
				score += 10;}
			}
		
		return score;

	}
}
