package models;

import java.util.List;

import play.db.ebean.Model.Finder;

import com.avaje.ebean.Page;

public class CrudFinder<T extends CrudModel<T>> {
	
	public Finder<Long, T> finder;
	public String labelDbFieldName;
	
	public CrudFinder(Finder<Long, T> finder, String labelDbFieldName) {
		this.finder = finder;
		this.labelDbFieldName = labelDbFieldName;
	}

    public Page<T> page(final int page, final int pageSize, final String sortBy, final String order, final String filter) {
        return finder.where().ilike(labelDbFieldName, "%" + filter + "%").orderBy(sortBy + " " + order).findPagingList(pageSize).getPage(page);
    }    
    
    public List<T> all() {
        return finder.all();
    }

    public T byId(final Long id) {
        return finder.byId(id);
    }

    public T byLabel(final String label) {
        return finder.where().eq(labelDbFieldName, label).findUnique();
    }

    public void deleteById(final Long id) {
    	finder.ref(id).delete();
    }

}
