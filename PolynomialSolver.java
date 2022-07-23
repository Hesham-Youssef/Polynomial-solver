import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.math.*;


interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}





interface ILinkedList {
    /**
    * Inserts a specified element at the specified position in the list.
    * @param index
    * @param element
    */
    public void add(int index, int[][] element);
    /**
    * Inserts the specified element at the end of the list.
    * @param element
    */
    public void add(int[][] element);
    /**
    * @param index
    * @return the element at the specified position in this list.
    */
    public int[][] get(int index);

    /**
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index
    * @param element
    */
    public void set(int index, int[][] element);
    /**
    * Removes all of the elements from this list.
    */
    public void clear();
    /**
    * @return true if this list contains no elements.
    */
    public boolean isEmpty();
    /**
    * Removes the element at the specified position in this list.
    * @param index
    */
    public void remove(int index);
    /**
    * @return the number of elements in this list.
    */
    public int size();
    /**
    * @param fromIndex
    * @param toIndex
    * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
    */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
    * @param o
    * @return true if this list contains an element with the same value as the specified element.
    */
    public boolean contains(int[][] o);
}


class SingleLinkedList implements ILinkedList{
    /* Implement your linked list class here*/
   
    Node head = null;
    int size = 0;
    static class Node{
        int[][] value;
        Node next;
        Node(int[][] d,Node n){
            value = d;
            next = n;
        }
        public String toString(){
            return this.value + "";
        }
    }
    public void add(int[][] element){
        Node newNode = new Node(element,null);
        if(head == null){
            head = newNode;
        }else{
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }
    
    public void add(int index, int[][] element){
        if(!((index > size) || (index < 0))){
            Node newNode = new Node(element,null);
            if(index == 0){
                newNode.next = head;
                head = newNode;
            }else{
                Node temp = head;
                int i = 0;
                while(i < index-1){
                    temp = temp.next;
                    i++;
                }
                newNode.next = temp.next;
                temp.next = newNode;
            }
            size++;
        }
    }
    
    public int[][] get(int index){
        if((index > size-1) || (index < 0))
            return null;   
        else{
            if(index == 0){
                return head.value;
            }else{
                Node temp = head;
                int i = 0;
                while(i < index){
                    temp = temp.next;
                    i++;
                }
                return temp.value;
            }
        }
    }
        
    public void set(int index, int[][] element){
        if(!((index > size-1) || (index < 0))){
            if(index == 0){
                head.value = element;
            }else{
                Node temp = head;
                int i = 0;
                while(i < index){
                    temp = temp.next;
                    i++;
                }
                temp.value = element;
            }
        }
    }
    
    public void clear(){
        head = null;
        size = 0;
    }
    
    public boolean isEmpty(){
        if(head == null)
            return true;
        else
            return false;
    }
    
    public void remove(int index){
        if(!((index > size-1) || (index < 0))){
            if(index == 0){
                head = head.next;
            }else{
                Node temp = head;
                int i = 0;
                while(i < index-1){
                    temp = temp.next;
                    i++;
                }
                temp.next = temp.next.next;
                
            }
            size--;
        }
        
    }

    public int size(){return size;}
    
    public ILinkedList sublist(int fromIndex, int toIndex){
        ILinkedList sub = new SingleLinkedList();
        if((fromIndex > size-1) || (fromIndex < 0) || (toIndex > size-1) || (toIndex < 0) || (toIndex < fromIndex))
                System.out.println("Error");
        else{
            Node temp = head;
            int i = 0;
            while(i < toIndex+1){
                if(i >= fromIndex && i <= toIndex)
                    sub.add(temp.value);
                temp = temp.next;
                i++;
            }
        }
        return sub;
    }

    public boolean contains(int[][] o){
        Node temp = head;
        for(int i=0;i<size;i++){
            if(temp.value.equals(o.toString()))
                return true;
            else
                temp = temp.next;
        }
        return false;
    }
    
    public void printlist(SingleLinkedList list){
        if(list.size() > 1){
            System.out.print("[");
            for(int i=0;i<list.size()-1;i++)
                System.out.print(list.get(i) + ", ");
            System.out.print(list.get(list.size()-1) + "");
            System.out.print("]");
        }else if(list.size == 1){
            System.out.print("[" + list.get(0) + "]");
        }else{
            System.out.print("["+"]");
        }
    }
}


public class PolynomialSolver{
    static SingleLinkedList polies = new SingleLinkedList();
    static String names = "";
    

