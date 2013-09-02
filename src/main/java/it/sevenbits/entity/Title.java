package it.sevenbits.entity;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 8/30/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Title {
    public Title() {
    }

    public Title(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Title title = (Title)obj;

        return name.equals(title.name);
    }

    @Override
    public int hashCode() {
        return 31*name.hashCode();
    }

    private String name;
}
