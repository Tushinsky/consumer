/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author ������
 */
public class PrintPreviewDialog extends JDialog {

    private static int Width = 1000;
    private static int Height = 1000;
    private PrintPreviewCanvas canvas;
    /**
     * �������� ����������� ���� ���������������� ���������
     * @param p ������, ����������� ��������� Printable (������)
     * @param pf ������ ��������
     * @param pages ���������� ������� � ������� ������
     */
    public PrintPreviewDialog(Printable p, PageFormat pf, int pages) {
        Book book = new Book();
        book.append(p, pf, pages);
        layoutUI(book);
    }

    /**
     * �������� ����������� ���� ���������������� ���������
     * @param b ������ �����, � ������� �������� �������������� ������
     */
    public PrintPreviewDialog(Book b) {
        layoutUI(b);
    }
    
    /**
     * ����� ����������������� ���������� �����������
     * ���� ���������������� ���������
     * @param book ������, ������� ����� ������������ � ���� ���������
     */
    public final void layoutUI(Book book){
        // ������ ����
        setSize(Width, Height);
        Container contentPane = getContentPane();// ������ �����������
        // ������ ����� ���������������� ���������
        canvas = new PrintPreviewCanvas(book);
        contentPane.add(canvas, BorderLayout.CENTER);
        
        // ������ ������ � ��������
        JPanel buttonPanel = new JPanel();
        JButton firstButton = new JButton("|< First");
        firstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.flipPage(- canvas.getCurrentPage());
            }
        });
        JButton nextButton = new JButton("Next >");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.flipPage(1);
            }
        });
        JButton previousButton = new JButton("< Previous");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.flipPage(-1);
            }
        });
        JButton lastButton = new JButton("Last >|");
        lastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.flipPage(canvas.getNumberOfPages() - canvas.getCurrentPage() - 1);
            }
        });
        buttonPanel.add(firstButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(lastButton);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);// �������� ����
            }
        });
        buttonPanel.add(closeButton);
        // ��������� ������ � ��������
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    class PrintPreviewCanvas extends JPanel{
        private final Book book;
        private int currentPage;

        public PrintPreviewCanvas(Book book) {
            this.book = book;
            currentPage = 0;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            Graphics2D g2 = (Graphics2D) g;
            PageFormat pageFormat = book.getPageFormat(getCurrentPage());
            double xoff;// ����� ������� ��������
            double yoff;// ������� ������� ��������
            double scale;// ���������� ����������� ��� �������� �������� ��������
                            // � �������� ��������� ������������� ������� ������
            double px = pageFormat.getWidth();
            double py = pageFormat.getHeight();
            double sx = getWidth() - 1;
            double sy = getHeight() - 1;
            
            if(px/py < sx/sy){
                // ������������� �� �����������
                scale = sy / py;
                xoff = 0.5 * (sx - scale * px);
                yoff = 0;
            } else {
                // ������������� �� ���������
                scale = sx / px;
                xoff = 0;
                yoff = 0.5 * (sy - scale *py);
            }
            g2.translate((float)xoff, (float)yoff);
            g2.scale((float)scale, (float)scale);
            
            // ��������� �������� �������� (��� �����)
            Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
            g2.setPaint(Color.white);
            g2.fill(page);
            g2.setPaint(Color.BLACK);
            g2.draw(page);
            
            Printable printable = book.getPrintable(getCurrentPage());
            try{
                printable.print(g2, pageFormat, getCurrentPage());
            } catch (PrinterException ex) {
                g2.draw(new Line2D.Double(0, 0, px, py));
                g2.draw(new Line2D.Double(0, px, 0, py));
            }
        }
        
        /**
         * ������� � �������� � �������� �������
         * @param by ����� ��������, �� ������� �������������� �������
         */
        public void flipPage(int by){
            int newPage = getCurrentPage() + by;
            // ��������� ����� ��������, �� ������� ��������� ������������
            if(0 <= newPage && newPage < book.getNumberOfPages()){
                setCurrentPage(newPage);
                repaint();
            }
        }

        /**
         * @return the currentPage
         */
        public int getCurrentPage() {
            return currentPage;
        }

        /**
         * @param currentPage the currentPage to set
         */
        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
        
        public int getNumberOfPages(){
            return book.getNumberOfPages();
        }
    }
}
