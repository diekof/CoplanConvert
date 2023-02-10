/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.coplan.coplanconvert;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementHandler;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.parser.XMLParserListener;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/**
 *
 * @author diego.donizete
 */
public class CoplanConvert {

    public static String CaminhoImagem = "/memorynotfound-logo.jpg";
    public static String TextoTitulo = "Gextec - GestÃ£o em Tecnologia";
    public static String TextoTituloInfo_1 = "-x-";
    public static String TextoTituloInfo_2 = "Fone: (65) 3025-7352";
    public static String TextoTituloInfo_3 = "-x-";
    public static String TextoRodape = "";
    public static Boolean isEsconderCabRod = false;
    public static Boolean isEsconderCab = false;
    public static Boolean isEsconderRod = false;
    public static Boolean isPaisagem = false;

    public static class HeaderFooterPageEvent
            extends PdfPageEventHelper {

        private PdfTemplate t;
        private Image total;

        public void onOpenDocument(PdfWriter paramPdfWriter, Document paramDocument) {
            this.t = paramPdfWriter.getDirectContent().createTemplate(36.0F, 16.0F);
            try {
                this.total = Image.getInstance(this.t);
                this.total.setRole(PdfName.ARTIFACT);
            } catch (DocumentException localDocumentException) {
                throw new ExceptionConverter(localDocumentException);
            }
        }

        public void onEndPage(PdfWriter paramPdfWriter, Document paramDocument) {
            if(CoplanConvert.isEsconderCab == false){
                addHeader(paramPdfWriter, paramDocument);
            }
            
            if(CoplanConvert.isEsconderRod == false){
                addFooter(paramPdfWriter, paramDocument);
            }
        }

        private void addHeader(PdfWriter paramPdfWriter, Document paramDocument) {
            PdfPTable localPdfPTable = new PdfPTable(2);
            try {
                localPdfPTable.setWidths(new int[]{2, 24});
                if (CoplanConvert.isPaisagem == true) {
                    localPdfPTable.setTotalWidth(802.0F);
                } else {
                    localPdfPTable.setTotalWidth(529.0F);
                }
                localPdfPTable.setLockedWidth(true);
                localPdfPTable.getDefaultCell().setFixedHeight(40.0F);
                localPdfPTable.getDefaultCell().setBorder(2);
                localPdfPTable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

                Image localImage = Image.getInstance(new URL(CoplanConvert.CaminhoImagem));

                localPdfPTable.addCell(localImage);

                PdfPCell localPdfPCell = new PdfPCell();
                localPdfPCell.setPaddingBottom(10.0F);
                localPdfPCell.setPaddingLeft(10.0F);
                localPdfPCell.setBorder(2);
                localPdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
                localPdfPCell.addElement(new Phrase(CoplanConvert.TextoTitulo, new Font(Font.FontFamily.HELVETICA, 12.0F, 1)));
                localPdfPCell.addElement(new Phrase(CoplanConvert.TextoTituloInfo_1, new Font(Font.FontFamily.HELVETICA, 8.0F)));
                localPdfPCell.addElement(new Phrase(CoplanConvert.TextoTituloInfo_2, new Font(Font.FontFamily.HELVETICA, 8.0F)));
                localPdfPCell.addElement(new Phrase(CoplanConvert.TextoTituloInfo_3, new Font(Font.FontFamily.HELVETICA, 8.0F)));
                localPdfPTable.addCell(localPdfPCell);

                localPdfPTable.writeSelectedRows(0, -1, paramDocument.left(), paramDocument.top() + (paramDocument.topMargin() + localPdfPTable.getTotalHeight()) / 2.0F, paramPdfWriter.getDirectContent());
            } catch (DocumentException localDocumentException) {
                throw new ExceptionConverter(localDocumentException);
            } catch (MalformedURLException localMalformedURLException) {
                throw new ExceptionConverter(localMalformedURLException);
            } catch (IOException localIOException) {
                throw new ExceptionConverter(localIOException);
            }
        }

        private void addFooter(PdfWriter paramPdfWriter, Document paramDocument) {
            PdfPTable localPdfPTable = new PdfPTable(3);
            try {
                localPdfPTable.setWidths(new int[]{24, 2, 1});
                if (CoplanConvert.isPaisagem == true) {
                    localPdfPTable.setTotalWidth(800.0F);
                } else {
                    localPdfPTable.setTotalWidth(550.0F);
                }
                localPdfPTable.setLockedWidth(true);
                localPdfPTable.getDefaultCell().setFixedHeight(5.0F);
                localPdfPTable.getDefaultCell().setBorder(0);

                localPdfPTable.addCell(new Phrase(CoplanConvert.TextoRodape, new Font(Font.FontFamily.HELVETICA, 9.0F, 1)));

                localPdfPTable.getDefaultCell().setHorizontalAlignment(2);
                localPdfPTable.addCell(new Phrase(String.format("Pag. %d de", new Object[]{Integer.valueOf(paramPdfWriter.getPageNumber())}), new Font(Font.FontFamily.HELVETICA, 8.0F)));

                PdfPCell localPdfPCell = new PdfPCell(this.total);
                localPdfPCell.setBorder(0);
                localPdfPTable.addCell(localPdfPCell);

                PdfContentByte localPdfContentByte = paramPdfWriter.getDirectContent();
                localPdfContentByte.beginMarkedContentSequence(PdfName.ARTIFACT);
                localPdfPTable.writeSelectedRows(0, -1, 30.0F, 30.0F, localPdfContentByte);

                localPdfContentByte.endMarkedContentSequence();
            } catch (DocumentException localDocumentException) {
                throw new ExceptionConverter(localDocumentException);
            }
        }

        public void onCloseDocument(PdfWriter paramPdfWriter, Document paramDocument) {
            int i = String.valueOf(paramPdfWriter.getPageNumber()).length();
            int j = i * 5;
            ColumnText.showTextAligned(this.t, 2, new Phrase(String.valueOf(paramPdfWriter.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8.0F)), j, 6.0F, 0.0F);
        }
    }

