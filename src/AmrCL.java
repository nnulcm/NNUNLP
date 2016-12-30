

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AmrCL 
{
	public static void main(String[] args) throws IOException {
		
	    String input_file = "";
	    String output_file = "";
	    
	    int c = 0;
	    while(c < args.length){
	        String arg = args[c];
	         if(arg.equals("-input")){
	        	input_file = args[++c];
	        }else if(arg.equals("-output")){
	        	output_file = args[++c];
	        }else {
	            //showhelp();
	        	return;
	        }
	        c ++;
	    }	
		 try (BufferedReader br = new BufferedReader(new InputStreamReader(
        new FileInputStream(new File(input_file)),"UTF-8"))) {
 String temp = null;
 FileOutputStream writerStream = new FileOutputStream(output_file);  
 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
 ArrayList< ArrayList<String>> textlist = new ArrayList<ArrayList<String>>();
 ArrayList<String> tempsen = new ArrayList<String>();
 int num=0;
 int maxid=0;
 int tempnum=0;
 int renum=0;//�滻����
 String tempstr="";
 HashMap<String,Integer> wordmap = new HashMap<>();
  while ((temp = br.readLine()) != null) { 	
  if(temp.contains("::id"))
  {
	  tempsen.add(temp);
  }
  else if(!temp.equals("")) 
  {
	  tempsen.add(temp);
  }
  else if(temp.equals(""))
  {
	  tempsen.add(temp);
	  textlist.add((ArrayList<String>) tempsen.clone());
      tempsen.clear();
  }

  }
  for(ArrayList<String> s:textlist)
  {
	  for(String sb:s)                                  
	  {	  
		  StringTokenizer st = new StringTokenizer(sb.trim()," ");
		  while(st.hasMoreTokens())
		  {
             if((tempstr=st.nextToken()).startsWith("(x"))
             {
            	 if(tempstr.contains("_"))
            	 {
            		String[] strtemp = tempstr.substring(2).split("_");
            		for(int i=0;i<strtemp.length;i++)
            		{
            			if(strtemp[i].replaceAll("[^0-9]", "").equals(""))
            				continue;
            			if(tempnum<Integer.parseInt(strtemp[i].replaceAll("[^0-9]", "")))
            			tempnum=Integer.parseInt(strtemp[i].replaceAll("[^0-9]", ""));
            		}
    //        	tempnum=Integer.parseInt(tempstr.substring(2,tempstr.indexOf("_")));
            	 }
            	 else if(tempstr.contains("-"))
            	 {
            		String[] strtemp = tempstr.substring(2).split("-");
            		for(int i=0;i<strtemp.length;i++)
            		{
            			if(strtemp[i].replaceAll("[^0-9]", "").equals(""))
            				continue;
           // 			System.out.println(strtemp[i].replaceAll("[^0-9]", ""));
            			if(tempnum<Integer.parseInt(strtemp[i].replaceAll("x", "")))
            			tempnum=Integer.parseInt(strtemp[i].replaceAll("x", ""));
            		}
    //        	tempnum=Integer.parseInt(tempstr.substring(2,tempstr.indexOf("_")));
            	 }
            	 else
            	 {
         			if(tempstr.substring(2).replaceAll("[^0-9]", "").equals(""))
        				continue;
            		 if(tempnum<Integer.parseInt(tempstr.substring(2).replaceAll("[^0-9]", "")))
            		 tempnum=Integer.parseInt(tempstr.substring(2));
            	 }
             	 if(tempnum>maxid)
           	 {
            		 maxid=tempnum;
           	 }
             }
		  }		
	  }
	  maxid++;
	  for(String sb:s)                                 
	  {	  
		  StringTokenizer sm = new StringTokenizer(sb.trim()," ");
		  while(sm.hasMoreTokens())
		  {
	             if((tempstr=sm.nextToken()).startsWith("(x"))
	             {
	            	 if(!wordmap.containsKey(tempstr))
	            	 {
	            	 wordmap.put(tempstr, 1);
	//            	 System.out.println(tempstr);
	            	 }
	            	 else
	            	 {
	            		  System.out.println("原句"+sb);
	//           		 System.out.println("");
	            		 if(sb.split("/")[1].trim().endsWith(")"))
	            		 {
	            			 sb=sb.split("/")[0]+")";
	            		 }
	            		 else
	            		 {
	            		 sb=sb.split("/")[0];
	            		 }
	            		 tempstr=tempstr.replace("(", "");
	            	   sb=sb.replace(tempstr, "x"+maxid+" / "+tempstr);
            	   System.out.println("改写为"+sb);
	            	   maxid++;
	            	   renum++;
	            	 }      	            	 
	            	 }     
		
		  }
		 
		    writer.write(sb+System.getProperty("line.separator"));
	  }
	  wordmap.clear();
//	  System.out.println("wordmap"+wordmap.size());
	  maxid=0;
	  tempnum=0;
  }
  
  
  
  
  
//  System.out.println(textlist.size());
//  for(ArrayList<String> s:textlist)
//  {
////	  System.out.println(s.size());
//	  for(String sb:s)
//	  {	  
//       writer.write(sb+System.getProperty("line.separator"));
//  //     writer.write("/n");
//	  }
//  }
//  
  
  writer.close();
  System.out.println("处理完毕，文件中共有"+textlist.size()+"个句子，一共替换了"+renum+"处");
}
	
	}
}
