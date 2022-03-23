package frameworkjava.practice.interfaceprac.generic;

@FunctionalInterface
public interface MyMapper<IN,OUT>{
    OUT map(IN s);
}

