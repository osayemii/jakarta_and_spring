package com.sticky.notes;

import java.util.Scanner;

public class StickyNote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		System.out.println("---------------------------------");
		System.out.print("Enter your Math score: ");
		int math = input.nextInt();
		
		System.out.print("Enter your English score: ");
		int english = input.nextInt();
		
		System.out.print("Enter your Physics score: ");
		int physics = input.nextInt();
		
		System.out.print("Enter your Biology score: ");
		int biology = input.nextInt();
		
		System.out.print("Enter your Chemistry score: ");
		int chemistry = input.nextInt();
		
		System.out.println("---------------------------------");
		int total_score = math +english+physics+biology+chemistry;
		System.out.println("Your total score is " + total_score);
		
		input.close();
	}

}
