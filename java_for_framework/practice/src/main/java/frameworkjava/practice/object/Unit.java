package frameworkjava.practice.object;

import java.util.Objects;

public class Unit {

    Long id;
    Long level;
    String name;
    Long hp;

    public Unit(Long id, Long level, String name, Long hp) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", level=" + level +
                ", name=" + name +
                ", hp=" + hp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return this.hashCode() == unit.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, name, hp);
    }

}
