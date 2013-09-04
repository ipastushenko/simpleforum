package it.sevenbits.entity;

/**
 * Title
 * @author Ivan Pastushenko @sevenbits
 * @version 30.08.2013
 */
public class Title {
    private String name;

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
}
