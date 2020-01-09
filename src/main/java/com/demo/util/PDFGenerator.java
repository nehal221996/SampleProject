package com.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator 
{
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(List<User> user) throws MalformedInputException,IOException
	{
		Document document =new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		 try 
		 {
			PdfWriter.getInstance(document, out);
			 document.open();
			 
			 /*Image image=Image.getInstance(((User) user).getProfile());
			 image.scaleAbsolute(150f, 150f);
			 image.setAbsolutePosition(30f, 685f);
			 document.add(image);*/
			 
				
			// Add Text to PDF file ->
				Font font = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);
				Paragraph para = new Paragraph("User Detail", font);
				para.setAlignment(Element.ALIGN_CENTER);

				document.add(para);
				document.add(Chunk.NEWLINE);
			 
			 
				float[] pointColumnWidths = { 25F, 75F, 75F, 75F, 75F, 75F, 75F };
				PdfPTable table = new PdfPTable(pointColumnWidths);
				table.setWidthPercentage(100);
				
				// Add PDF Table Header ->
				Stream.of("ID", "FirstName", "LastName", "Email", "Mobile", "Password", "Gender").forEach(headerTitle ->
				{
					PdfPCell header = new PdfPCell();
					Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					header.setBorderWidth(1);
					header.setPhrase(new Phrase(headerTitle, headFont));
					table.addCell(header);
				});
				
				for(User employee:user)
				{
					/*cell = new PdfPCell(new Phrase(employee.getId().toString()));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);*/
					
					PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(employee.getId())));
					// idCell.setPaddingLeft(5);
					idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(idCell);
					
					PdfPCell FirstnameCell = new PdfPCell(new Phrase(employee.getFirstname()));
					FirstnameCell.setPaddingLeft(11);
					FirstnameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					FirstnameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(FirstnameCell);
					
					PdfPCell LastnameCell = new PdfPCell(new Phrase(employee.getLastname()));
					LastnameCell.setPaddingLeft(11);
					LastnameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					LastnameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(LastnameCell);
					
					PdfPCell EmailCell = new PdfPCell(new Phrase(employee.getEmail()));
					EmailCell.setPaddingLeft(11);
					EmailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					EmailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(EmailCell);
					
					PdfPCell MobileCell = new PdfPCell(new Phrase(employee.getMobile()));
					MobileCell.setPaddingLeft(11);
					MobileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					MobileCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(MobileCell);
					
					PdfPCell PasswordCell = new PdfPCell(new Phrase(employee.getPassword()));
					PasswordCell.setPaddingLeft(11);
					PasswordCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PasswordCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(PasswordCell);
					
					PdfPCell GenderCell = new PdfPCell(new Phrase(employee.getGender()));
					GenderCell.setPaddingLeft(11);
					GenderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					GenderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(GenderCell);
					
				}
				document.add(table);
				document.close();
		} 
		 catch (DocumentException e) 
		 {
			 logger.error(e.toString());
		}
     
		return new ByteArrayInputStream(out.toByteArray());
		
	}
}
