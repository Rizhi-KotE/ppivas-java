package grapheditor.algo;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import grapheditor.view.elements.ViewGraphElement;

public class MinPath implements GraphPath {

	LinkedList<ViewGraphElement> path;

	public MinPath() {
		path = new LinkedList<>();
	}

	@Override
	public int size() {
		return path.size();
	}

	@Override
	public boolean isEmpty() {
		return path.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return path.contains(o);
	}

	@Override
	public Iterator<ViewGraphElement> iterator() {
		return path.iterator();
	}

	@Override
	public Object[] toArray() {
		return path.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return path.toArray(a);
	}

	@Override
	public boolean add(ViewGraphElement e) {
		return path.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return path.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return path.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends ViewGraphElement> c) {
		return path.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends ViewGraphElement> c) {
		return path.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return path.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return path.retainAll(c);
	}

	@Override
	public void clear() {
		path.clear();
		;
	}

	@Override
	public ViewGraphElement get(int index) {
		return path.get(index);
	}

	@Override
	public ViewGraphElement set(int index, ViewGraphElement element) {
		return path.set(index, element);
	}

	@Override
	public void add(int index, ViewGraphElement element) {
		path.add(index, element);
	}

	@Override
	public ViewGraphElement remove(int index) {
		return path.remove();
	}

	@Override
	public int indexOf(Object o) {
		return path.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return path.lastIndexOf(o);
	}

	@Override
	public ListIterator<ViewGraphElement> listIterator() {

		return path.listIterator();
	}

	@Override
	public ListIterator<ViewGraphElement> listIterator(int index) {
		return path.listIterator();
	}

	@Override
	public List<ViewGraphElement> subList(int fromIndex, int toIndex) {

		return path.subList(fromIndex, toIndex);
	}

}
