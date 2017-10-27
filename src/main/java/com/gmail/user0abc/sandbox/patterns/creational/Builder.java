package com.gmail.user0abc.sandbox.patterns.creational;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class Builder {
    public static void main(String[] args){
        jdkClassic();
        new Builder().doBuilder();
    }

    private void doBuilder() {
        BuilderMember builder = Director.getBuilder();
        builder.addPart(new PartOfProduct("Part 1"));
        builder.addPart(new PartOfProduct("Part 2"));
        builder.addPart(new PartOfProduct("Part 3"));
        prn(builder.assemble().reportParts());
        prn(builder.assemble().reportParts());
    }

    static class Director{
        public static BuilderMember getBuilder(){
            return new BuilderMemberImpl();
        }
    }
    interface BuilderMember{
        void addPart(PartOfProduct part);
        Product assemble();
    }
    static class BuilderMemberImpl implements BuilderMember{
        PartOfProduct[] parts;
        @Override
        public void addPart(PartOfProduct part){
            if(parts==null){
                parts = new PartOfProduct[1];
                parts[0]=part;
            }
            PartOfProduct[] parts2 = new PartOfProduct[parts.length+1];
            for(int i = 0; i < parts.length; i++){
                parts2[i] = parts[i];
            }
            parts2[parts.length]=part;
            parts = parts2;
        }

        @Override
        public Product assemble() {
            return new Product(parts);
        }
    }

    static class Product{
        PartOfProduct[] parts;

        public Product(PartOfProduct[] parts) {
            this.parts = parts;
        }

        String reportParts(){
            StringBuilder sb = new StringBuilder();
            sb.append("Product ["+hashCode()+"] of ");
            for(int i = 0; i < parts.length; i++){
                sb.append(parts[i].name);
                if(i < parts.length-1)sb.append(", ");
            }
            return sb.toString();
        }
    }

    class PartOfProduct{
        String name;

        public PartOfProduct(String name) {
            this.name = name;
        }
    }

    private static void jdkClassic() {
        StringBuilder builder = new StringBuilder();
        builder.append("part 1").append(",").append("part 2");
        prn(builder.toString());
    }
}
