package com.test.jaxb;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Component;

import com.test.marshaller.DataFeed;
import com.test.marshaller.Prop;
import com.test.model.BenefitAttributes;


@Component
public class JaxbEngine {

	public void runMarshalling() {
		try{
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(Student.class);
		    //creating the marshaller object
		    Marshaller marshallObj = jContext.createMarshaller();
		    //setting the property to show xml format output
		    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    //setting the values in POJO clas
		    Student student = new Student("waheed", 1163, "java");
		    //calling the marshall method
		    marshallObj.marshal(student, new FileOutputStream("C:/Users/john/Documents/w/student.xml"));
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
	public String processBenefit(List<BenefitAttributes> prop) {
		DataFeed df=new DataFeed();
		try {
			if(!prop.isEmpty()) {
				for(BenefitAttributes attribute:prop) {
					Prop p=new Prop();
					p.setAction(attribute.getAction());
					p.setAtt(attribute.getAtt());
					p.setID(BigInteger.valueOf(attribute.getID()));
					p.setType(attribute.getType());
					p.setVal(attribute.getVal());
					df.getProp().add(p);
				}
				String res=generateXML(df);
				return res;
			}
			
			
		}catch(Exception e) {
			
		}
		return "failed"; 
	}
	public String generateXML(DataFeed dfeed) {
		StringWriter writer=new StringWriter();
		try{
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(DataFeed.class);
		    //creating the marshaller object
		    Marshaller marshallObj = jContext.createMarshaller();
		    //setting the property to show xml format output
		    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    //setting the values in POJO clas
		    Student student = new Student("waheed", 1163, "java");
		    //calling the marshall method
		   // marshallObj.marshal(dfeed, new FileOutputStream("C:/Users/john/Documents/w/dfeed.xml"));
		    
		    marshallObj.marshal(dfeed, writer);
		    
		} catch(Exception e) {
		    e.printStackTrace();
		   return "failed";
		}
		return writer.toString();
	}
}
