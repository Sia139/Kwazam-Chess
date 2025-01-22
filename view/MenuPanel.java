//MenuPanel.java
package view;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuPanel extends JMenuBar{

    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem exitMenuItem;

    public MenuPanel(){

        fileMenu = new JMenu("File");

        this.setSize(15, 10);

        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        this.add(fileMenu);
    }


    public JMenuItem getNewMenuItem(){
        return newMenuItem;
    }

    public JMenuItem getOpenMenuItem(){
        return openMenuItem;
    }

    public JMenuItem getSaveMenuItem(){
        return saveMenuItem;
    }

    public JMenuItem getExitMenuItem(){
        return exitMenuItem;
    }

}