    public static void Stringtopdf(String paramString1, String paramString2)
            throws IOException, FileNotFoundException, DocumentException, NoSuchAlgorithmException, Exception {
        TrustManager[] arrayOfTrustManager = {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {
            }

            public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {
            }
        }};
        SSLContext localSSLContext = SSLContext.getInstance("SSL");
        localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());

        HostnameVerifier local2 = new HostnameVerifier() {
            public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(local2);
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
        
        String html         = paramString1;
        String localhost    = html.contains("tributario_sinop") ? "src=\"http://localhost:5022" : "src=\"http://localhost:5012";
        String pattern      = "src=(\\\"|\\')https?:\\/\\/www\\.gp\\.srv\\.br(:80|:553)?";
        html                = html.replaceAll(pattern,localhost);
        
        Document localDocument;
        PdfWriter localPdfWriter;
        if (isEsconderCabRod == true) {
            if (isPaisagem == true) {
                localDocument = new Document(PageSize.A4.rotate(), 30.0F, 30.0F, 30.0F, 30.0F);
            } else {
                localDocument = new Document(PageSize.A4, 30.0F, 30.0F, 30.0F, 30.0F);
            }
            localPdfWriter = PdfWriter.getInstance(localDocument, localFileOutputStream);
        } else {
            if (isPaisagem == true) {
                localDocument = new Document(PageSize.A4.rotate(), 30.0F, 30.0F, 115.0F, 30.0F);
            } else {
                localDocument = new Document(PageSize.A4, 30.0F, 30.0F, 30.0F, 50.0F);
            }
            localPdfWriter = PdfWriter.getInstance(localDocument, localFileOutputStream);

            HeaderFooterPageEvent localObject = new CoplanConvert.HeaderFooterPageEvent();
            localPdfWriter.setPageEvent((PdfPageEvent) localObject);
        }
        localDocument.open();

        // Realiza a magica
        ByteArrayInputStream localObject = new ByteArrayInputStream(html.getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(localPdfWriter, localDocument, (InputStream) localObject);

        // Fecha os arquivos abertos
        localDocument.close();
        localPdfWriter.close();
    }   
}
