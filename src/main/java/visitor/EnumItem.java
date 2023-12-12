package visitor;

public class EnumItem {
    public final String name;
    public final int value;

    public EnumItem(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return name + "(" + value + ")";
    }
}