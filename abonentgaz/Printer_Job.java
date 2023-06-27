/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import frames.PrintPreviewDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author Сергей
 */
public class Printer_Job {
    private String massage;
    private PageFormat pageformat;
    private PrintRequestAttributeSet attributes;
    private Graphics graphics;
    private Font font;

    public Printer_Job() {
    }

    /**
     * Создаёт объект для работы с принтером, выводит окно предварительного просмотра
     * и позволяет отправить на печать
     * @param massage
     * @param pageformat
     */
    public Printer_Job(String massage, PageFormat pageformat) {
        this.massage = massage;
        this.pageformat = pageformat;
        attributes = new HashPrintRequestAttributeSet();
    }

    /**
     * @param massage the massage to set
     */
    public void setMassage(String massage) {
        this.massage = massage;
    }

    /**
     * @param pageformat the pageformat to set
     */
    public void setPageformat(PageFormat pageformat) {
        this.pageformat = pageformat;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(PrintRequestAttributeSet attributes) {
        this.attributes = attributes;
    }
    
    public void Print_Preview(){
        PrintPreviewDialog dialog = new PrintPreviewDialog(makeBook());
        dialog.setVisible(true);
    }
    
    public void Page_Setup(){
        PrinterJob job = PrinterJob.getPrinterJob();
        pageformat = job.pageDialog(attributes);
    }
    
    /**
     * Создание объекта - книги с титульной и обычными страницами
     * @return объект - книгу
     */
    private Book makeBook(){
        if(getPageformat() == null){
            PrinterJob job = PrinterJob.getPrinterJob();
            pageformat = job.defaultPage();
        }
        Book book = new Book();
        Banner banner = new Banner(massage);
        banner.setF(font);
        int pageCount = banner.getPageCount((Graphics2D)graphics, getPageformat());
        book.append(new CoverPage(massage), getPageformat());
        book.append(banner, getPageformat(), pageCount);
        return book;
    }
    
    class Banner implements Printable {
        private final String massage;
        private double scale;
        private Font f;

        public Banner(String massage) {
            this.massage = massage;
        }

        /**
         * Вычсляет количество страниц для вывода на печать
         * @param g2 графический контекст
         * @param pf формат печатаемой страницы
         * @return количество полученных страниц для печати
         */
        public int getPageCount(Graphics2D g2, PageFormat pf){
            if(massage.equals("")) return 0;
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(massage, context);
            scale = pf.getImageableHeight() / bounds.getHeight();
            double width = scale * bounds.getWidth();
            int pages = (int)Math.ceil(width / pf.getImageableWidth());
            return pages;
        }
        
        
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, 
                int pageIndex) throws PrinterException {
            Graphics2D g2 = (Graphics2D)graphics;
            if(pageIndex > getPageCount(g2, pageFormat))
                return Printable.NO_SUCH_PAGE;
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            drawPage(g2, pageFormat, pageIndex);
            return Printable.PAGE_EXISTS;
        }
        
        public void drawPage(Graphics2D g2, PageFormat pageFormat, int page){
            if(massage.equals("")) return;
            page--;
            
            drawCropMarks(g2, pageFormat);
            g2.clip(new Rectangle2D.Double(0, 0, pageFormat.getImageableWidth(), 
                    pageFormat.getImageableHeight()));
            g2.translate(-page * pageFormat.getImageableWidth(), 0);
            g2.scale(scale,scale);
            FontRenderContext context = g2.getFontRenderContext();
            TextLayout layout = new TextLayout(massage, f, context);
            AffineTransform transform = AffineTransform.getTranslateInstance(0, 
                    layout.getAscent());
            Shape outline = layout.getOutline(transform);
            g2.draw(outline);
        }
        
        /**
         * Отображение контуров доступной для печати области с полями шириной 1/2 дюйма
         * @param g2 графический контекст
         * @param pageFormat формат страницы
         */
        public void drawCropMarks(Graphics2D g2, PageFormat pageFormat){
            final double C = 36;// отступ длиной полдюйма
            double w = pageFormat.getImageableWidth();
            double h = pageFormat.getImageableHeight();
            g2.draw(new Line2D.Double(0,0,0,C));
            g2.draw(new Line2D.Double(0,0,C,0));
            g2.draw(new Line2D.Double(w,0,w,C));
            g2.draw(new Line2D.Double(w,0,w - C,0));
            g2.draw(new Line2D.Double(0,h,0,h - C));
            g2.draw(new Line2D.Double(0,h,C,h));
            g2.draw(new Line2D.Double(w,h,w,h - C));
            g2.draw(new Line2D.Double(w,h,w - C,h));
        }

        /**
         * @param f the f to set
         */
        public void setF(Font f) {
            this.f = f;
        }
        
    }
    
    /**
     * Титульная страница с заголовком
     */
    class CoverPage implements Printable {

        private final String title;

        public CoverPage(String title) {
            this.title = title;
        }
        
        
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, 
                int pageIndex) throws PrinterException {
            if(pageIndex >= 1) return Printable.NO_SUCH_PAGE;
            Graphics2D g2 = (Graphics2D)graphics;
            g2.setPaint(Color.BLACK);
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            FontRenderContext context = g2.getFontRenderContext();
            Font f = g2.getFont();
            TextLayout layout = new TextLayout(title, f, context);
            float ascent = layout.getAscent();
            g2.drawString(title, 0, ascent);
            return Printable.PAGE_EXISTS;
        }
        
    }

    /**
     * @param graphics the graphics to set
     */
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * @return the pageformat
     */
    public PageFormat getPageformat() {
        return pageformat;
    }
}
