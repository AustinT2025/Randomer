package studio.visualdust.product.Randomer;

import studio.visualdust.product.Randomer.gui.MainFrame;
import studio.visualdust.product.Randomer.method.EventRW;
import studio.visualdust.product.Randomer.structure.LinedFile;
import studio.visualdust.product.Randomer.structure.ListItem;
import studio.visualdust.product.Randomer.structure.Shuffler;
import studio.visualdust.product.gztwigets.GMessageWindow;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class RandomerLauncher {
    static MainFrame mainFrame = new MainFrame(null);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            EventRW.Write(e);
        }
        ResetFonts();
//        new MainFrame();
        EventRW.Write("Randomer Launched");

        JFrame rubbishFrame = new JFrame();
        rubbishFrame.setSize(1, 1);
        rubbishFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        rubbishFrame.setVisible(false);
        GMessageWindow messageWindow = new GMessageWindow(rubbishFrame, 2, "请输入一个列表的路径");
        messageWindow.okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<ListItem> collection = new ArrayList<>();
                File file = new File(stripQuotes(messageWindow.getText()));
                if (!file.isFile()) {
                    EventRW.Write(new Exception("Studio.VisualDust.Product.Exception.FileNotEnabledException"));
                    System.exit(255);
                }
                LinedFile linedFile = new LinedFile(file);
                for (int i = 0; i < linedFile.getLineCount() - 1; i++) {
                    collection.add(new ListItem(linedFile.getLineOn(i)));
                }
                mainFrame.setShuffler(new Shuffler<>(collection));
                mainFrame.setVisible(true);
            }
        });

    }

    public static Font defaultFont = new Font("等线", 0, 17);

    public static void ResetFonts() {

        String names[] = {"Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem", "ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item + ".font", defaultFont);
        }
    }

    private static String stripQuotes(String s) {
        int begin = 0;
        int end = s.length();
        while (s.charAt(begin) == '"') {
            begin++;
        }
        while (s.charAt(end - 1) == '"') {
            end--;
        }
        return s.substring(begin, end);
    }
}