    public int[][] stringto2dint(String strline){
        if(strline.length() > 0){
            String[] str = strline.split(",");
            int[][] CandE = new int[str.length][2];
            for(int i = 0;i<str.length;i++){
                CandE[i][0] = Integer.parseInt(str[i]);
                CandE[i][1] = str.length - 1 - i;
            }
            return CandE;
        }else
            return new int[1][2];
    }
    
    
    void setPolynomial(char poly, int[][] terms){
        names = names + poly;
        polies.add(terms);    
    }
    
    String print(char poly){
        int indexoftemp = names.lastIndexOf(poly);
        int[][] temp = polies.get(indexoftemp);
        String polstr = new String();
        int term = 0;
        
        if(polies.get(indexoftemp) == null){
            return "[]";
        }else{
            for(int i=0;i<temp.length;i++){
                if(!(temp[i][0] == 0)){
                    if((term != 0) && (temp[i][0] > 0))
                        polstr = polstr + "+";
                    if(temp[i][0] != 1)
                        polstr = polstr + temp[i][0] + "";
                    else if(temp[i][1] == 0)
                        polstr = polstr + temp[i][0];
                    if(temp[i][1] > 1)
                        polstr = polstr + "x^" + temp[i][1];
                    else if(temp[i][1] == 1)
                        polstr = polstr + "x";
                    term++;
                }
            }
        }
        return polstr;
    }
    
    void clearPolynomial(char poly){
        int index = names.lastIndexOf(poly);
        polies.set(index,null);
    }
    
    float evaluatePolynomial(char poly, float value){
        int[][] temp = polies.get(names.lastIndexOf(poly));
        float res = 0;
        for(int i=0;i<temp.length;i++){
            res += Math.pow(value,temp[i][1]) * temp[i][0];
        }
        return res;
    }
    
    int[][] add(char poly1, char poly2){
        int[][] temp1 = polies.get(names.lastIndexOf(poly1));
        int[][] temp2 = polies.get(names.lastIndexOf(poly2));
        int[][] res = new int[Math.max(temp1.length,temp2.length)+3][2];
        int i = 0,j = 0,c = 0;
        
        while((i < temp1.length) && (j < temp2.length)){
            if(temp1[i][1] == temp2[j][1]){
                res[c][0] = temp1[i][0] + temp2[j][0];
                res[c][1] = temp1[i][1];
                i++;
                j++;
            }
            else if(temp1[i][1] > temp2[j][1]){
                res[c][0] = temp1[i][0];
                res[c][1] = temp1[i][1];
                i++;
            }else{
                res[c][0] = temp2[j][0];
                res[c][1] = temp2[j][1];
                j++;
            }
            c++;
            
        }
        
        
        
        
        return res;
    }
    
    int[][] subtract(char poly1, char poly2){
        int[][] temp1 = polies.get(names.lastIndexOf(poly1));
        int[][] temp2 = polies.get(names.lastIndexOf(poly2));
        int[][] res = new int[Math.max(temp1.length,temp1.length)+3][2];
        int i = 0,j = 0,c = 0;
        while((i < temp1.length) && (j < temp2.length)){
            if(temp1[i][1] == temp2[j][1]){
                res[c][0] = temp1[i][0] - temp2[j][0];
                res[c][1] = temp1[i][1];
                i++;
                j++;
            }
            else if(temp1[i][1] > temp2[j][1]){
                res[c][0] = temp1[i][0];
                res[c][1] = temp1[i][1];
                i++;
            }else{
                res[c][0] = -1 *temp2[j][0];
                res[c][1] = temp2[j][1];
                j++;
            }
            c++;
        }
        return res;
    }
    
