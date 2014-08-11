package com.kalda.downloaddata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblReplyJavaRanch;

public class V3_generateCorpusJavaRanchPostNoCode {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		
		List<TblReplyJavaRanch> replyList = new ArrayList<TblReplyJavaRanch>();
		
		File file = null;
		BufferedWriter writer = null;
		TblReplyJavaRanch r = new TblReplyJavaRanch();
		
		
		System.out.println(replyList.size());
		
		
		for (int j=1;j<=111132;j++) {
			
			replyList = c.getReplyJavaRanch(j);
			r = replyList.get(0);
			
			try {
							
				String sss = r.getReplyContent();
				
				if (	sss.contains("<textarea") == false && 
						sss.contains("</textarea>")==false &&
						V3_generateCorpusJavaRanchPostNoCode.containJava(sss) == false &&
						V3_generateCorpusJavaRanchPostNoCode.containDumpStack(sss) == false &&
						V3_generateCorpusJavaRanchPostNoCode.containXML(sss) == false ) {
					
				

				}
				
				if (j%10000==0)
					System.out.println("generated file "+j);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static boolean containJava(String str) {
		//str = " function getXMLHttpRequest() {   var xmlHttpReq = false;   if (window.XMLHttpRequest) {     xmlHttpReq = new XMLHttpRequest();   } else if (window.ActiveXObject) {     try {       xmlHttpReq = new ActiveXObject('Msxml2.XMLHTTP');     } catch (exp1) {       try {         xmlHttpReq = new ActiveXObject('Microsoft.XMLHTTP');       } catch (exp2) {  ";
		String tempString = new String(str);
		Pattern p = Pattern.compile("[\\{\\}]");
		Matcher m = p.matcher(str);
		int counter = 0;
		while(m.find()) {
			counter++;
			System.out.println(m.group());
			System.out.println(m.start() + "-" + m.end());
		}
		if (counter>6) 
			return true;
		else 
			return false;
		
	}
	public static boolean containDumpStack(String str) {
		//str = "at org.apache.naming.factory.ResourceFactory.getObjectInstance(ResourceFactory.java:146) 	at javax.naming.spi.NamingManager.getObjectInstance(NamingManager.java:321) 	at org.apache.naming.NamingContext.lookup(NamingContext.java:826) 	at org.apache.naming.NamingContext.lookup(NamingContext.java:145) 	at org.apache.naming.NamingContext.lookup(NamingContext.java:814) 	at org.apache.naming.NamingContext";
		//str = "  at	 a.a.a.a.a.a.a.a.a(bb.java:123)   a.a.a.a.a.a.a.a.a(bb.java:123)  at a.a.a.a.a.a.a.a.a(bb.java:123)     ";
		Pattern p = Pattern.compile("at[\\s]+([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]+\\([_a-zA-Z0-9]+\\.java:\\d{1,5}\\)");
		Matcher m = p.matcher(str);
		int counter = 0;
		while(m.find()) {
			counter++;
			//System.out.println(m.group());
			//System.out.println(m.start() + "-" + m.end());
		}
		if (counter>3) 
			return true;
		else 
			return false;
	}
	
	public static boolean containXML(String str) {
		str = "</td> <%}%>   <td><select name='aa[<%=i%>]'> <option>accept</option> <option>Reject</option></select> </td>   </tr>    <% } %>   </table> <input type='submit' value='Submit'>            </form> </body> </html>";
		String tempString = new String(str);
		Pattern p = Pattern.compile("\\</?[^\\>]+\\>");
		Matcher m = p.matcher(str);
		int counter = 0;
		while(m.find()) {
			counter++;
			if (counter >10) {
				return true;
			}
			//System.out.println(m.group());
			//System.out.println(m.start() + "-" + m.end());
		}
		if (counter >10)
			return true;
		else 
			return false;
	}

}
