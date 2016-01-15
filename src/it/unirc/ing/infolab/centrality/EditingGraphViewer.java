package it.unirc.ing.infolab.centrality;

import edu.uci.ics.jung.algorithms.layout.Layout;


import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import it.unirc.ing.infolab.centrality.metrics.Betweenness;
import it.unirc.ing.infolab.centrality.metrics.Closeness;
import it.unirc.ing.infolab.centrality.util.Save;
import it.unirc.ing.infolab.centrality.util.SelectColor;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;


public class EditingGraphViewer implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	static Graph<Integer, String> g;
	int nodeCount, edgeCount;
	Factory <Integer> vertexFactory;
	Factory<String> edgeFactory;
	static ArrayList<Integer> colorArray = new ArrayList<Integer>();
	static ArrayList<Integer> nodeArray = new ArrayList<Integer>();
	

	public EditingGraphViewer() 
	{
		// Graph<V, E> where V is the type of the vertices and E is the type of the edges
		g = new SparseMultigraph<Integer, String>();
		nodeCount = 0; edgeCount = 0;
		vertexFactory = new Factory<Integer>() { // My vertex factory
			public Integer create() {
				return nodeCount++;
			}
		};
		edgeFactory = new Factory<String>() { // My edge factory
			public String create() {
				return "E"+edgeCount++;
			}
		};
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) 
	{

		final EditingGraphViewer sgv = new EditingGraphViewer();
		
		// Layout<V, E>, VisualizationViewer<V,E>
		final Layout<Integer, String> layout = new StaticLayout<Integer,String>(g);
		layout.setSize(new Dimension(300,300));
		final VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
		vv.setBackground(Color.white);
    	vv.setPreferredSize(new Dimension(350,350));
    	
    	Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
            public Paint transform(Integer i) {
                return (Paint) Color.WHITE;
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer((Transformer<Integer, java.awt.Paint>) vertexPaint);
        
		// Show vertex and edge labels
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());

		// Create a graph mouse and add it to the visualization viewer
		// Our Vertices are going to be Integer objects so we need an Integer factory
		EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), 
				sgv.vertexFactory, sgv.edgeFactory);      
		vv.setGraphMouse(gm);
		 
		JFrame frame = new JFrame("Editing Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		
//===========================================================================
// Menu for changing mouse modes
		
		JMenuBar menuBar = new JMenuBar();
		JMenu modeMenu = gm.getModeMenu();
		modeMenu.setText("Mode");
		modeMenu.setIcon(null); 		
		modeMenu.setPreferredSize(new Dimension(80,20)); 
		menuBar.add(modeMenu);

//---------------------------------------------------------------------------
//Menu Item to compute Centrality Measures

		JMenu centrality=new JMenu("Centrality");
		JMenuItem execB=new JMenuItem("Betweenness C.");
		execB.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {	
				Betweenness.compute(g);
			}
		});
		centrality.add(execB);
		
		
		JMenuItem execC=new JMenuItem("Closeness C");
		execC.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Closeness.compute(g);
			}
		});
		centrality.add(execC);
			
		menuBar.add(centrality);
		
//---------------------------------------------------------------------------
//Menu Item to change Vertices Color
		colorArray.add(2);
		
		JMenu selColor=new JMenu("NodeColor");
		
		JMenuItem selBlack=new JMenuItem("black");
		selBlack.addActionListener(new ActionListener() 
		{		
			@Override
			public void actionPerformed(ActionEvent e) {
		        
				colorArray.add(1);
				int v=g.getVertexCount();
				for(int j=0;j<nodeArray.size();j++)
					v=v-nodeArray.get(j);
				int vC= g.getVertexCount();
				nodeArray.add(v);
				SelectColor.choiceColor(1,vv,nodeArray,colorArray,vC);
				
			}
		});
		selColor.add(selBlack);
		
		
		JMenuItem selGray=new JMenuItem("gray");
		selGray.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
		
				colorArray.add(0);
				int v=g.getVertexCount();
				for(int j=0;j<nodeArray.size();j++)
					v=v-nodeArray.get(j);
				nodeArray.add(v);
				int vC= g.getVertexCount();
				SelectColor.choiceColor(0,vv,nodeArray,colorArray,vC);
		    }
		});
		selColor.add(selGray);
		
		
		JMenuItem selWhite=new JMenuItem("white");
		selWhite.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				colorArray.add(2);
				int v=g.getVertexCount();
				for(int j=0;j<nodeArray.size();j++)
					v=v-nodeArray.get(j);
				nodeArray.add(v);
				int vC= g.getVertexCount();
				SelectColor.choiceColor(2,vv,nodeArray,colorArray,vC);
			}
		});
		selColor.add(selWhite);
		
		JMenuItem selYellow=new JMenuItem("yellow");
		selYellow.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				colorArray.add(3);
				int v=g.getVertexCount();
				for(int j=0;j<nodeArray.size();j++)
					v=v-nodeArray.get(j);
				nodeArray.add(v);
				int vC= g.getVertexCount();
				SelectColor.choiceColor(3,vv,nodeArray,colorArray,vC);
			}
		});
		selColor.add(selYellow);
		
		menuBar.add(selColor);
		
//------------------------------------------------------------------------------------		
//MenuItem to save the graph as jpeg ,or eps, or to save code generation graph
		
		JMenu saveImg=new JMenu("Save");
	
		JMenuItem saveJPG=new JMenuItem("Save as jpeg");
		saveJPG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				Save.saveJpeg(vv);
				                  
			}
		});
		
		saveImg.add(saveJPG);
		
		JMenuItem saveEPS=new JMenuItem("Save as eps");
		saveEPS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				Save.saveEps(vv);
				                  
			}
		});
		
		saveImg.add(saveEPS);
		
		JMenuItem saveCode=new JMenuItem("Save Graph Code");
		saveCode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				Save.saveCode(g, colorArray, nodeArray);
				                  
			}
		});
		
		saveImg.add(saveCode);
		
		menuBar.add(saveImg);
		
//-------------------------------------------------------------------------------------		

		frame.setJMenuBar(menuBar);
		gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
	
		frame.pack();
		frame.setVisible(true);  
	
	}
	
}