    int[][] multiply(char poly1, char poly2){
        int[][] temp1 = polies.get(names.lastIndexOf(poly1));
        int[][] temp2 = polies.get(names.lastIndexOf(poly2));
        int[][] res = new int[Math.max(temp1.length,temp1.length)+5][2];
        int[][] mulres = new int[Math.max(temp1.length,temp1.length+5)][2];
        new PolynomialSolver().setPolynomial('r',res);
        new PolynomialSolver().setPolynomial('m',mulres);
        for(int i=0;i<temp1.length;i++){
            for(int j=0;j<temp2.length;j++){
                mulres[j][0] = temp1[i][0] * temp2[j][0];
                mulres[j][1] = temp1[i][1] + temp2[j][1];
            }
            polies.set(names.lastIndexOf('r'),new PolynomialSolver().add('r','m'));
        }
        res = polies.get(names.lastIndexOf('r'));   
        return res;
    }
    
    
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        char poln;
        char poln2;
        String pol;
        float val = 0;
        end:
        while((line = br.readLine()) != null){
            line.trim();
            switch(line){
                case "set":
                    poln = (char) br.read();
                    br.readLine();
                    pol = br.readLine().replaceAll("\\[|\\]","");
                    if(pol.length() == 0){
                        System.out.println("Error");
                        break end;
                    }
                    else
                        new PolynomialSolver().setPolynomial(poln,new PolynomialSolver().stringto2dint(pol));
                    break;
                case "print":
                    
                    poln = (char) br.read();
                    if(polies.get(names.lastIndexOf(poln)) == null){
                        System.out.println("Error");
                        break end;
                    }
                    else
                        System.out.println(new PolynomialSolver().print(poln));
                    break;
                case "eval":
                    
                    poln = (char) br.read();
                    br.read();
                    val = Float.parseFloat(br.readLine());
                    
                    if(polies.get(names.lastIndexOf(poln)) == null){
                        System.out.println("Error");
                        break end;
                    }
                    else{
                        float res = new PolynomialSolver().evaluatePolynomial(poln,val);
                        if(res%1 == 0)
                            System.out.println((int) res);
                        else
                            System.out.println(res);
                    }
                    break;
                case "add":
                    poln = (char) br.read();
                    br.read();
                    poln2 = (char) br.read();
                    if((polies.get(names.lastIndexOf(poln)) == null) || (polies.get(names.lastIndexOf(poln2)) == null)){
                        System.out.println("Error");
                        break end;
                    }
                    else{
                        new PolynomialSolver().setPolynomial('N',new PolynomialSolver().add(poln,poln2));
                        System.out.println(new PolynomialSolver().print('N'));
                    }
                    break;
                case "clear":
                    poln = (char) br.read();
                    if(polies.get(names.lastIndexOf(poln)) == null){
                        System.out.println("Error");
                        break end;
                    }
                    else{
                        new PolynomialSolver().clearPolynomial(poln);
                        System.out.println(new PolynomialSolver().print(poln));
                    }
                    break;
                case "sub":
                    poln = (char) br.read();
                    br.read();
                    poln2 = (char) br.read();
                    if((polies.get(names.lastIndexOf(poln)) == null) || (polies.get(names.lastIndexOf(poln2)) == null)){
                        System.out.println("Error");
                        break end;
                    }
                    else{
                        new PolynomialSolver().setPolynomial('S',new PolynomialSolver().subtract(poln,poln2));
                        System.out.println(new PolynomialSolver().print('S'));
                    }
                    break;
                case "mult":
                    poln = (char) br.read();
                    br.read();
                    poln2 = (char) br.read();
                    if((polies.get(names.lastIndexOf(poln)) == null) || (polies.get(names.lastIndexOf(poln2)) == null)){
                        System.out.println("Error");
                        break end;
                    }
                    else{
                        new PolynomialSolver().setPolynomial('M',new PolynomialSolver().multiply(poln,poln2));
                        System.out.println(new PolynomialSolver().print('M'));
                    }
                    break;
                default:
                    if(line.length() != 0){
                        System.out.println("Error");
                        break end;
                    }
            }
        }
    }
}
