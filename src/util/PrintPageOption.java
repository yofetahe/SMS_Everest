/*
 * steps to print doc from java class
 * 1. load your document into the document
 * 2. create the print dialog, initialize it with the default parameters and display it on the screen.
 * 3. check the dialog result and proceed if the user accepted the print dialog
 * 4. create an instance of the DOCUMENT class
 * 5. create the print preview dialog and specify the DOCUMENT class as the target document, pass 
 *    the user specified print settings and then show the dialog
 */


package util;

import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;

public class PrintPageOption {
	
	public static String printDoc(){
	
		PrinterJob pj = PrinterJob.getPrinterJob();
		
		/////****Initialize the print dialog with the number of page in the document
		PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		attributes.add(new PageRanges(1, 1));//.add(new PageRenges(1, doc.getPageCount));
		
		/////**** proceed with print preview only if the user accepts the print dialog
		if(!pj.printDialog(attributes))
			return "";
		
		/////**** the object responsible for rendering our document for use with the Java Print API
		//AsposeWordsPringDocument awPrintDoc = new AsposeWordsPringDocument(doc);
		
		/////**** Pass our document as pageable to the printer job.
		//pj.setJobName(awPrintDoc);
		
		
		
		return "";
	
	}

}
