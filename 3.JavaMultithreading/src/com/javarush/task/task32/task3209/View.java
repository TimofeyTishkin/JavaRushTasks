package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Новый" :
                this.controller.createNewDocument();
                break;
            case "Открыть" :
                this.controller.openDocument();
                break;
            case "Сохранить" :
                this.controller.saveDocument();
                break;
            case "Сохранить как...":
                this.controller.saveDocumentAs();
                break;
            case "Выход":
                this.exit();
                break;
            case "О программе" :
                this.showAbout();
                break;
        }
    }

    public void init() {
        initGui();
        this.addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", scrollPane);
        JScrollPane jScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPane);
        tabbedPane.setPreferredSize(new Dimension(300, 300));
        TabbedPaneChangeListener changeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(changeListener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void exit() {
        this.controller.exit();
    }

    public void selectedTabChanged() {
        switch (this.tabbedPane.getSelectedIndex()) {
            case 0:
                controller.setPlainText(this.plainTextPane.getText());
                break;
            case 1:
                this.plainTextPane.setText(controller.getPlainText());
        }
                this.resetUndo();

    }

    public View() {
        try {
            UIManager.setLookAndFeel("System");
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public boolean isHtmlTabSelected() {
        return this.tabbedPane.getSelectedIndex() == 0;
    }
    public void selectHtmlTab(){
        this.tabbedPane.setSelectedIndex(0);
        this.resetUndo();
    }
    public void update(){
        try{
            this.htmlTextPane.setDocument(controller.getDocument());
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public void showAbout(){
        try{
            JOptionPane.showMessageDialog(tabbedPane.getSelectedComponent(),
                    "My first project, where I used to work with" +
                            " class swing and it's not so difficult as I expected and I really enjoyed while" +
                            " working on it" , "О программме", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
}
