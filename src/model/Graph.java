package model;

import java.io.PrintStream;
import java.util.Dictionary;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;
import javax.swing.text.Position;

public class Graph extends AbstractDocument {
	public Graph() {
		// TODO Auto-generated constructor stub
		super(data, context);
	}

	
	@Override
	public Element getDefaultRootElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getParagraphElement(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDocumentListener(DocumentListener listener) {
		// TODO Auto-generated method stub
		super.addDocumentListener(listener);
	}

	@Override
	public void addUndoableEditListener(UndoableEditListener listener) {
		// TODO Auto-generated method stub
		super.addUndoableEditListener(listener);
	}

	@Override
	public synchronized Position createPosition(int offs) throws BadLocationException {
		// TODO Auto-generated method stub
		return super.createPosition(offs);
	}

	@Override
	public void dump(PrintStream out) {
		// TODO Auto-generated method stub
		super.dump(out);
	}

	@Override
	public int getAsynchronousLoadPriority() {
		// TODO Auto-generated method stub
		return super.getAsynchronousLoadPriority();
	}

	@Override
	public Element getBidiRootElement() {
		// TODO Auto-generated method stub
		return super.getBidiRootElement();
	}

	@Override
	public DocumentFilter getDocumentFilter() {
		// TODO Auto-generated method stub
		return super.getDocumentFilter();
	}

	@Override
	public DocumentListener[] getDocumentListeners() {
		// TODO Auto-generated method stub
		return super.getDocumentListeners();
	}

	@Override
	public Dictionary<Object, Object> getDocumentProperties() {
		// TODO Auto-generated method stub
		return super.getDocumentProperties();
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return super.getLength();
	}
}
