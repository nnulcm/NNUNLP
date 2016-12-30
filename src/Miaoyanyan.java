

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Miaoyanyan
{
	public static void main(String[] args)  throws IOException
	{
		for(int i=1;i<=4;i++)
		wxs("E:\\苗艳艳语料\\毕飞宇\\文本\\"+i+".txt","毕飞宇"+i);
		for(int i=1;i<=4;i++)
		wxs("E:\\苗艳艳语料\\苏童\\文本\\"+i+".txt","苏童"+i);
		for(int i=1;i<=15;i++)
		wxs("E:\\苗艳艳语料\\文本\\"+i+".txt","背景语料"+i);
	}
	public static void wxs(String filepath,String name) throws IOException 
	{
		
		 try (BufferedReader br = new BufferedReader(new InputStreamReader(
			        new FileInputStream(new File(filepath)),"UTF-8"))) {
			 String temp = null;
			 int senNum=0;
			 int wxsNum=0;
			 int dls=0;
		     ArrayList<Integer> wxs = new ArrayList<Integer>(); 
		     ArrayList<Integer> wxs1 = new ArrayList<Integer>(); 
		     ArrayList<Integer> wxs2 = new ArrayList<Integer>(); 
		     Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5_a-zA-Z0-9]+$");
			  while ((temp = br.readLine()) != null) {
            for(String s : BigSentence.toSentenceList(temp))
            {
         	   for(int i =0;i<s.length();i++)
         	   {	   if(pattern.matcher(s.charAt(i)+"").matches() )
         	   {
         		     senNum++;
         		     
         	   }
         	   
         	   
         	   }
         	   wxs.add(senNum);
         	   senNum=0;
            }
			 for(String s1: SmallSentence.toSentenceList(temp))
			 {
			//	 System.out.println(s1);
				 for(int i =0;i<s1.length();i++)
				   if(pattern.matcher(s1.charAt(i)+"").matches() )
         	   {
         		     senNum++;
         		     
         	   }	 
			
			  wxs1.add(senNum);
    	      senNum=0;
			 }
            for(int i =0;i<temp.length();i++)
            {
         	   if(pattern.matcher(temp.charAt(i)+"").matches() )
         	   {
         		     senNum++;
         		     
         	   }	 
            }
     //       System.out.println(senNum);
            wxs2.add(senNum);
            senNum=0;
           }
		  System.out.println(name+"大句长方差为"+getVariance(wxs));
		  System.out.println("小句长方差为"+getVariance(wxs1));
		  System.out.println("段落方差为"+getVariance(wxs2));
		 }
	}
    public static Double getVariance(ArrayList<Integer> xs)
    {
        Double xi=0D;
        Double averge=0D;
        Double sum=0D;
    	for(Integer x : xs)
    	{
    		xi=xi+x;
    	}
    	averge=xi/xs.size();
    	for(Integer x:xs)
    	{
    		sum=sum+Math.pow(x-averge, 2);
    	}
    	sum=sum/xs.size();
		return sum; 	
    }



}
