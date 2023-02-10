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
public static String CaminhoImagem = "";  
  public static String caminhoCss = "";  
  public static String TextoTitulo = "Gextec - Gestem Tecnologia";  
  public static String TextoTituloInfo_1 = "-x-";  
  public static String TextoTituloInfo_2 = "Fone: (65) 3025-7352";  
  public static String TextoTituloInfo_3 = "-x-";  
  public static String TextoRodape = "";  
  public static String htmlCabecalho = "";  
  public static String htmlRodape = "";  
  public static String caminhoFont = "";  
  public static float marginTop = 50.0F;  
  public static float marginBottom = 30.0F;  
  public static float marginRight = 30.0F;  
  public static float marginLeft = 30.0F;  
  public static float marginTopCab = 30.0F;  
  public static Boolean isMarginInformada = false;  
  public static Boolean isEsconderCabRod = false;  
  public static Boolean isEsconderCab = false;  
  public static Boolean isEsconderRod = false;  
  public static Boolean isCabecalhoHtml = false;  
  public static Boolean isRodapeHtml = false;  
  public static Boolean isPaisagem = false;
  
  public static class HeaderFooterPageEvent extends PdfPageEventHelper {
    private PdfTemplate t;    
    private Image total;    
    protected ElementList header;    
    protected ElementList footer;    
    protected boolean exibirPaginacao = false;
    
    public HeaderFooterPageEvent() throws IOException {
      if (!CoplanConvert.isEsconderCabRod) {
        if (!CoplanConvert.isEsconderCab && CoplanConvert.isCabecalhoHtml == true && CoplanConvert.htmlCabecalho.length() > 0)
          this.header = CoplanConvert.parseToElementList(CoplanConvert.htmlCabecalho, CoplanConvert.caminhoCss); 
        if (CoplanConvert.htmlRodape.contains("#PAGINA_TOTAL#"))
          this.exibirPaginacao = true; 
        if (!CoplanConvert.isEsconderRod && CoplanConvert.isRodapeHtml == true && CoplanConvert.htmlRodape.length() > 0)
          this.footer = CoplanConvert.parseToElementList(CoplanConvert.htmlRodape.replace("#PAGINA_TOTAL#", ""), CoplanConvert.caminhoCss); 
      } 
    }
    
    @Override
    public void onOpenDocument(PdfWriter paramPdfWriter, Document paramDocument) {
      this.t = paramPdfWriter.getDirectContent().createTemplate(36.0F, 16.0F);
      try {
        this.total = Image.getInstance(this.t);
        this.total.setRole(PdfName.ARTIFACT);
      } catch (DocumentException localDocumentException) {
        throw new ExceptionConverter(localDocumentException);
      } 
    }
    
    @Override
    public void onEndPage(PdfWriter paramPdfWriter, Document paramDocument) {
      if (!CoplanConvert.isEsconderCab)
        addHeader(paramPdfWriter, paramDocument); 
      if (!CoplanConvert.isEsconderRod)
        addFooter(paramPdfWriter, paramDocument); 
    }
    
    private void addHeader(PdfWriter paramPdfWriter, Document paramDocument) {
      if (!CoplanConvert.isCabecalhoHtml) {
        addHeaderPdf(paramPdfWriter, paramDocument);
      } else {
        addHeaderHtml(paramPdfWriter, paramDocument);
      } 
    }
    
    private void addHeaderHtml(PdfWriter paramPdfWriter, Document paramDocument) {
      if (CoplanConvert.htmlCabecalho.length() > 0)
        try {
          
          int x1 = (int)((CoplanConvert.isPaisagem == true) ? 36 : 36);//36;
          int x2 = (int)((CoplanConvert.isPaisagem == true) ? (paramDocument.getPageSize().getWidth() - (CoplanConvert.marginLeft + CoplanConvert.marginRight)) : 529);//559;
          int y1 = (int)(paramDocument.getPageSize().getHeight() - CoplanConvert.marginTopCab);
          int y2 = (int)((CoplanConvert.isMarginInformada == true && CoplanConvert.marginTop > 30.0F) ? (700.0F - CoplanConvert.marginTop - 30.0F) : 700.0F);
          
          System.out.println(paramDocument.getPageSize().getHeight());
          System.out.println(paramDocument.getPageSize().getWidth());
          
          System.out.println(x1);
          System.out.println(x2);
          System.out.println(y1);
          System.out.println(y2);
          
          ColumnText ct = new ColumnText(paramPdfWriter.getDirectContent());
          //ct.setSimpleColumn(new Rectangle(x1, y1, x2, y2));
          if(CoplanConvert.isPaisagem == true){
              ct.setSimpleColumn(new Rectangle(CoplanConvert.marginLeft,550.0f,812.0f,0f));
          }else{
             ct.setSimpleColumn(new Rectangle(CoplanConvert.marginLeft, y1, x2, y2));
          }
            
          for (Element e : this.header)
            ct.addElement(e); 
          ct.go();
        } catch (Exception e) {
          throw new ExceptionConverter(e);
        }  
    }
    
    private void addHeaderPdf(PdfWriter paramPdfWriter, Document paramDocument) {
      try {
        PdfPTable localPdfPTable = new PdfPTable(2);
        localPdfPTable.setWidths(new int[] { 2, 24 });
        if (CoplanConvert.isPaisagem == true) {
          localPdfPTable.setTotalWidth(802.0F);
        } else {
          localPdfPTable.setTotalWidth(529.0F);
        } 
        localPdfPTable.setLockedWidth(true);
        localPdfPTable.getDefaultCell().setFixedHeight(40.0F);
        localPdfPTable.getDefaultCell().setBorder(2);
        localPdfPTable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
        try {
          Image localImage = Image.getInstance(new URL(CoplanConvert.CaminhoImagem));
          localPdfPTable.addCell(localImage);
        } catch (MalformedURLException localMalformedURLException) {
          System.out.println("Imagem na geracao do cabecalho do PDF nao localizada!");
        } 
        PdfPCell localPdfPCell = new PdfPCell();
        localPdfPCell.setPaddingBottom(10.0F);
        localPdfPCell.setPaddingLeft(10.0F);
        localPdfPCell.setBorder(2);
        localPdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
        localPdfPCell.addElement((Element)new Phrase(CoplanConvert.TextoTitulo, new Font(Font.FontFamily.HELVETICA, 12.0F, 1)));
        localPdfPCell.addElement((Element)new Phrase(CoplanConvert.TextoTituloInfo_1, new Font(Font.FontFamily.HELVETICA, 8.0F)));
        localPdfPCell.addElement((Element)new Phrase(CoplanConvert.TextoTituloInfo_2, new Font(Font.FontFamily.HELVETICA, 8.0F)));
        localPdfPCell.addElement((Element)new Phrase(CoplanConvert.TextoTituloInfo_3, new Font(Font.FontFamily.HELVETICA, 8.0F)));
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
      if (!CoplanConvert.isRodapeHtml || this.exibirPaginacao)
        addFooterPdf(paramPdfWriter, paramDocument); 
      if (CoplanConvert.isRodapeHtml)
        addFooterHtml(paramPdfWriter, paramDocument); 
    }
    
    private void addFooterHtml(PdfWriter paramPdfWriter, Document paramDocument) {
      if (CoplanConvert.htmlRodape.length() > 0)
        try {
          ColumnText ct = new ColumnText(paramPdfWriter.getDirectContent());
          int x1 = 36;
          int x2 = 559;
          int y1 = (int)((CoplanConvert.isMarginInformada == true && CoplanConvert.marginBottom > 30.0F) ? CoplanConvert.marginBottom : 30.0F);
          int y2 = 0;
          ct.setSimpleColumn(new Rectangle(x1, y1, x2, y2));
          ElementList f = this.footer;
          if (CoplanConvert.htmlRodape.contains("#PAGINA_ATUAL#"))
            f = CoplanConvert.parseToElementList(CoplanConvert.htmlRodape.replace("#PAGINA_ATUAL#", String.valueOf(paramPdfWriter.getPageNumber())), CoplanConvert.caminhoCss); 
          for (Element e : f)
            ct.addElement(e); 
          ct.go();
        } catch (Exception e) {
          throw new ExceptionConverter(e);
        }  
    }
    
    private void addFooterPdf(PdfWriter paramPdfWriter, Document paramDocument) {
      PdfPTable localPdfPTable = new PdfPTable(3);
      try {
        localPdfPTable.setWidths(new int[] { 10, 2, 1 });
        if (CoplanConvert.isPaisagem == true) {
          localPdfPTable.setTotalWidth(800.0F);
        } else {
          localPdfPTable.setTotalWidth(575.0F);
        } 
        localPdfPTable.setLockedWidth(true);
        localPdfPTable.getDefaultCell().setFixedHeight(5.0F);
        localPdfPTable.getDefaultCell().setBorder(0);
        localPdfPTable.addCell(new Phrase(CoplanConvert.TextoRodape, new Font(Font.FontFamily.HELVETICA, 9.0F, 1)));
        int paginaAtual = paramPdfWriter.getPageNumber();
        localPdfPTable.getDefaultCell().setHorizontalAlignment(2);
        localPdfPTable.addCell(new Phrase(String.format("Pag. %d de", new Object[] { Integer.valueOf(paginaAtual) }), new Font(Font.FontFamily.HELVETICA, 8.0F)));
        PdfPCell localPdfPCell = new PdfPCell(this.total);
        localPdfPCell.setBorder(0);
        localPdfPTable.addCell(localPdfPCell);
        PdfContentByte localPdfContentByte = paramPdfWriter.getDirectContent();
        localPdfContentByte.beginMarkedContentSequence(PdfName.ARTIFACT);
        localPdfPTable.writeSelectedRows(0, -1, 25.0F, CoplanConvert.marginBottom, localPdfContentByte);
        localPdfContentByte.endMarkedContentSequence();
      } catch (DocumentException localDocumentException) {
        throw new ExceptionConverter(localDocumentException);
      } 
    }
    
    @Override
    public void onCloseDocument(PdfWriter paramPdfWriter, Document paramDocument) {
      int i = String.valueOf(paramPdfWriter.getPageNumber()).length();
      int j = i * 5;
      ColumnText.showTextAligned((PdfContentByte)this.t, 2, new Phrase(String.valueOf(paramPdfWriter.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8.0F)), j, 6.0F, 0.0F);
    }
  }
  
  public static void Stringtopdf(String documentoHtml, String caminhoPdf) throws IOException, FileNotFoundException, DocumentException, NoSuchAlgorithmException, Exception {
    Document localDocument;
    PdfWriter localPdfWriter;
    TrustManager[] arrayOfTrustManager = { new X509TrustManager() {
          @Override
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }
          
          @Override
          public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {}
          
          @Override
          public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {}
        } };
    SSLContext localSSLContext = SSLContext.getInstance("SSL");
    localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
    HostnameVerifier local2 = (String paramAnonymousString, SSLSession paramAnonymousSSLSession) -> true;
    HttpsURLConnection.setDefaultHostnameVerifier(local2);
    FileOutputStream localFileOutputStream = new FileOutputStream(caminhoPdf);
    String html = corrigeUrlHtml(documentoHtml);
    if (isEsconderCabRod == true) {
      if (isPaisagem == true) {
        localDocument = new Document(PageSize.A4.rotate(), 30.0F, 30.0F, 30.0F, 30.0F);
      } else {
        localDocument = new Document(PageSize.A4, 30.0F, 30.0F, 30.0F, 30.0F);
      } 
      localPdfWriter = PdfWriter.getInstance(localDocument, localFileOutputStream);
    } else {
      if (isPaisagem == true) {
        if (isMarginInformada == true) {
          localDocument = new Document(PageSize.A4.rotate(), marginLeft, marginRight, marginTop, marginBottom);
        } else {
          localDocument = new Document(PageSize.A4.rotate(), 30.0F, 30.0F, 115.0F, 30.0F);
        } 
      } else if (isMarginInformada == true) {
        localDocument = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);
      } else {
        localDocument = new Document(PageSize.A4, 30.0F, 30.0F, 30.0F, 50.0F);
      } 
      localPdfWriter = PdfWriter.getInstance(localDocument, localFileOutputStream);
      HeaderFooterPageEvent localObject = new HeaderFooterPageEvent();
      localPdfWriter.setPageEvent((PdfPageEvent)localObject);
    } 
    localDocument.open();
    InputStream isCss = null;
    if (caminhoCss.length() > 0)
      isCss = new FileInputStream(caminhoCss); 
    InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
    if (caminhoFont.length() > 0) {
      XMLWorkerFontProvider xMLWorkerFontProvider = new XMLWorkerFontProvider(caminhoFont);
      XMLWorkerHelper.getInstance().parseXHtml(localPdfWriter, localDocument, is, isCss, Charset.forName("UTF-8"), (FontProvider)xMLWorkerFontProvider);
    } else {
      XMLWorkerHelper.getInstance().parseXHtml(localPdfWriter, localDocument, is, isCss, Charset.forName("UTF-8"));
    } 
    localDocument.close();
    localPdfWriter.close();
  }
  
  private static String corrigeUrlHtml(String documentoHtml) {
    String html = documentoHtml;
    String localhost = (html.contains("educacao_sinop") || html.contains("tributario_sinop")) ? "src=\"http://localhost:5022" : "src=\"http://localhost:5012";
    String pattern = "src=(\\\"|\\')https?:\\/\\/www\\.gp\\.srv\\.br(:80|:553)?";
    html = html.replaceAll(pattern, localhost);
    return html;
  }
    
  private static ElementList parseToElementList(String html, String caminhoCss) throws IOException {
    StyleAttrCSSResolver styleAttrCSSResolver = new StyleAttrCSSResolver();
    if (caminhoCss != null && caminhoCss.length() > 0) {
      InputStream isCss = new FileInputStream(caminhoCss);
      CssFile cssFile = XMLWorkerHelper.getCSS(isCss);
      styleAttrCSSResolver.addCss(cssFile);
    } 
    CssAppliersImpl cssAppliersImpl = new CssAppliersImpl((FontProvider)FontFactory.getFontImp());
    HtmlPipelineContext htmlContext = new HtmlPipelineContext((CssAppliers)cssAppliersImpl);
    htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
    htmlContext.autoBookmark(false);
    ElementList elements = new ElementList();
    ElementHandlerPipeline end = new ElementHandlerPipeline((ElementHandler)elements, null);
    HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, (Pipeline)end);
    CssResolverPipeline cssPipeline = new CssResolverPipeline((CSSResolver)styleAttrCSSResolver, (Pipeline)htmlPipeline);
    XMLWorker worker = new XMLWorker((Pipeline)cssPipeline, true);
    XMLParser p = new XMLParser((XMLParserListener)worker);
    InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
    p.parse(is, Charset.forName("UTF-8"));
    return elements;
  }  
}
