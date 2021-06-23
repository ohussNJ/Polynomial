package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		Node fin=null;
		Node f=null;
		Node com=null;
		if(poly1==null && poly2==null) {
			  return f;
		}
		while(poly1!=null && poly2!=null) {
			  if(poly1.term.degree<poly2.term.degree) {
				  com=new Node(poly1.term.coeff, poly1.term.degree, null);
				 if(fin!=null){
					 fin.next=com;
				}else{
					  f=com;
				}
				 fin=com;
				 poly1=poly1.next;
			}else if(poly2.term.degree<poly1.term.degree) {
				 com=new Node(poly2.term.coeff, poly2.term.degree, null);
				 if(fin!=null){
					  fin.next=com;
				}else{
					  f=com;
				}
				 fin=com;
				  poly2=poly2.next;
			}else if(poly1.term.degree==poly2.term.degree){
				  com=new Node(poly1.term.coeff + poly2.term.coeff, poly1.term.degree,null);
				  if(fin!=null){
					 fin.next=com;
				}else{
					 f=com;
				}
				 fin=com;
				  poly1=poly1.next;
				   poly2=poly2.next;
			}	}
		if(poly1!=null) {
			 while(poly1!=null) {
			    	com=new Node(poly1.term.coeff, poly1.term.degree, null);
				   if(fin!=null){
					 fin.next=com;
				}else{
				    	f=com;
					}
					 fin=com;
					  poly1=poly1.next;
				}}else if(poly2!=null){
			 while(poly2!=null) {
				  com=new Node(poly2.term.coeff, poly2.term.degree, null);
				  if(fin!=null){
				 	fin.next=com;
				}else{
				    	f=com;
					}
					 fin=com;
					  poly2=poly2.next;
				}}
		Node prev=null;
		com=f;
		while(com!=null) {
		    	if(com.term.coeff==0) {
			    	if(com==f) {
				    	f=com.next;
				}else{
					prev.next=com.next;
				}
			}else{
				prev=com;
			}
			com=com.next;
		}
	   
		return f;


	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		Node f=null;
		Node e=null;
		Node a=null;
		if(poly1==null || poly2==null) {
			return f;
		}
		for(Node com=poly2; com!=null; com=com.next) {
			  for(Node com1=poly1; com1!=null; com1=com1.next) {
				  Node com2=new Node(com1.term.coeff * com.term.coeff, com1.term.degree + com.term.degree, null);
			  if(e!=null){
				  e.next=com2;
			} else {
				f=com2;
			}
			e=com2;
			}
			a=add(a, f);
			f=null;
			e=null;
		}
		return a;
		
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		float xx=0;
		if(poly!=null) {
			for(Node com=poly; com!=null; com=com.next) {
				xx+=com.term.coeff * Math.pow(x,com.term.degree);
			}
		}
		return xx;

		
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
