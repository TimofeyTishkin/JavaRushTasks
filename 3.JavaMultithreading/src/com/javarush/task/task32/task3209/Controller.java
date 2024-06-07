package com.javarush.task.task32.task3209;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
    public void init(){
        this.createNewDocument();
    }
    public void exit(){
        System.exit(0);
    }

    public HTMLDocument getDocument() {
        return document;
    }
    public void resetDocument() {
        if (this.document != null)
            this.document.removeUndoableEditListener(this.view.getUndoListener());
        this.document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        this.document.addUndoableEditListener(this.view.getUndoListener());
        this.view.update();
    }
    public void setPlainText(String text){
        try {
            resetDocument();
            StringReader reader = new StringReader(text);
            HTMLEditorKit editorKit = new HTMLEditorKit();
            editorKit.read(reader, this.document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }
    public String getPlainText(){
        StringWriter writer = new StringWriter();
        try{
            new HTMLEditorKit().write(writer, this.document, 0, document.getLength());
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void createNewDocument() {
        this.view.selectHtmlTab();
        this.resetDocument();
        this.view.setTitle("HTML editor");
        this.view.resetUndo();
        this.currentFile = null;
    }
    public void openDocument() {
        this.view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        chooser.setDialogTitle("Open File");
        int index = chooser.showOpenDialog(view);
        if(index == JFileChooser.APPROVE_OPTION){
            currentFile = chooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try(FileReader fileReader = new FileReader(currentFile);){
                new HTMLEditorKit().read(fileReader, document, 0);
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
            this.view.resetUndo();
        }
    }

    public void saveDocument() {
        if(currentFile == null) {
            saveDocumentAs();
            return;
        }
        this.view.selectHtmlTab();
        try {
            FileWriter fileWriter = new FileWriter(currentFile);
            new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            fileWriter.close();
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public void saveDocumentAs() {
        this.view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        chooser.setDialogTitle("Save File");
        int index = chooser.showSaveDialog(view);
        if(index == JFileChooser.APPROVE_OPTION){
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.close();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }
}
