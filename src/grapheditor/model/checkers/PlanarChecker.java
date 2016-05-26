package grapheditor.model.checkers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import grapheditor.model.main.Graph;

public class PlanarChecker implements GraphChecker {

	class Segment{
	    ArrayList<ArrayList<Integer>> rebra = new ArrayList<>();
	    ArrayList<Integer> contact = new ArrayList<>();
	    ArrayList<Integer> inGrani = new ArrayList<>();
	}
	
	class Gran{
	    ArrayList<Integer> a = new ArrayList<>();
	}
	
	boolean ukladka(ArrayList<ArrayList<Integer>> graph, ArrayList<Gran> grani, ArrayList<Segment> segments, ArrayList<Integer> contact) {
		ArrayList<ArrayList<Integer>> graphUkladka = new ArrayList<>(graph.size());
		for (int i = 0; i < graph.size(); i++) {
			ArrayList<Integer> r = new ArrayList(graph.size());
			graphUkladka.add(i,r);
		}
		for (int i = 0; i < grani.get(0).a.size() - 1; i++) {// формирует матрицу
															// уложенного графа
			graphUkladka.get(grani.get(0).a.get(i)).add(grani.get(0).a.get(i + 1),1);
			graphUkladka.get(grani.get(0).a.get(i + 1)).add(grani.get(0).a.get(i),1);
		}
		graphUkladka.get(grani.get(0).a.get(0)).add(grani.get(0).a.get(grani.get(0).a.size()-1),1);
		graphUkladka.get(grani.get(0).a.get(grani.get(0).a.size())).add(grani.get(0).a.get(0),1);
		ArrayList<ArrayList<Integer>> rebrasviazi = new ArrayList<>();
		while (segments.size() != 0) {
			int n = segments.size();
			for (int z = 0; z < n; z++) {
				for (int i = 0; i < contact.size() - 1; i++)
					for (int j = i; j < contact.size(); j++)// ищет ребра
																// концы которых
																// - контактные
																// вершины
					{ // формирует из них сегменты
						Segment segment = new Segment();
						if (segments.get(z).rebra.get(contact.get(i)).get(contact.get(j))==null) {

							ArrayList<Integer> a = new ArrayList<Integer>(graph.size());
							for (int y = 0; y < graph.size(); y++)
								segment.rebra.add(a);
							if (graphUkladka.get(contact.get(i)).get(contact.get(j)) == 0) {
								segment.rebra.get(contact.get(i)).add(contact.get(j),1);
								segment.rebra.get(contact.get(j)).add(contact.get(i),1);
								segment.contact.add(contact.get(j));
								segment.contact.add(contact.get(i));
							}
							segments.get(z).rebra.get(contact.get(i)).add(contact.get(j),0);
							segments.get(z).rebra.get(contact.get(j)).add(contact.get(i),0);

						}
						if (segment.contact.size()!=0)

						{
							segments.add(segment);
							segment.rebra.clear();
							segment.contact.clear();
						}
					}
				// n = segments.size();
				for (int i = 0; i < contact.size(); i++)
					for (int j = 0; j < segments.get(z).rebra.size(); j++)
						if (segments.get(z).rebra.get(contact.get(i)).get(j)!=null) {
							ArrayList<Integer> t = new ArrayList<>();
							t.add(j);
							t.add(contact.get(i));
							rebrasviazi.add(t);
							segments.get(z).rebra.get(contact.get(i)).add(j,0);
							segments.get(z).rebra.get(j).add(contact.get(i),0);
						}
				if (segments.get(z).contact.size() >= 2) {
					// find_comps(segments.get(z).rebra, segments);
					segments.remove(z);
					z--;
					n--;
				}
				// segments.erase(segments.begin(), segments.begin() + n);
			}
			for (int x = n; x < segments.size(); x++) {
				int s = segments.get(x).contact.size();
				int c;
				boolean bl = false;
				for (int h = 0; h < s; h++) {

					for (int v = 0; v < rebrasviazi.size(); v++)// добавляет
																	// в
																	// сегмент
																	// ребра
																	// которые
																	// начинаютя
																	// на
																	// вершинах
																	// сегмента
																	// и
																	// заканчиваютя
																	// на
																	// контактных
																	// вершинах
						if (rebrasviazi.get(v).get(0) == segments.get(x).contact.get(h)) {
							segments.get(x).rebra.get(rebrasviazi.get(v).get(0)).add(rebrasviazi.get(v).get(1),1);
							segments.get(x).rebra.get(rebrasviazi.get(v).get(1)).add(rebrasviazi.get(v).get(0),1);
							segments.get(x).contact.add(rebrasviazi.get(v).get(1));
							rebrasviazi.remove(v);
							v--;
							bl = true;
						}
				}
				if (bl) {
					// удаяет лишние контактные вершины сегмента
					segments.get(x).contact.subList(0, s).clear();

					//
					for (int r = 0; r < segments.get(x).contact.size(); r++) {
						for (int e = r; e < segments.get(x).contact.size(); e++) {
							if (segments.get(x).contact.get(r) > segments.get(x).contact.get(e)) {
								int t = segments.get(x).contact.get(r);
								segments.get(x).contact.add(r,segments.get(x).contact.get(e));
								segments.get(x).contact.add(e,t);
							}
						}
					}
					int r = 1;
					while (r < segments.get(x).contact.size()) {
						if (segments.get(x).contact.get(r) == segments.get(x).contact.get(r - 1)) {
							segments.get(x).contact.remove(r);
							r--;
						}
						r++;
					}
				}

			}
			// segments.erase(segments.begin(), segments.begin() + n);
			for (int u = 0; u < segments.size(); u++)
				if (segments.get(u).contact.size() <= 1) {
					segments.remove(u);
					u--;
				}

			poiskGranei(grani, segments);// ищет грани в которые входят
											// сегменты, сортирует масств
											// сегментов по кол-ву таких граней
			if (segments.get(0).inGrani.size() == 0)// если первый сегмент не имеет
												// граней
				return false; // граф не пранарный
			ArrayList<Integer> cep;
			ArrayList<Integer> vibrGrani = segments.get(0).inGrani;
			cep = viborCepi(grani, segments);// выбирает сепь из первого
												// сегмента
			for (int i = 0; i < cep.size() - 1; i++) {// формирует матрицу графа
														// укладки
				graphUkladka.get(cep.get(i)).add(cep.get(i + 1),1);
				graphUkladka.get(cep.get(i + 1)).add(cep.get(i),1);
			}
			for (int i = 1; i < cep.size() - 1; i++)
				contact.add(cep.get(i));// обновляется массив контактных
											// вершин
			/*
			 * for (int i = 0; i < cep.size(); ++i) printf("%d ", cep.get(i));
			 */

			vstavkaCepi(grani, segments, cep);// цепь вставляется в грань
			if (segments.get(0).contact.size() == 2)
				segments.remove(0);
		}
		return true;

	}

