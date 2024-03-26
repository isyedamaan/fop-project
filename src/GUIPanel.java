package fop_assignment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.SwingUtilities;

public class GUIPanel extends JPanel {
    String windowName;
    private final int width = 1000;
    private final int heigth = 500;
    private final int padding = 50;
    private final int labelPadding = 25;
    private final Color lineColor = new Color(44, 102, 230, 180);
    private final Color pointColor = new Color(255, 100, 100, 180);
    private final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth =6;
    private int numberYDivisions;// = 10;
    private int[] GraphData;
    private String[] xLabel;
    private String title;
    int tableWidth = 300;
    String[][] tableData;    
    String[] tableColumns;
    
    
    //constructor
    public GUIPanel(String title, int[] GraphData, String[] xLabel) {
        this.GraphData = GraphData;
        this.title = title;
        this.xLabel = xLabel;
        this.windowName = title;
        //for y divisions
        int digitCount = Integer.toString((int)getMaxValue()).length();         //digits in max value
        this.numberYDivisions = (int)Math.ceil(2*getMaxValue()/Math.pow(10,digitCount-1));
    }
    public void setTable(String[][] data, String[] columns){
        this.tableData = data;
        this.tableColumns = columns;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (GraphData.length - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / getMaxValue();
        
        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < GraphData.length; i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxValue() - GraphData[i]) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (GraphData.length!=0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) (((getMaxValue()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < GraphData.length; i++) {
            if (GraphData.length > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (GraphData.length - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((GraphData.length / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel[i]);
                    g2.drawString(xLabel[i], x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }
        
        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        
        FontMetrics metrics = g2.getFontMetrics();
        int xLabelWidth = metrics.stringWidth(title);
        g2.drawString("Graph: ",padding + labelPadding, 40);
        
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
       return new Dimension(width+tableWidth+padding, getHeight());
    }
    @Override
    public int getHeight(){
        return 500;
    }
    @Override
    public int getWidth(){
        return 1000;
    }
    
    
    private double getMaxValue() {
        double maxValue = 0d;
        for (int Value : GraphData) {
            maxValue = Math.max(maxValue, Value);
        }
        String max = Integer.toString((int)maxValue);
        int digitCount = max.length();
        int modifier = 5 * (int)Math.pow(10, digitCount-2);
        return Math.ceil(maxValue/modifier) * modifier;
    }
    public void setTableWidth(int size){
        tableWidth = size;
    }
    
    private void createAndShowGui(GUIPanel mainPanel) {
        mainPanel.setPreferredSize(new Dimension(width, heigth));
        JFrame frame = new JFrame(windowName);
        
        //Title
        JLabel label = new JLabel();
        label.setText(title);
        label.setBounds((width+tableWidth+padding)/2-110, 8, tableWidth, 15);
        frame.getContentPane().add(label);
        
        //Table
        JTable jt=new JTable(tableData,tableColumns); 
        jt.setRowHeight(20);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(width, padding, tableWidth, Math.min(377,(tableData.length+1)*20)+3); //377
        frame.getContentPane().add(sp);
        
        //Graph
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void makeGraph(GUIPanel mainPanel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui(mainPanel);
            }
        });
        System.out.println("GUI window has been created.");
   }
}
