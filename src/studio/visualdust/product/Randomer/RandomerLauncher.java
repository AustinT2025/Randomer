package studio.visualdust.product.Randomer;

import studio.visualdust.product.Randomer.gui.MainFrame;
import studio.visualdust.product.Randomer.method.EventRW;
import studio.visualdust.product.Randomer.structure.ListItem;
import studio.visualdust.product.Randomer.structure.LinedFile;
import studio.visualdust.product.Randomer.structure.ListItem;
import studio.visualdust.product.Randomer.structure.Shuffler;
import studio.visualdust.product.Randomer.structure.WeighedShuffler;
import studio.visualdust.product.gztwigets.GMessageWindow;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class RandomerLauncher {


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            EventRW.Write(e);
        }
        ResetFonts();

        MainFrame mainFrame = new MainFrame(null);

        JFrame rubbishFrame = new JFrame();
        rubbishFrame.setSize(1, 1);
        rubbishFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        rubbishFrame.setVisible(false);
        GMessageWindow messageWindow = new GMessageWindow(rubbishFrame, 2, "请输入一个列表的路径");
        messageWindow.okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageWindow.setWaring(false);
                ArrayList<ListItem> collection = new ArrayList<>();
                File file = new File(stripQuotes(messageWindow.getText()));
                if (!file.isFile() || !file.exists()) {
                    messageWindow.setVisible(true);
                    messageWindow.setText("File not enabled !!!");
                    messageWindow.setWaring(true);
                } else {
                    mainFrame.RefreshList(file);
                    mainFrame.setVisible(true);
                }
            }
        });

        messageWindow.cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventRW.Write("User exited");
                System.exit(0);
            }
        });

        messageWindow.noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventRW.Write("User exited");
                System.exit(0);
            }
        });

        EventRW.Write("Randomer Launched");
    }

    public static Font defaultFont = new Font("等线", Font.PLAIN, 17);

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
        final int size = s.length();
        int begin = 0;
        int end = size;
        while (begin < size && s.charAt(begin) == '"') {
            begin++;
        }
        while (end > 0 && s.charAt(end - 1) == '"') {
            end--;
        }
        return s.substring(begin, end);
    }
}
