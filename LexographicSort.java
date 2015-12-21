import java.io.*;
import java.util.HashMap;

class ArrayElement{
	String element;
	int weight;
	public ArrayElement(String element, int weight){
		this.element=element;
		this.weight=weight;
	}
}
public class LexographicSort {

String sampleOrder="";
int hmSize;
ArrayElement[] arrayElement;
HashMap<Character, Integer> hm= new HashMap<Character, Integer>();

public void sortByWeight()
{
	ArrayElement temp;
	int length=arrayElement.length;
	for(int i=0; i<length; i++){
		for(int j=1;j<length-i;j++){
			if(arrayElement[j-1].weight<arrayElement[j].weight){
				temp=arrayElement[j-1];
				arrayElement[j-1]=arrayElement[j];
				arrayElement[j]=temp;
				}
			if(arrayElement[j-1].weight==arrayElement[j].weight && arrayElement[j-1].element.length()>arrayElement[j].element.length()){
					temp=arrayElement[j-1];
					arrayElement[j-1]=arrayElement[j];
					arrayElement[j]=temp;
			}
		}
	}
}
public int findWeight(String string) {
	if(string=="")
		return (int)Math.pow(10,hm.size()+2); // Returning some higher number for emty string
	
	int weight=0;
	if(string.length()>hmSize)
	string=string.substring(0,hmSize);
	char[] letters=string.toCharArray();
	
	for(int i=0; i<letters.length;i++)
		if(hm.get(letters[i])!=null)
		// weight=weight+(hm.get(letters[i])*(hmSize-i));
		   weight=(weight+(hm.get(letters[i])*(int)(Math.pow(10,hmSize-i))));
	
	return weight;
}

public void lexSort(String[] arr, String s){
	int i;
	arrayElement=new ArrayElement[arr.length];
	this.sampleOrder=s;
	int multiplier=10;
	
	for(i=s.length()-1;i>=0; i--){
		 hm.put(s.charAt(i), multiplier);
		 multiplier=multiplier*10;
		}
	hmSize=hm.size();
	
	for(i=0;i<arr.length; i++){
		arrayElement[i]=new ArrayElement(arr[i],findWeight(arr[i]));
	}
	
	sortByWeight();
	
}

public static void main(String args[]) throws IOException
{
	LexographicSort ls=new LexographicSort();
	String arr[]={"acb", "abc", "bca"};
	ls.lexSort(arr,"abc");
    System.out.print("[");
	for(int i=0; i< ls.arrayElement.length;i++)
	// System.out.println(ls.arrayElement[i].element +" "+ ls.arrayElement[i].weight);
		System.out.print(ls.arrayElement[i].element + " ");
	System.out.print("]");
}
}