	void poiskGranei(ArrayList<Gran> grani, ArrayList<Segment> segments)
	{
	    for (int i = 0; i < segments.size(); i++){//Находит грани в которые входят сегменты
	        for (int z = 0; z < grani.size(); z++){
	            boolean t = true;
	            for (int j = 0; j < segments.get(i).contact.size(); j++){
	                boolean bl = false;
	                for (int k = 0; k < grani.get(z).a.size(); k++){
	                    if (segments.get(i).contact.get(j) == grani.get(z).a.get(k)){
	                        bl = true;
	                        break;
	                    }
	                }
	                if (!bl){
	                    t = bl;
	                    break;
	                }
	            }
	            if (t)
	                segments.get(i).inGrani.add(z);
	        }
	    }
	    for (int i = 0; i < segments.size() - 1; i++){
	        for (int j = i + 1; j < segments.size(); j++)
	        if (segments.get(i).inGrani.size()>segments.get(j).inGrani.size()){
	            Segment temp = segments.get(i);
	            segments.add(i,segments.get(j));
	            segments.add(j,temp);
	        }
	    }
	}

	ArrayList<Integer> viborCepi(ArrayList<Gran> grani, ArrayList<Segment> segments)
	{

	ArrayList<Integer> used = new ArrayList<>(segments.get(0).rebra.size());
	    ArrayList<Integer> cep = new ArrayList<>();
	    for (int i = 0; i < segments.get(0).contact.size(); i++)
	        used.add(segments.get(0).contact.get(i),2);
	    cep.add(segments.get(0).contact.get(0));
	    int a = cep.get(cep.size()-1);
	    while (true)
	    {
	        for (int i = 0; i < segments.get(0).rebra.size(); i++){
	            if (segments.get(0).rebra.get(a).get(i) != 0){
	                if ((cep.size()>1) && (cep.get(cep.size() - 2) == i)){
	                    continue;
	                }
	                a = i;
	                break;
	            }
	        }
	        if (a != cep.get(cep.size()-1))
	            cep.add(a);
	        if ((cep.size()>1) && (used.get(cep.get(cep.size() - 1)) == 2))
	            break;
	    }
	    boolean bl = false;
	    for (int i = 0; i < segments.get(0).rebra.size(); i++)
	    {
	        if (segments.get(0).rebra.get(i).size() != 0){
	            bl = true;
	            break;
	        }
	    }
	    return cep;
	}

	void vstavkaCepi(ArrayList<Gran> grani,ArrayList<Segment> segments, ArrayList<Integer> cep)
	{
	    int i;
	    int gran = segments.get(0).inGrani.get(0);
	    for (i = 0; i < grani.get(gran).a.size();i++)
	    if (cep.get(0) == grani.get(gran).a.get(i))
	        break;
	    int j;
	    for (j = 0; j < grani.get(gran).a.size(); j++)
	    if (cep.get(cep.size()-1) == grani.get(gran).a.get(j))
	        break;
	    Gran dopGran = new Gran();
	    if (i>j){
	    	Collections.reverse(grani.get(gran).a);
	        for (i = 0; i < grani.get(gran).a.size(); i++)
	        if (cep.get(0) == grani.get(gran).a.get(i))
	            break;
	        for (j = 0; j < grani.get(gran).a.size(); j++)
	        if (cep.get(cep.size()-1) == grani.get(gran).a.get(j))
	            break;
	    }
	    resize(dopGran.a, j-i+cep.size()-1);

	    dopGran.a = new ArrayList<>(grani.get(gran).a.subList(i, j));
	  
	    grani.get(gran).a.subList(i, j).clear();
	    int k = grani.get(gran).a.size();
	    resize(grani.get(gran).a, grani.get(gran).a.size() + cep.size()-1);
	    grani.get(gran).a.addAll(k, cep.subList(0, cep.size()-2));
	    //copy(cep.get(0), cep.end() - 1, dopGran.a.get(0) + j - i);
	    Collections.reverse(cep);
	    dopGran.a.addAll(j - i, cep.subList(0, cep.size()-2));
	    grani.add(dopGran);
	}

	private void resize(ArrayList<Integer> list, int size){
		ArrayList<Integer> out = new ArrayList<>(size);
		Iterator<Integer> listIt = list.iterator();
		while(listIt.hasNext()){
			out.add(listIt.next());
		}
		for(int i = out.size();i<size;i++){
			out.add(new Integer(0));
		}
		list = out;
	}
	@Override
	public boolean test(Graph t) {
		
		return ukladka(graph, grani, segments, contact);
	}
}